package sk.kosickaakademia.calculator;

import sk.kosickaakademia.api.Api;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Calculator {

    private static final String[] rates = new String[]{"USD","BTC"};


    public void calculate(double euro){
        if(euro<0){
            System.out.println("Input can not be a red number!!!");
            return;
        }
        Set<String> set = fillTheSet();
        Api api = new Api();
        Map<String,Double> map = api.getExchngeRates(set);

        for(Map.Entry<String, Double> entry : map.entrySet()) {
            double value = entry.getValue();
            double result = value * euro;
            print(euro, value, result);
        }

    }

    //create from array -> set
    private Set<String> fillTheSet(){
        Set<String> set = new HashSet<>();
        for(int i = 0; i < rates.length; i++){
            set.add(rates[i]);
        }
        return set;
    }

    //method only for printing
    private void print(double euro, double value, double result){
        System.out.println( "Euro: " + euro + " curse: " + value + " result: " + result);
    }


}
