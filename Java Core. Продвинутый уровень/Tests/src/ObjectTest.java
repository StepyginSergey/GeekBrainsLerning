/**
 * Created by stepygin on 09.06.2018.
 */
public class ObjectTest {
    String s;

    public ObjectTest(String str){
        this.s = str;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for(int i = 0; i < s.length(); i++){
            h += s.charAt(i);
        }
        return h;
    }



    public static void main(String[] args) {
        ObjectTest obj1 = new ObjectTest("ABC");
        ObjectTest obj2 = new ObjectTest("CBA");

        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.hashCode()== obj2.hashCode());
    }

}
