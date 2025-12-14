package kuliandreed.simp_file_sharing_monolith;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/print")
public class printAPItest {

  @GetMapping("/hellotest")
  public ResponseEntity<String> hellotest() {
    return new ResponseEntity("Hello test", HttpStatus.ACCEPTED);
  }
}
