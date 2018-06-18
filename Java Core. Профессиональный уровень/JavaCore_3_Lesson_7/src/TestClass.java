

/**
 * Created by stepygin on 18.06.2018.
 */
public class TestClass {

    private String name;
    private String status;

    public TestClass(String name, String status){
        this.name = name;
        this.status = status;
    }
    public TestClass(){
        this.name = "TempName";
        this.status = "TempStatus";
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int arifmetic(int var1, int var2, String operator){
        int result = 0;
        if(operator.equals("+")){
            result = var1 + var2;
        }else if (operator.equals("-")){
            result = var1 - var2;
        }
        return result;
    }


}
