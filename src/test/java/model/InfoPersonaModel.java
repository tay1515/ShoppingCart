package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoPersonaModel {

    private static String first_name;
    private static String last_name;
    private static String county_code;

    private static List<Map<String, String>> map_data;

    private static List<String> list_data;


    public static String getFirstName() {
        return first_name;
    }

    public static void setFirstName(String firstName) {
        first_name = firstName;
    }

    public static String getLastName() {
        return last_name;
    }

    public static void setLastName(String lastName) {
        last_name = lastName;
    }

    public static String getCode() {
        return county_code;
    }

    public static void setCode(String code) {
        county_code = code;
    }


    public static List<Map<String, String>> getlist(){
        return map_data;
    }
    public static void setMap(List<Map<String, String>> info){

        if (map_data == null){
            map_data = new ArrayList<>();
        }

        for (Map<String, String> item : info) {
            map_data.add(item);
        }
    }

    public static void setList(String info){

        if (list_data == null){
            list_data = new ArrayList<>();
        }
            list_data.add(info);
    }

}
