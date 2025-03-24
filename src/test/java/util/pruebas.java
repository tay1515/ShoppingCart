package util;

import java.text.DecimalFormat;
import java.util.*;

public class pruebas {
    public static void main(String[] args) {

        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        float tax = (float) (63.96 % 100 * 8);
        //System.out.println(tax);
        System.out.println("Con ceil: " + decimalFormat.format(Math.ceil(tax)));



        /*
        Random random = new Random();

        List<String> myList = Arrays.asList("A", "B", "C", "D");

        Random r = new Random();

        int randomitem = r.nextInt(myList.size());
        String randomElement = myList.get(randomitem);

        System.out.println(randomElement);

         */
    }
}
