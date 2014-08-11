package com.stylight.service;

import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;



@Service
public class CurrencyConverterManagerImpl implements CurrencyConverterManager {
	
	private static String resturl = "http://rate-exchange.appspot.com/currency?from=%s&to=%s";
	
	
	HttpClient httpclient = new HttpClient();
	
	@Override
	public String convert(String source, String target) {
		 String from = "";
		 String to = "";
		
		Float result = 1.0f;
		if (source.equalsIgnoreCase(target)) {
			return "1";
		}
		
		if(source.equalsIgnoreCase(EURO)){
			from = "EUR";
		}
		
		else if(source.equalsIgnoreCase(YEN)){
			from = "YEN";
		}
		
		else if(source.equalsIgnoreCase(EURO)){
			from = "USD";
		}
		
		switch (source.toUpperCase()) {
		
		case EURO:
			switch (target.toUpperCase()) {
			case EURO:
				result = 1.0f;
				to ="EUR";
				break;

			case DOLLAR:
				result = 2.0f;
				to ="USD";
				break;

			case YEN:
				result = 3.0f;
				to ="YEN";
				break;
			}

		case DOLLAR:
			switch (target.toUpperCase()) {
			case EURO:
				result = 0.5f;
				to ="EUR";
				break;

			case DOLLAR:
				result = 1.0f;
				to ="USD";
				break;

			case YEN:
				result = 0.3f;
				to ="YEN";
				break;
			}

		case YEN:
			switch (target.toUpperCase()) {
			case EURO:
				result = 0.3f;
				to ="EUR";
				break;

			case DOLLAR:
				result = 3.0f;
				to ="USD";
				break;

			case YEN:
				result = 1.0f;
				to ="YEN";
				break;
			}
		}
		
		
		String restAPI = String.format(resturl,
				   from, to);

		GetMethod get = new GetMethod(restAPI);
		
		try {
			httpclient.executeMethod(get);
			
			if (get.getStatusCode() == HttpStatus.SC_OK) {
				JSONObject qResponse = new JSONObject(new JSONTokener(
						new InputStreamReader(get.getResponseBodyAsStream())));
				System.out.println("Query response: " + qResponse.toString(2));
				return ((Double) qResponse.get("rate")).toString();
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.toString();
	
	}

}
