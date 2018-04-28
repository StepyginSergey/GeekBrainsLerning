package ru.geekbrains.java.core.lesson2;

public class Main {

    public static void main(String[] args) {

        String[][] array =  {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"9","1","2a","3"},
                {"4","5","6","7"}
        };

        try{
            int count = summElementArray(array);
            System.out.println("Массив прошел проверку");
            System.out.println("Сумма элементов массива равна = " + count);

        }catch(MySizeArrayException e){
        }catch(MyArrayDataException e){
        }
    }
/*

1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
ячейке лежит символ или текст вместо числа), должно быть брошено исключение
MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
3 В методе main() вызвать полученный метод, обработать возможные исключения
MySizeArrayException и MyArrayDataException и вывести результат расчета.

*/

    public static int summElementArray(String[][] array) throws MySizeArrayException, MyArrayDataException {
        int result = 0;
        int row = array.length;
        if( row > 4){
            throw new MySizeArrayException("Массив не удовлетворяет условиям. Колличество строк в массиве больше 4-х");
        }else if(row < 4){
            throw new MySizeArrayException("Массив не удовлетворяет условиям. Колличество строк в массиве меньше 4-х");
        }
        for(int i = 0; i < array.length; i++){
            if(array[i].length > 4){
                throw new MySizeArrayException("Массив не удовлетворяет условиям. Колличество столбцов в массиве больше 4-х");
            }else if(array[i].length < 4){
                throw new MySizeArrayException("Массив не удовлетворяет условиям. Колличество столбцов в массиве меньше 4-х");
            }
        }

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                try {
                    result += Integer.parseInt(array[i][j]);
                }catch(NumberFormatException e){
                    throw new MyArrayDataException("Ошибка преобразования. Не числовое значение находится в [" +
                            (i+1) + "][" + (j+1) + "] ячейке массива.");
                }
            }
        }

        return result;
    }





    static class MySizeArrayException extends Exception {

        public MySizeArrayException(String message) {
            super(message);
            System.out.println("MySizeArrayException - message = [" + message + "]");
            super.printStackTrace();
        }
    }

    static class MyArrayDataException extends Exception {

        public MyArrayDataException(String message) {
            super(message);
            System.out.println("MyArrayDataException - message = [" + message + "]");
            super.printStackTrace();
        }
    }

}
