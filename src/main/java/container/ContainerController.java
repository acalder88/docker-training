package container;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle the health of the service.
 *
 * @author Alejandro Calderon Velez
 */
@RestController
@RequestMapping("/api/container")
public class ContainerController {

  @Autowired
  private KafkaPublisher publisher;

  /**
   * Obtains the http code of the health of the service.
   *
   * @return http code of the health of the service.
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> sayHello(HttpServletResponse response, @RequestParam("message") String message) {
    publisher.publishMessage(message);
    return new ResponseEntity<>("{\"Published\": \" " + message + "\"}", HttpStatus.OK);
  }
}
