package basics.oops;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App
{
    private String name="prashanth";
    public String usn = "4MT20CS117";
    protected boolean status=true;
    int empId = 789488;
    void test(){
        System.out.println("My name is "+name);
    }

    public static void main(String[] args) {
        App app = new App();
        app.test();
        System.out.println("Name "+app.name);
        System.out.println("EmpId " +app.empId);
    }

}

class basic{
    public static void main(String[] args) {
        App app = new App();
        app.test();
        System.out.println(app.status);
    }

}
