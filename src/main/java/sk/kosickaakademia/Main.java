package sk.kosickaakademia;

import sk.kosickaakademia.api.Api;
import sk.kosickaakademia.calculator.Calculator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Currency converter
 *
 */
public class Main
{
    public static void main( String[] args )
    {
/*
        System.out.println( "Exchanger" );
        Set<String> set = new HashSet<>();
        set.add("FJD");
        set.add("AAA");
        set.add("USD");
        set.add("BTC");
        Api api = new Api();
        Map map = api.getExchngeRates(set);
        System.out.println(map.toString());
*/
        Calculator calculator = new Calculator();
        calculator.calculate(123);

    }
}
