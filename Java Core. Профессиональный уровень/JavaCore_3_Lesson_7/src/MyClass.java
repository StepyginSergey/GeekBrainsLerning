import java.lang.reflect.Method;


public class MyClass {
    @MarkingAnnotation
    public void markedMethod() {
        System.out.println("Java");
    }

    public static void main(String[] args) {
        Method[] methods = MyClass.class.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(MarkingAnnotation.class) != null) {
                System.out.println(o);
            }
        }

        /*            for ( Method m :  methods) {
                System.out.print(m.getName() + " - ");
                for(Annotation a : m.getAnnotations()){
                    System.out.println(a.annotationType().getName());
                }

            }
*/
    }
}