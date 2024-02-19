package basics.services;

public class Filters {

    static Object[] storage ={"Prash",12.5,true};
    public static void main(String[] args) {
            Filters.map();
    }
    public static void map(){
        for(Object each:storage)
            System.out.print(each + " ");
        System.out.println();
    }
}
