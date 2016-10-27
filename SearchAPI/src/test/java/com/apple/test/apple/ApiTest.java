package com.apple.test.apple;

import static org.junit.Assert.*;

import java.util.Properties;

import org.json.JSONArray;
import org.junit.Test;

import com.apple.test.util.TestUtil;

public class ApiTest {
	
	private static Properties prop = null;
	private String apiPropertyPath = "./api.properties";

	public ApiTest(){
		
		prop = TestUtil.loadProperties(apiPropertyPath);
	}

	ApiConnection check = new ApiConnection();

	// Test if the response contains given parameter key value pair as {"artistName":"Alicia keys"} matches in all the results
	// Expected : Test Failure
	@Test
	public void testTerm() throws Exception{
		
        // Method gets the url input from the property file
		JSONArray getArray = ApiConnection.apiTesting(prop.getProperty("url1"));
		try {
			for (int i = 0; i < getArray.length(); i++) {
				String artistName = getArray.getJSONObject(i).getString("artistName");
				boolean checkTermValue = artistName.contains("Alicia keys");
				assertEquals("All results are not matched", true,
						checkTermValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Test if the result count is 0 , when the parameter key 'Term' is not added in the url
	// Expected : Test Passed
	@Test
	public void testMissingTerm() throws Exception{
		JSONArray getArray = ApiConnection.apiTesting(prop.getProperty("url2"));
		try {
			if (getArray.length() == 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Test if all parameter key and value pairs match
	// Expected : Test Passed
	@Test
	public void testAllFourParameters() throws Exception{
		JSONArray getArray = ApiConnection.apiTesting(prop.getProperty("url3"));
		try {
			for (int i = 0; i < getArray.length(); i++) {
				String trackName = getArray.getJSONObject(i).getString(
						"trackName");
				String country = getArray.getJSONObject(i).getString("country");
				String media = getArray.getJSONObject(i).getString("kind");
				int limit = getArray.length();
				boolean checkTermValue = trackName.contains("Zootopia");
				boolean countryName = country.contains("USA");
				boolean mediaType = media.contains("movie");
				assertEquals("Track name does not matched", true,checkTermValue);
				assertEquals("Country does not match", true, countryName);
				assertEquals("Media type does not matched", true,mediaType);
				assertEquals("Limit not matched", 1, limit);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
