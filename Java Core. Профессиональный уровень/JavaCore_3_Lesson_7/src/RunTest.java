import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by stepygin on 18.06.2018.
 */
public class RunTest {

    static UITest obj;
    static boolean before = false;
    static boolean after = false;

    public static void main(String[] args) {

        try {
            Class test = UITest.class;
            Object obj = test.newInstance();
            Method[] methods = test.getMethods();

            for(Method m : methods){
                if(m.getAnnotation(BeforeSuite.class) != null){
                    if (before) throw new RuntimeException();
                    before = true;
                    m.invoke(obj);
                }
            }

            for(Method m : methods){
                if(m.getAnnotation(Test.class) != null) {
                    if( m.getAnnotation(MarkingAnnotation.class) != null){
                        MarkingAnnotation ma = m.getAnnotation(MarkingAnnotation.class);
                        System.out.print("priority " + ma.value() + " method - ");
                    }
                    m.invoke(obj);
                }
            }

            for(Method m : methods){
                if(m.getAnnotation(AfterSuite.class) != null){
                    if (after) throw new RuntimeException();
                    after = true;
                    m.invoke(obj);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
