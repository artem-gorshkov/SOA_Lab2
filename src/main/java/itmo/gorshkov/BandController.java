package itmo.gorshkov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("v1/api/band")
public class BandController {
    private final static Logger log = LoggerFactory.getLogger(BandController.class);

    private final BandClient client;
    private URI targetUrl;

    public BandController(BandClient client) {
        initServices();
        this.client = client;
    }

    @GetMapping("/{band-id}/participants/add")
    public String addParticipants(@PathVariable("band-id") Integer id) {
        return editParticipants(id, true);
    }

    @GetMapping("/{band-id}/participants/remove")
    public String removeParticipants(@PathVariable("band-id") Integer id) {
        return editParticipants(id, false);
    }

    private String editParticipants(Integer id, Boolean isAdd) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id can't be null");
        }

        try {
            MusicBand band = client.getBand(id, targetUrl);
            if (isAdd) {
                band.setNumberOfParticipants(band.getNumberOfParticipants() + 1);
            } else {
                band.setNumberOfParticipants(band.getNumberOfParticipants() - 1);
                if (band.getNumberOfParticipants() <= 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of participants can't be less than 1");
                }

            }
            Response response = client.putBand("text/plain", band, targetUrl);
            if (response.status() == 200) {
                return band.getNumberOfParticipants().toString();
            }
            if (response.status() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Band not found");
            }
            if (response.status() == 400) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request from server");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown answer from server");
            }
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Band not found");
        } catch (FeignException.InternalServerError e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Main service unavailable");
        }
    }

    private boolean initServices() {
        try {
            String sdUrl = "http://localhost:8500";
            String url = sdUrl + "/v1/agent/service/l1";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> answer = restTemplate.getForEntity(url, String.class);
            if (HttpStatus.OK.equals(answer.getStatusCode())) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(answer.getBody());
                targetUrl = new URI("http://" + root.path("Address").textValue()+ ":" + root.path("Port").intValue() + "/api/band");
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

}
