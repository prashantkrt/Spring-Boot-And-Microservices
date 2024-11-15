package extras;

class OuterClass2{
    // Static inner class
    static class StaticInnerClass {
        void display() {
            System.out.println("Inside Static Inner Class");
        }
    }

    void outerMethod() {
        // You can create an instance of the static inner class from an outer method
        StaticInnerClass innerObj = new StaticInnerClass();
        innerObj.display();
    }
}

public class MainStaticInnerClass {
    public static void main(String[] args) {
        // You can create an instance of the static inner class without an outer class object
        OuterClass2.StaticInnerClass staticInner = new OuterClass2.StaticInnerClass();
        staticInner.display();

        // Alternatively, using outer class method
        OuterClass2 outer = new OuterClass2();
        outer.outerMethod();
    }
}
