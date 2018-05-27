public class MainClass {

    public static void main(String[] args) {

        Integer inums[] = { 1 , 2 , 3 , 4 , 5 };
        //Double dnums[] = { 1.1 , 2.2 , 3.3 , 4.4 , 5.5 };
        Stats<Integer> iob = new Stats<Integer>(inums);

       /* swap(iob, 2, 4);*/

        MainClass m = new MainClass();
        m.swap(iob, 2, 4);

    }


    public void swap(Stats array, int from, int to){
        if(to > array.getLen() || from > array.getLen()){
            System.out.println("Одна из указанных позиций выходит за пределы массива.");
            System.out.println("Они должны быть меньше " + array.getLen());
        }
        if(to < 0 || from < 0){
            System.out.println("Одна из указанных позиций выходит за пределы массива.");
            System.out.println("Они должны быть больше 0");
        }
    }

    public static class Stats < T > {
        private T[] nums;

        public Stats(T[] о) {
            nums = о;
        }

        public int getLen(){
            return nums.length;
        }
    }
}
