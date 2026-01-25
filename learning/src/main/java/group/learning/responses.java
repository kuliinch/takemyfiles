package group.learning;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

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
    try {
      tally += amount;
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .header("Something went wrong:", "Help!")
          .body(e.toString());
    }
    return ResponseEntity.ok("Success");
  }
}
