package basics.project;

import basics.project.entities.UserInformation;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        UserInformation userInformation = new UserInformation();
        System.out.println("Enter the username");
        String userName = scanner.nextLine();
        System.out.println( "Hello World!" );
    }
}
