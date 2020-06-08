/**
 * 
 */
package org.pjay.k8s;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
public class Application2Controller {

	private static String appMessage = "app2-welcome";

	@RequestMapping(value = "/", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>(
				"Welcome to application 2 " + "value of instance variable appMessage is " + appMessage, HttpStatus.OK);
	}

}
