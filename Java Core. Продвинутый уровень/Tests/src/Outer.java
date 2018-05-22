/**
 * Created by stepygin on 21.05.2018.
 */
 class Outer {
    private static int oInt = 0;

    public  class Inner {

        private int iInt = 1;

        void summ(){
            iInt = iInt + oInt;
            Object object = new Object();
        }


    }




}
