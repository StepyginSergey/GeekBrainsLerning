import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by stepygin on 18.06.2018.
 */
public class UITest {
    private TestClass test;

    @BeforeSuite
    public void startTest(){
        test = new TestClass("A", "Run");
        System.out.println("Start test");
    }

    @Test
    @MarkingAnnotation(value = 3)
    public void testArifmetic_1(){
        Assert.assertEquals(10, test.arifmetic(5 , 5, "+"));
        System.out.println("testArifmetic_1");
    }

    @Test
    @MarkingAnnotation(value = 5)
    public void testArifmetic_2(){
        Assert.assertEquals(12, test.arifmetic(7 , 5, "+"));
        System.out.println("testArifmetic_2");
    }

    @Test
    @MarkingAnnotation(value = 10)
    public void testArifmetic_3(){
        Assert.assertEquals(0, test.arifmetic(5 , 5, "-"));
        System.out.println("testArifmetic_3");
    }
    @Test
    public void testArifmetic_4(){
        Assert.assertEquals(10, test.arifmetic(15 , 5, "-"));
        System.out.println("testArifmetic_4");
    }
    @Test
    @MarkingAnnotation(value = 7)
    public void testArifmetic_5(){
        Assert.assertEquals(11, test.arifmetic(16 , 5, "-"));
        System.out.println("testArifmetic_5");
    }

    @AfterSuite
    public void finishTest(){
        test = null;

        System.out.println("Finish test");
    }

}
