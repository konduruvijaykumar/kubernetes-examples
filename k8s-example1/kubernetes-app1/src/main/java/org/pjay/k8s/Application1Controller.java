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
public class Application1Controller {

	private static String appMessage = "app1-welcome";

	@Autowired
	RestTemplate restTemplate;

	private static final String LOCAL_URL = "http://localhost:8282";

	@RequestMapping(value = "/", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>(
				"Welcome to application 1 " + "value of instance variable appMessage is " + appMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "/messagefromapp1", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> messageFromApp1() {
		return new ResponseEntity<String>(messageFromApp(), HttpStatus.OK);
	}

	private String messageFromApp() {
		return "Message from application 1 " + "value of instance variable appMessage is " + appMessage;
	}

	@RequestMapping(value = "/updateappmessage/{appMessage}", method = { RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateAppName(@PathVariable("appMessage") String applicationMessage) {
		appMessage = applicationMessage;
		return new ResponseEntity<String>("appMessage instance variable set to " + appMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "/app1toapp2", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String[]> app1ToApp2() {
		String app1ResponseString = messageFromApp();
		ResponseEntity<String> app2Response = restTemplate.getForEntity(LOCAL_URL + "/messagefromapp2", String.class);
		String responseMessage[] = { app1ResponseString, app2Response.getBody().toString() };
		return new ResponseEntity<String[]>(responseMessage, HttpStatus.OK);
	}

}
