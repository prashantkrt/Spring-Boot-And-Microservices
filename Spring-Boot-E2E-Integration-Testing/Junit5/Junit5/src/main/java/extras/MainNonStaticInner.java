package extras;

class OuterClass {
    // Non-static inner class
    class InnerClass {
        void display() {
            System.out.println("Inside Non-Static Inner Class");
        }
    }

    void outerMethod() {
        // To create an instance of the non-static inner class, you need an instance of the outer class
        InnerClass innerObj = new InnerClass();
        innerObj.display();
    }
}

public class MainNonStaticInner {
    public static void main(String[] args) {
        // To create an instance of the non-static inner class, first create an outer class object
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass(); // Syntax for non-static inner class
        inner.display();

        // Alternatively, using outer class method
        outer.outerMethod();
    }
}
