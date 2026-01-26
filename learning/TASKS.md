1. Return status codes and messages
2. Handle data embedded in the link 
  - Sanitise user input
3. Send API requests using hardcoded tokens 
  - Send a response depending on whether the token in the header is valid or not
  - Use redis to have those tokens in memory
  - Check the IP locality and do something different
4. Stream a file to the user given the file ID
5. Basic login page
  - Have another project serve html/js to an end user and sent requests to authenticate
  - Use springboot to authenticate the user (no more hardcoding creds)
6. Stream a file to the user given the file ID and user token
  - Communicate with another server to verify the user first
  - Depending on the (hardcoded) "permission" limit the bandwidth
7. Queue requests
  - Expose an API that will serve files based on ID and token BUT the request will be queued
  - Spam this API to observe Java 25's virtual threads
8. Communicate with a DNS server and request different information from them
  - Query a DNS server for something like "happymessages.tmf" and send requests and send the same request to "sadmessages.tmf" and see different IPs result in different results
  - Requires setting up two other servers to repond differently
9. Use NGINX to to create a CDN node
  - Have communications proxied through this server
10. Use prometheus to find the healthiest node for communicating between user and service
