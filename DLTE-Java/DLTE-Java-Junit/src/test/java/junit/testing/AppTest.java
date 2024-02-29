package junit.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    static List<Integer> myNumbers;
    @BeforeAll
    static void initiate(){
        myNumbers = Arrays.asList(12,10,5,4,9);
    }

    @BeforeEach
    void verify(){
        System.out.println(myNumbers);
    }
    @Test
    void readTest(){
        assertTrue(myNumbers.size()<0);
    }
}
