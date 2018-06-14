import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by stepygin on 14.06.2018.
 */
public class Task_1_Main {
    Task_1_Main task;
    public static void main(String[] args) {

        int[] data = {1, 2, 4, 4, 2, 3, 4, 1, 7};

        Task_1_Main task1 = new Task_1_Main();
        System.out.println(Arrays.toString(task1.getArray(data)));
        System.out.println(Arrays.toString(task1.getArray(new int[]{1,2,4,4,2,3,4,1,7,3,4,6,7,5,8})));

    }

    public int[] getArray(int[] array){

        int pos = 0;
        for(int i = array.length-1; i > 0; i--){
            if(array[i] == 4){
                pos = i+1;
                break;
            }
        }
        return Arrays.copyOfRange(array, pos, array.length);
    }

    @Before
    public void startTest(){
        task = new Task_1_Main();
    }

    @Test
    public void testArray(){
        Assert.assertArrayEquals(new int[]{6,7,5,8} , task.getArray(new int[]{1,2,4,4,2,3,4,1,7,3,4,6,7,5,8}));
        Assert.assertArrayEquals(new int[]{1,7} , task.getArray(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }


    @Test(expected = RuntimeException.class)
    public void testArray2(){
        //проверка с заведомо ложным результатом для выброса эксепшена
        try {
            Assert.assertArrayEquals(new int[]{1, 7}, new Task_1_Main().getArray(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 4, 7}));
        }catch (AssertionError e){
            throw new RuntimeException();
        }
    }
}
