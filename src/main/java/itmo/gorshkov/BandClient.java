package itmo.gorshkov;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(value = "bandClient", url = "${service.host}", path = "api/band", configuration = FeignConfig.class)
public interface BandClient {
    @GetMapping("/{id}")
    MusicBand getBand(@PathVariable("id") Integer id, URI baseUrl);

    @PutMapping(value = "")
    Response putBand(@RequestHeader("X-Content-Type2") String contentType, MusicBand musicBand, URI baseUrl);
}
