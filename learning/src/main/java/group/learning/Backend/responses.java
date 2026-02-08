package group.learning.Backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/responses")
public class responses {

  int tally;
  String string;

  responses() {
    string = "";
  }

  // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
  @GetMapping("/return_ok")
  public ResponseEntity<String> return_ok() {
    return new ResponseEntity<String>("ok!", HttpStatus.OK);

    /*
     * Status code
     * Header
     * Body
     */
  }

  @GetMapping("/return_ok_v2")
  public ResponseEntity<String> return_ok_v2() {
    return ResponseEntity.ok("OK!");
  }

  @GetMapping("/accept_response")
  public ResponseEntity<String> return_full_response() {
    ResponseEntity<String> response = ResponseEntity.accepted()
        .header("Header Name", "Header Value")
        .body("Body");
    return response;
  }

  @GetMapping("/get_tally")
  public ResponseEntity<?> get_tally() { // ? because it might be ResponseEntity<int> or it might go wrong and we
                                         // explain in a String (ResponseEntity<String>)
    try {
      return ResponseEntity.ok(tally);
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }
  }

  @GetMapping("/reset_tally")
  public ResponseEntity<String> reset_tally() {
    try {
      tally = 0;
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }

    return ResponseEntity.ok("Success");
  }

  @GetMapping("/add_to_tally")
  public ResponseEntity<String> add_to_tally() {
    try {
      tally++;
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }

    return ResponseEntity.ok("Success");
  }

  @GetMapping("/add_to_tally_amount")
  public ResponseEntity<String> add_to_tally(@RequestParam int amount) {
    // http://localhost:8080/responses/add_to_tally?amount=5
    try {
      tally += amount;
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }
    return ResponseEntity.ok("Success");
  }

  @GetMapping("/add_to_tally_v2")
  public ResponseEntity<String> add_to_tally_v2(@RequestParam(defaultValue = "1") int amount) { // Amount is optional
    // http://localhost:8080/responses/add_to_tally_v2?amount=5
    try {
      tally += amount;
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }
    return ResponseEntity.ok("Success");
  }

  @GetMapping("/uppercase/{stringy}")
  // if there is a questionmark in the PathVariable then it will be treated as a
  // RequestParam, this is standard to HTTP
  public String upper(@PathVariable String stringy) {
    return stringy.toUpperCase();
  }

  @GetMapping("/combo/{number}")
  public String combo_func(
      @PathVariable int number,
      @RequestParam(name = "string1", defaultValue = "STRING") String string,
      @RequestParam(name = "string2", defaultValue = "MONEY") String string2) {
    String res = "";
    for (int i = 0; i < number; i++) {
      res += string + string2;
    }
    return res;
  }

  @GetMapping("/combo_v2/{number}/{number2}")
  public String combo_2(
      @PathVariable(name = "number") int number,
      @PathVariable(name = "number2") int number2,
      @RequestParam(name = "string1", defaultValue = "STRING") String string,
      @RequestParam(name = "string2", defaultValue = "MONEY") String string2) {
    String res = "";
    for (int i = 0; i < number; i++) {
      res += string + string2 + number2;
    }
    return res;
  }

  @GetMapping("/downloadRick")
  public ResponseEntity<ClassPathResource> downloadRick() {

    ClassPathResource resource = new ClassPathResource("Rick.mp4");

    return ResponseEntity.ok().
      header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Rick.mp4").
      body(resource);
  }

  @GetMapping("/displayTest")
  public ResponseEntity<ClassPathResource> displayTest() {
    ClassPathResource resource = new ClassPathResource("test.png");

    return ResponseEntity.ok().
      header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=test.png")
      .contentType(MediaType.valueOf("image/png")) //Have to specify the file type, the rest is handled automatically
      .body(resource);

  }

}
