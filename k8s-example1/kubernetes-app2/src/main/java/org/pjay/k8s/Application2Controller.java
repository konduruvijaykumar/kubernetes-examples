/**
 * 
 */
package org.pjay.k8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author vijayk
 *
 */
@RestController
public class Application2Controller {

	private static String appMessage = "app2-welcome";

	@Autowired
	RestTemplate restTemplate;

	// When run as spring boot application
	private static final String LOCAL_URL = "http://localhost:8181";
	// When deployed as war file
	// private static final String LOCAL_URL = "http://localhost:8181/app1";

	@RequestMapping(value = "/", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>(
				"Welcome to application 2 " + "value of instance variable appMessage is " + appMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "/messagefromapp2", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> messageFromApp2() {
		return new ResponseEntity<String>(messageFromApp(), HttpStatus.OK);
	}

	private String messageFromApp() {
		return "Message from application 2 " + "value of instance variable appMessage is " + appMessage;
	}

	@RequestMapping(value = "/updateappmessage/{appMessage}", method = { RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateAppName(@PathVariable("appMessage") String applicationMessage) {
		appMessage = applicationMessage;
		return new ResponseEntity<String>("appMessage instance variable set to " + appMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "/app2toapp1", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String[]> app2ToApp1() {
		String app2ResponseString = messageFromApp();
		ResponseEntity<String> app1Response = restTemplate.getForEntity(LOCAL_URL + "/messagefromapp1", String.class);
		String responseMessage[] = { app2ResponseString, app1Response.getBody().toString() };
		return new ResponseEntity<String[]>(responseMessage, HttpStatus.OK);
	}

}
