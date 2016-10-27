package com.apple.test.apple;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiConnection {

	public static JSONArray apiTesting(String apiUrl) throws Exception{

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code :" + conn.getResponseCode());
			}

			Scanner scan = new Scanner(url.openStream());
			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();
            scan.close();
            
            JSONObject jsonObj = new JSONObject(entireResponse);
			String resultCount = jsonObj.getString("resultCount");
			System.out.println("Result Count Value in JSON :" + resultCount);

			JSONArray array = jsonObj.getJSONArray("results");
			
			conn.disconnect();
			return array;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	} // function

} // class

