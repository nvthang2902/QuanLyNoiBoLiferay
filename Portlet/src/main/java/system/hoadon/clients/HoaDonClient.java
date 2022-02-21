package system.hoadon.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import system.hoadon.dto.HoaDonDTO;

import java.io.File;

@Service
@RequiredArgsConstructor
public class HoaDonClient {

    private final String path = "";
    @Autowired
    @Qualifier("hoaDonRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public void uploadFile(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        Resource resource = new FileSystemResource(file);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:9191/hoadon/upload";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), requestEntity, String.class);
    }

    public HoaDonDTO getByName(String name) {

        String restURL = path + "/" + name;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<HoaDonDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<HoaDonDTO>() {
                });

        return responseEntity.getBody();
    }

    public String getUrl() {

        String imageUrl = "http://localhost:9191/hoadon/files";

        return imageUrl;
    }
}

