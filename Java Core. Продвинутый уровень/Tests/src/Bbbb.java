/**
 * Created by stepygin on 20.06.2018.
 */
public class Bbbb {

    public void с() throws Exception {
        try{
            System.out.println("блок try ");
            double d = 10 /0;
            System.out.println("после ошибки");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("блок finally ");
            double d = 10 /0;
        }
    }

    public static void main(String[] args) throws Exception {
        new Bbbb().с();
    }
}
