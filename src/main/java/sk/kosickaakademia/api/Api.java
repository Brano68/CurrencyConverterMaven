package sk.kosickaakademia.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Api {

    //method for getting Map for example USD 1.18 ........ USD is a key and 1.18 is a value
    public Map<String, Double> getExchngeRates(Set<String> rates){


        if(rates == null || rates.size() == 0){
            return null;
        }

        Map<String, Double> map = new HashMap<>();
        //here i am calling JSON
        JSONObject js = parseString();
        //System.out.println(js);
        for(String nameOfCurrency : rates){
            if(js.containsKey(nameOfCurrency)){
                double value = (double)js.get(nameOfCurrency);
                map.put(nameOfCurrency,value);
            }
        }
        return map;
    }


    //source for inspiration
    //https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751
    //method for getting string from API
    public String getRatesFromApiServer(){
        try {

            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=b1eb8b41c45acff55c69f48fddefe598&format=1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();
                return inline;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    //method for parsing JSON and getting onlz the rates
    public JSONObject parseString(){
        String inline = getRatesFromApiServer();
        JSONParser parse = new JSONParser();
        JSONObject data_obj = null;
        try {
            data_obj = (JSONObject) parse.parse(inline);
            JSONObject obj = (JSONObject) data_obj.get("rates");
            return obj;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

















/* only training
    public String gainConnection(){
        try {
            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=b1eb8b41c45acff55c69f48fddefe598&format=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            System.out.println("----" + " " + responsecode);
            if(responsecode == 200){
                String a = gainJson(url);
                return a;
            }else{
                System.out.println("Something wrong");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String gainJson(URL url){


        String inline = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        //Close the scanner
        scanner.close();
        return inline;
    }
*/
}
