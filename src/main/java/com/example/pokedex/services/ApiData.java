package com.example.pokedex.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ApiData implements AccessData{
    /**
     * This class serves to get data of a pokémon from an external database.
     * The pokemon is chosen by instanciating an ApiData object using ApiData() with the id number of the pokemon.
     * Then data is accessible using the getData() function.
     */

    private HttpGet request;

    public void setRequest(HttpGet request) {this.request = request;}
    public HttpGet getRequest() {return request;}

    public ApiData(int id){
        request = new HttpGet("https://pokeapi.co/api/v2/pokemon/" + id);
    }

    public Object getData(){
        String jsonResponse = "";
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            jsonResponse = EntityUtils.toString(result.getEntity(), "UTF-8");

            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(jsonResponse);

            return resultObject;

        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        } catch (ParseException e) {
            System.err.println("Could not parse the response given by the API as JSON");
            System.err.println("Response body is :");
            System.err.println(jsonResponse);
            e.printStackTrace();
            return "ParseException";
        }
    }
}
