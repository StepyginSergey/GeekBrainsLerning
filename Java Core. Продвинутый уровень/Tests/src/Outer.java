/**
 * Created by stepygin on 21.05.2018.
 */
 class Outer {
    private static int oInt = 0;

    public  class Inner {

        private int iInt = 1;
/*
        void summ(){
            iInt = iInt + oInt;
            Object object = new Object();
        }

        void add(int a){
            System.out.println(a);
        }*/
    }

    public void a(){
        int x = 10;

        new Inner(){
            void c(){
                System.out.println(oInt);
            }
        }.c();
    }

    public static void main(String[] args) {
        Outer o = new Outer();
        o.a();
    }
}
