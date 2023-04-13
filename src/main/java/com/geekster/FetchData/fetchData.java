package com.geekster.FetchData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetchData {

    public static void main(String[] args) throws Exception {

        URL getUrl = new URL("  https://api.nationalize.io/?name=nathaniel");
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponseData = new StringBuilder();
            String readLine;


            while ((readLine = in.readLine()) != null) {
                jsonResponseData.append(readLine);
            }

            in.close();
            JSONObject masterData = new JSONObject(jsonResponseData.toString());
            JSONArray jsonArray = masterData.getJSONArray("country");

            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject countryObj = jsonArray.getJSONObject(i);
                System.out.println("country_id => " + countryObj.get("country_id"));
                System.out.println("probability=> " + countryObj.get("probability"));
            }

        } else {
            System.out.println("This is not valid URL- " + responseCode);
        }


    }
}
