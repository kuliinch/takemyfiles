package group.learning;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streaming")
public class streaming {

  @GetMapping("/test")
  public ResponseEntity<Resource> stream() {
    Resource resource = new ClassPathResource("./iwoudlntsayfreed.mp4");
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"iwoudlntsayfreed.mp4\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
  }
}
