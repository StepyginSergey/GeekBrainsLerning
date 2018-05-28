import java.util.ArrayList;

/**
 * Created by stepygin on 28.05.2018.
 */
public class Box<T extends Fruit> {
    private T fruitBox;
    private float weight = 0.0f;
    private ArrayList<T> content;

    public Box(T fruit) {
        this.fruitBox = fruit;
        this.content = new ArrayList<T>();
    }

    public void showType(){
        System.out.println("Type fruit: " + fruitBox.getClass().getName());
    }
    //Метод добавления фруктов
    public void addFruit(T fruit){
        this.weight += fruit.getWeight();
        content.add(fruit);
    }
    //Метод возвращает вес коробки
    public float getWeight(){
        //System.out.println("Mass: " + weight);
        return weight;
    }
    //метод сравнивает вес двух коробок. возможно сравнивать по весу коробки с разными фруктами
    public boolean compare(Box<? extends Fruit> box){
        if(this.getWeight() == box.getWeight()){
            return true;
        }
        return false;
    }
    // метод пересыпает содержимое одной коробки в другую
    public void pepper(Box<T> box){
        this.content.addAll(box.content);
        this.weight += box.getWeight();
        box.content.clear();
        box.weight = 0.0f;
    }
}
