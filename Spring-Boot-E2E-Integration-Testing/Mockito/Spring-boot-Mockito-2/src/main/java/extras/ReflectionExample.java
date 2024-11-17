package extras;
import java.lang.reflect.Method;

public class ReflectionExample {

    // Private methods with different parameters
    private void greet() {
        System.out.println("Hello, no parameters!");
    }

    private String greetWithName(String name) {
        return "Hello, " + name + "!";
    }

    private int addNumbers(int a, int b) {
        return a + b;
    }

    private boolean isPositive(Long number) {
        return number > 0;
    }

    public static void main(String[] args) {
        try {
            // Create an instance of the class
            ReflectionExample instance = new ReflectionExample();

            // Access and invoke the `greet` method (no parameters)
            Method greetMethod = ReflectionExample.class.getDeclaredMethod("greet");
            greetMethod.setAccessible(true); // Allow access to private methods
            greetMethod.invoke(instance); // Invoke the method

            // Access and invoke the `greetWithName` method (String parameter)
            Method greetWithNameMethod = ReflectionExample.class.getDeclaredMethod("greetWithName", String.class);
            greetWithNameMethod.setAccessible(true);
            String greeting = (String) greetWithNameMethod.invoke(instance, "Prashant");
            System.out.println(greeting);

            // Access and invoke the `addNumbers` method (int parameters)
            Method addNumbersMethod = ReflectionExample.class.getDeclaredMethod("addNumbers", int.class, int.class);
            addNumbersMethod.setAccessible(true);
            int sum = (int) addNumbersMethod.invoke(instance, 5, 7);
            System.out.println("Sum: " + sum);

            // Access and invoke the `isPositive` method (Long parameter)
            Method isPositiveMethod = ReflectionExample.class.getDeclaredMethod("isPositive", Long.class);
            isPositiveMethod.setAccessible(true);
            boolean isPositiveResult = (boolean) isPositiveMethod.invoke(instance, 10L);
            System.out.println("Is positive: " + isPositiveResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
Hello, no parameters!
Hello, Prashant!
Sum: 12
Is positive: true
*/
