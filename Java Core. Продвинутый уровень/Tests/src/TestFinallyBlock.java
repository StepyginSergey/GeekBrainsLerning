/**
 * Created by stepygin on 20.06.2018.
 */
public class TestFinallyBlock {


    public static void main(String[] args) {
        System.out.println(new TestFinallyBlock().a());
    }


    public int a(){
        try{
            System.exit(0);
            return 0;
        }
        finally {
            return 10;
        }
    }

}
