package com.testing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class UsersList {

	public static void cityOrUserList(int option) throws IOException, ParseException {
		String strUrl = "";
		if(option == 1) {
			strUrl = "https://bpdts-test-app.herokuapp.com/city/London/users";
		} else {
			strUrl = "https://bpdts-test-app.herokuapp.com/users";
		}
		
		URL url = new URL(strUrl);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.connect();
		
		int responseCode = conn.getResponseCode();
		
		if(responseCode != 200) {
			throw new RuntimeException("HttpResponseCode error: " +responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			String inline = "result";
			while(sc.hasNext()) {
				inline += sc.nextLine();
			}
			
			System.out.println("\n List of user details from London");
			System.out.println(inline);
			
			sc.close();
		}
		
	}

	public static void main(String[] args) throws IOException, ParseException {
		//calling below method to display London based users or all the users by passing the parameter.
		//If 1 is passed London based users will display else all the user list will display
		cityOrUserList(1);
	}

}
