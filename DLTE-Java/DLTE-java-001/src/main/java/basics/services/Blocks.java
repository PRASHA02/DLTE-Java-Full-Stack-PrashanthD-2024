package basics.services;

import java.util.Arrays;

class Traditional {
    static{
        System.out.println("Casa");
        Traditional.main(new String[]{"prash","vineeth"});
    }
    public static void main(String[] args) {
        System.out.println("CLI Banking");
        System.out.println(Arrays.toString(args));
    }
}

class Facility{
    public static void main(String[] args) {
        System.out.println("Mobile banking");
    }
}