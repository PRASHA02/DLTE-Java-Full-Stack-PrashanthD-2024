package basics.files;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeFileRepository employeeFileRepository = new EmployeeFileRepository();
        employeeFileRepository.writeFile();
        employeeFileRepository.readFile();
    }
}
