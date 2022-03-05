package itmo.gorshkov;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "bandClient", url = "${service.host}", path = "/v1/api/band", configuration = FeignConfig.class)
public interface BandClient {
    @GetMapping("/{id}")
    MusicBand getBand(@PathVariable("id") Integer id);

    @PutMapping("")
    Response putBand(MusicBand musicBand);
}
