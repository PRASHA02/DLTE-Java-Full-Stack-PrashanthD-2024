package basics.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExp {
    public static void main(String[] args) {
        String mobileExp = "\\d{10,}";
        Pattern pattern = Pattern.compile(mobileExp);
        Matcher mathcher = pattern.matcher(args[0]);
//        if(mathcher.matches())
//            System.out.println("Valid Mobile number");
//        else
//            System.out.println("Invalid mobile number");

        String  passwordExp = "^(?=.[a-zA-Z])(?=.*[0-9])(?=.*[@$_%#])(?=.*//S).{8,}+$";
        pattern = Pattern.compile(passwordExp);
        mathcher = pattern.matcher(args[0]);
    if(mathcher.matches()){
        System.out.println("Valid");
    }
//        "^(?=.[a-zA-Z])(?=.[0-9])(?=.[@$_%#])(?=//S+$).{8,}+$"
    }
}
