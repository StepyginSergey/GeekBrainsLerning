import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by stepygin on 14.06.2018.
 */
public class Task_2_Main {

    Task_2_Main task;
    public boolean find(int[] array){

        boolean four = false;
        boolean one = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) one = true;
            if (array[i] == 4) four = true;
        }

        if (one && four) return true;

        return false;
    }

    @Before
    public void start(){
        task = new Task_2_Main();
    }

    @Test
    public void testFind(){
        Assert.assertTrue(task.find(new int[]{2,3,5,7,1,5,4}));
    }
    @Test
    public void testFind2(){
        Assert.assertFalse("Array uncorrect!", task.find(new int[]{2,3,5,7,5,4}));
    }

}
