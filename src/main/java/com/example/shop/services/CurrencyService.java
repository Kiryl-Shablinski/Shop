package com.example.shop.services;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CurrencyService {

    public double getEurPrice(double binPrice){
        try{
            URL url = new URL("https://api.nbrb.by/exrates/rates/EUR?parammode=2");
            return readApiPrice(binPrice, url);
        }catch (Exception e){
            e.printStackTrace();
            return -10;
        }
    }

    public double getUsdPrice(double binPrice){
        try{
            URL url = new URL("https://api.nbrb.by/exrates/rates/USD?parammode=2");
            return readApiPrice(binPrice, url);
        }catch (Exception e){
            e.printStackTrace();
            return -10;
        }
    }

    private double readApiPrice(double binPrice, URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String course = bufferedReader.readLine();
        JSONObject jsonObject = new JSONObject(course);
        double currency = Double.parseDouble(jsonObject.get("Cur_OfficialRate").toString());
        return Math.round(binPrice / currency * 100.0) / 100.0;
    }
}
