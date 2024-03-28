//package business.logic;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
//        import static org.mockito.Mockito.*;
//
//        import java.sql.Connection;
//        import java.sql.PreparedStatement;
//        import java.sql.ResultSet;
//        import java.sql.SQLException;
//        import java.util.ArrayList;
//        import java.util.List;
//
//import backend.exceptions.UserAlreadyExistException;
//import org.junit.After;
//        import org.junit.Before;
//        import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//        import org.mockito.MockitoAnnotations;
//
//        import entity.backend.Employee;
//        import entity.backend.EmployeeAddress;
//        import backend.exceptions.ConnectionFailureException;
//        import backend.method.EmployeeInterface;
//        import db.connection.Database;
//
//import org.junit.Test;
//import org.mockito.junit.MockitoJUnitRunner;
//
////Junit and Mockito is used for testing the class test cases
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest {
//
//    //This annotation is used to create a mock object.
//    @Mock
//    private Connection mockConnection;
//
//    @Mock
//    private PreparedStatement mockPreparedStatement;
//
//    @Mock
//    private ResultSet mockResultSet;
//
//    @Mock
//    private EmployeeInterface employeeInterface;
//
//    @InjectMocks
//    private App app;
//
//    //They are executed before each test method in the test class
//    @Before
//    public void initialize() throws SQLException, ConnectionFailureException {
//        app= new business.logic.App();
//        app.connection = mockConnection;
//    }
//    //They are executed after each test method in the test class
//    @After
//    public void tearDown() throws SQLException {
//        // Close resources
//        if (mockResultSet != null) {
//            mockResultSet.close();
//        }
//        if (mockPreparedStatement != null) {
//            mockPreparedStatement.close();
//        }
//        if (mockConnection != null) {
//            mockConnection.close();
//        }
//    }
//
//    @Test
//    public void testWriteEmployeeDetails() throws SQLException, UserAlreadyExistException,ConnectionFailureException {
//
//
//        //Employee Details
//        EmployeeAddress permanentAddress = new EmployeeAddress("shree veeramathe", "jodukatte", "karkala", "karnataka", 567890);
//        EmployeeAddress temporaryAddress = new EmployeeAddress("prasanna", "mailasandra", "karkala", "karnataka", 876543);
//        Employee employee = new Employee("prashanth", "d", "karkala", 444, 7896543212L, "prash@gmail.com", permanentAddress, temporaryAddress);
//
//        EmployeeAddress permanentAddressOne = new EmployeeAddress("shree veeramathe", "jodukatte", "karkala", "karnataka", 567890);
//        EmployeeAddress temporaryAddressOne = new EmployeeAddress("prasanna", "mailasandra", "karkala", "karnataka", 876543);
//        Employee employeeOne = new Employee("prashanth", "d", "karkala", 444, 7896543212L, "prash@gmail.com", permanentAddress, temporaryAddress);
//
//        // Mock the App class
//        //App mockApp = mock(App.class);
//        EmployeeInterface mockApp = mock(App.class);
//        mockApp.writeEmployeeDetails(employee);
//
//        // Verify that writeEmployeeDetails method was invoked with the correct arguments
//        verify(mockApp).writeEmployeeDetails(any());
//       // assertTrue(app.writeEmployeeDetails(employee));
//
//        assertEquals(employeeOne.getEmpID(),employee.getEmpID());
////        assertNotSame(employeeOne,employee);
//
//    }
//
//
//    @Test
//    public void testDisplayEmployeeDetails() throws SQLException {
//
//        // Set up test data
//        EmployeeAddress permanentAddress = new EmployeeAddress("shree veeramathe", "jodukatte", "karkala", "karnataka", 567890);
//        EmployeeAddress temporaryAddress = new EmployeeAddress("prasanna", "mailasandra", "karkala", "karnataka", 876543);
//        Employee employee = new Employee("prashanth", "d", "karkala", 444, 7896543212L, "prash@gmail.com", permanentAddress, temporaryAddress);
//
//        List<Employee> mockEmployees = new ArrayList<>();
//        mockEmployees.add(employee);
//
//        // Mock the App class
//        App mockApp = mock(App.class);
//
//        // Mock the behavior of displayEmployeeDetails method
//        when(mockApp.displayEmployeeDetails()).thenReturn(mockEmployees);
//
//        // Call the method under test
//        List<Employee> result = mockApp.displayEmployeeDetails();
//
//        // Verify that the method returns the expected list of employees
//        verify(mockApp).displayEmployeeDetails();
//
//        assertEquals(mockEmployees.size(), result.size());
//
//    }
//
//    @Test
//    public void testGetTemporaryPinCodes() throws SQLException {
//
//        EmployeeAddress permanentAddress = new EmployeeAddress("shree veeramathe", "jodukatte", "karkala", "karnataka", 567890);
//        EmployeeAddress temporaryAddress = new EmployeeAddress("prasanna", "mailasandra", "karkala", "karnataka", 876543);
//        Employee employee = new Employee("prashanth", "d", "karkala", 444, 7896543212L, "prash@gmail.com", permanentAddress, temporaryAddress);
//        List<Integer> mockPinCodes = new ArrayList<>();
//        mockPinCodes.add(123456);
//
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Ensure one iteration
//        when(mockResultSet.getInt("temporaryPinCode")).thenReturn(123456); // Mock pin code
//
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet); // Return mock result set
//
//        List<Integer> result = app.getTemporaryPinCodes();
//
//        // Verify that correct methods were called with correct parameters
//        verify(mockPreparedStatement, times(1)).executeQuery();
//        assertEquals(mockPinCodes, result);
//
//    }
//
//    @Test
//    public void testGetPermanentPinCodes() throws SQLException {
//        List<Integer> mockPinCodes = new ArrayList<>();
//        mockPinCodes.add(654321);
//
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Ensure one iteration
//        when(mockResultSet.getInt("permanentPinCode")).thenReturn(654321); // Mock pin code
//
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet); // Return mock result set
//
//        List<Integer> result = app.getPermanentPinCodes();
//
//        // Verify that correct methods were called with correct parameters
//        verify(mockPreparedStatement, times(1)).executeQuery();
//        assertEquals(mockPinCodes, result);
//    }
//}
//
//
//
