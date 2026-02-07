package group.learning;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/fs")
public class file_server {

  ArrayList<UUID> tokens; // hardcoded tokens so i dont need to talk to an auth server

  public file_server() {

    tokens = new ArrayList<UUID>();
    System.out.println("-------GENERATING \"TOKENS\"-------");
    for (int i = 0; i < 10; i++) {
      UUID token = UUID.randomUUID();
      System.out.println(token.toString());
      tokens.add(token);
    }
    System.out.println("-------DONE!------");
  }

  @GetMapping("/getMeta/{id}")
  public ResponseEntity<String> getID(
      @PathVariable(required = true) String id,
      @RequestHeader(required = true) String token) {

    // Make sure token is valid
    HttpStatusCode isTokenValid = validateToken(token);
    if (isTokenValid != HttpStatus.OK)
      return ResponseEntity.status(isTokenValid).build();

    // CONTINUE maybe return a JSON of metadata given ID
    // also using a csv or json for storing metadata is an awful idea, not sure if I
    // should learn databases first...

    return null;
  }

  // Helper function validateToken(String token)
  private HttpStatusCode validateToken(String header) {
    final String BEARER = "Bearer "; // maybe better to declare as class variable?

    if (!header.startsWith(BEARER))
      return HttpStatus.BAD_REQUEST;

    String rawToken = header.substring(BEARER.length());

    UUID uuid;
    try {
      uuid = UUID.fromString(rawToken);
    } catch (IllegalArgumentException e) {
      return HttpStatus.BAD_REQUEST;
    }

    if (!tokens.contains(uuid))
      return HttpStatus.UNAUTHORIZED;

    return HttpStatus.OK;
  }

}
