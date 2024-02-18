package com.example.shopapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyData {

    public static ArrayList<String> itemsName=new ArrayList<>(Arrays.asList("box","pencil","bottle"));
    public static ArrayList<String> itemsDescription=new ArrayList<>(Arrays.asList(
                    "matte black cardboard box, 12\"x8\"x6\"",
                    "7\" yellow wooden pencil with a smooth graphite core",
                    "10\" midnight blue stainless steel water bottle, holding 20 oz"));
    public static ArrayList<Integer> itemsAmount=new ArrayList<>(Arrays.asList(4,1,10));
    public static HashMap<String,String>users=new HashMap<String,String>(){{
        put("hadar","haha100");
        put("admin","admin");
    }};

}
