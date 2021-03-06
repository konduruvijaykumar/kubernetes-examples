/**
 * 
 */
package org.pjay.k8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	// private static final String APP1_LOCAL_URL = "http://localhost:8181";
	// When deployed as war file
	// private static final String APP1_LOCAL_URL = "http://localhost:8181/app1";
	
	//private String app1_service = "http://service-app1/app1";
	
	@Value("${SERVICE_APP1_SERVICE_HOST:service-app1}")
	private String SERVICE_HOST;
	@Value("${SERVICE_APP1_SERVICE_PORT:80}")
	private String SERVICE_PORT;

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
		// ResponseEntity<String> app1Response = restTemplate.getForEntity(APP1_LOCAL_URL + "/messagefromapp1", String.class);
		ResponseEntity<String> app1Response = restTemplate.getForEntity(
				"http://" + SERVICE_HOST + ":" + SERVICE_PORT + "/app1" + "/messagefromapp1", String.class);
		String responseMessage[] = { app2ResponseString, app1Response.getBody().toString() };
		return new ResponseEntity<String[]>(responseMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "/servicediscoveryurl", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> serviceDiscoveryUrl() {
		// return new ResponseEntity<String>(APP1_LOCAL_URL, HttpStatus.OK);
		return new ResponseEntity<String>("http://"+SERVICE_HOST+":"+SERVICE_PORT, HttpStatus.OK);
	}

	@RequestMapping(value = "/callappurl", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> callAppUrl(@RequestParam String url) {
		ResponseEntity<String> appResponse = restTemplate.getForEntity(url, String.class);
		return new ResponseEntity<String>(appResponse.getBody().toString(), HttpStatus.OK);
	}

}
