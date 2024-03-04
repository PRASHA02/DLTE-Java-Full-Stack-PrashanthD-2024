package basics.exception;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;

public class MapCollections {
    public static void main(String[] args) {
        Hashtable<String,Double> myAsset=new Hashtable<>();
        myAsset.put("Gold",50000.0);myAsset.put("Silver",6700.5);
        myAsset.put("Bronze",893.4);
        System.out.println(myAsset);
        TreeMap<String,Double> treeMap=new TreeMap<>();
        treeMap.putAll(myAsset);
        System.out.println(treeMap);

        Collection<Double> prices = treeMap.values();
        System.out.println(prices);

        myAsset.remove("Gold");

        treeMap.replace("silver",1900.4);
        System.out.println(treeMap);

        System.out.println(treeMap.containsKey("Gold"));
        System.out.println(myAsset.containsValue(1900.4));


    }
}
