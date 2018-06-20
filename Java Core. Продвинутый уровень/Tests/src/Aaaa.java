/**
 * Created by stepygin on 20.06.2018.
 */
public class Aaaa {
    private String name;

    static{
        try {
            double par = 10 / 0;
        }catch (ArithmeticException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Aaaa a= new Aaaa();


    }

    public static void a() throws Exception {
        {
            try {
                double par = 10 / 0;
            }catch (ArithmeticException e){
                throw new Exception();
            }
        }
    }

    public void b() throws Exception {
        {
            try {
                double par = 10 / 0;
            }catch (ArithmeticException e){
                throw new Exception();
            }
        }
    }
}
