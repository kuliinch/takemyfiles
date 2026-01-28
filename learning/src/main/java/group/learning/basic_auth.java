package group.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Tokens will be hardcoded in a ___
 */

@RestController
@RequestMapping("/basicauth")
public class basic_auth {

  Map<String, Boolean> tokens;
  Random dice;

  public basic_auth() {
    tokens = new HashMap<String, Boolean>(); // String token, Boolean administrator
    tokens.put("ALEXANDER", true);
    tokens.put("LANCE", true);
    tokens.put("NORMIE", false);
    dice = new Random();
  }

  @GetMapping("/diceroll")
  public ResponseEntity<String> diceroll(
      @RequestHeader(name = "Token", required = true) String tokenHeader,
      @RequestParam(name = "times", defaultValue = "1") int times) {

    if (!tokenHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body("Missing valid authorisation token \"Token\" in header");
    }

    String token = tokenHeader.substring("Bearer ".length());

    if (!tokens.containsKey(token)) {
      return ResponseEntity.status(401).body("Invalid token");
    }

    if (!tokens.get(token)) {
      return ResponseEntity.ok("The dice rolled " + (dice.nextInt(6) + 1));
    }

    String rolls = "";
    for (int i = 0; i < times; i++) {
      rolls += "The dice rolled " + (dice.nextInt(6) + 1) + "\n";
    }
    return ResponseEntity.ok(rolls);
  }

  /*
   * Using JS to call the endpoint:
   * Go to your web browser and go to localhost:8080
   * Then enter this in the dev console:
   *
   * fetch("localhost:8080/basicauth/diceroll", {
   * headers: {
   * Token: "Bearer ALEXANDER"
   * }
   * })
   * .then(res => res.text())
   * .then(text => console.log(text));
   *
   */

}
