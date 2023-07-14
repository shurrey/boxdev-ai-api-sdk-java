package boxdev.ai.services;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import org.apache.hc.client5.http.classic.HttpClient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import java.util.Collections;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import boxdev.ai.objects.BoxAiRequest;
import boxdev.ai.objects.BoxAiResponse;
import boxdev.ai.services.RequestResponseLoggingInterceptor;

public class BoxAiService {

	private Logger log = null;
	private String token = "";

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public BoxAiService (String token) {
		log = LoggerFactory.getLogger(BoxAiService.class);
        this.token = token;
	}

	public BoxAiResponse send(BoxAiRequest request) {
		log.info("CREATE");
		String endpoint = "https://api.box.com/2.0/ai/ask";

		BoxAiResponse response = sendRequest(endpoint, HttpMethod.POST, request, false);

		log.info(response.toString());
		return response;
	}

    private static RestTemplate getRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

		// Workaround for allowing unsuccessful HTTP Errors to still print to the screen
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
		    protected boolean hasError(HttpStatus statusCode) {
		        return false;
		    }});


		return(restTemplate);

	}

	private BoxAiResponse sendRequest(String sUri, HttpMethod method, BoxAiRequest body, boolean isCollection) {

		//List<Course> courseList = new ArrayList();
		RestTemplate restTemplate = null;
		BoxAiResponse boxAiResponse = null;

		try {

			restTemplate = getRestTemplate();

	        URI uri = new URI(sUri);

			HttpHeaders headers = new HttpHeaders();

			headers.add("Authorization", "Bearer " + this.token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			log.info("Request Headers: " + headers.toString());

			HttpEntity<BoxAiRequest> request = new HttpEntity<BoxAiRequest>(body, headers);
			log.info("Request Body: " + request.getBody());

			if(isCollection) {
				/*BoxAiResponse lastResponse = new BoxAiResponse();
				log.info("in isCollection, URI is " +uri.toString());

				while(uri != null) {
					log.info("getting terms");
					ResponseEntity<BoxAiResponse> response = restTemplate.exchange(uri, method, request, BoxAiRequest.class );

					log.info("setting tempResponse");
					BoxAiResponse tempResponse = response.getBody();

					log.info("getting results");
					BoxAiResponse[] results = tempResponse.getResults();

					log.info("if");
					if(lastResponse != null && results.length > 0 ) {
						log.info("nextIf");
						if(results[results.length-1].getId() != lastResponse.getId()) {
							log.info("startFor");
			         for(int i = 0; i < results.length; i++) {
								log.info("forLastCourse");
			         	lastCourse = results[i];
								log.info("forCourseAdd");
								courseList.add(results[i]);
			         }
							 try {
								uri = new URI(RestConfig.host + tempCourses.getPaging().getNextPage());
				 				log.info("NewURI is " + uri);
				 			} catch (URISyntaxException e) {
				 				// TODO Auto-generated catch block
								log.info("setNewURIEx");
				 				e.printStackTrace();
				 			} catch (NullPointerException npe) {
								log.info("Next Page is null, that means we are done!");
								uri = null;
							}
						} else {
							log.info("nextIfFalse");
			         uri = null;
			    	}
		    	} else {
						log.info("ifFalse");
		         uri = null;
		    	}
				}
				log.info("exit while");*/

			} else {
				ResponseEntity<BoxAiResponse> response = restTemplate.exchange(uri, method, request, BoxAiResponse.class );
				log.info("Response: " + response);

				boxAiResponse = response.getBody();
		        log.info("BoxAiResponse: " + boxAiResponse.toString());
			}
		}
		catch (Exception e) {
			log.error("Exception encountered");
			e.printStackTrace();
		}

        return (boxAiResponse);
	}
}
