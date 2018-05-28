import java.util.ArrayList;
import java.util.List;

public class Task_1_2_Main {

    public static void main(String[] args) {

        Integer inums[] = {1, 2, 3, 4, 5, 6};
        GArray<Integer> obj = new GArray<Integer>(inums);

        Integer[] newNums = obj.swap(2, 4);
        String arr = "";
        for (int i = 0; i < newNums.length; i++) {
            arr += newNums[i] + ", ";
        }
        System.out.println(arr);


        Double dnums[] = {1.0, 2.2, 3.3, 4.4, 5.5, 6.6};
        GArray<Double> dObj = new GArray<>(dnums);
        System.out.println(dObj.nums.getClass());
        ArrayList<Double> al = dObj.transformToArrayList();
        System.out.println(al.getClass());
    }



    public static class GArray<T> {
        private T[] nums;

        public GArray(T[] о) {
            nums = о;
        }

        public int getLen() {
            return nums.length;
        }

        //Метод меняет два элемента массива местами. Надо указать из какой позиции в какую надо поместить элемент
        public T[] swap(int from, int to) {
            int lFrom = from - 1;
            int lTo = to - 1;
            if (lTo > nums.length || lFrom > nums.length) {
                System.out.println("Одна из указанных позиций выходит за пределы массива.");
                System.out.println("Они должны быть меньше " + nums.length);
            }
            if (lTo < 0 || lFrom < 0) {
                System.out.println("Одна из указанных позиций выходит за пределы массива.");
                System.out.println("Они должны быть больше 0");
            }
            T positionFrom = nums[lFrom];
            T positionTo = nums[lTo];
            ArrayList<T> tempList = new ArrayList<T>();
            for (int i = 0; i < nums.length; i++) {
                if (i == lFrom){
                    tempList.add(positionTo);
                }else if (i == lTo){
                    tempList.add(positionFrom);
                }else{
                    tempList.add(nums[i]);
                }
            }

            return tempList.toArray(nums);
        }

        //Преобразование любого массива к ArrayList
        public ArrayList<T> transformToArrayList(){
            ArrayList<T> res = new ArrayList<T>();
            for (int i = 0; i < nums.length; i++){
                res.add(nums[i]);
            }
            return res;
        }
    }
}
