package junit.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    @Before
    public void initiate(){
        myNumbers = Arrays.asList(12,10,5,4,9);
    }

    @BeforeClass
    public static void verify(){
        System.out.println(myNumbers);
    }
    @Test
    public void readTest(){
        Assert.assertTrue(myNumbers.size()<0);
    }
}
