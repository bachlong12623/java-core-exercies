public class MethodsDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Write reusable code.\n");

        methodDeclarationDemo();
        parametersDemo();
        returnTypesDemo();
        staticVsInstanceDemo();
    }

    // Method declaration: access + static(optional) + return type + name + parameters.
    public static void methodDeclarationDemo() {
        System.out.println("=== Method Declaration ===");

        sayHello();
        System.out.println("Method declaration demo finished.\n");
    }

    // A basic declared method with no parameters and no return value.
    public static void sayHello() {
        System.out.println("Hello from sayHello().");
    }

    // Parameters: input values passed into methods.
    public static void parametersDemo() {
        System.out.println("=== Parameters ===");

        greetUser("Alice");
        greetUser("Bob");

        int sum = add(5, 7);
        System.out.println("add(5, 7) = " + sum);
        System.out.println("Parameters demo finished.\n");
    }

    public static void greetUser(String name) {
        System.out.println("Welcome, " + name + "!");
    }

    public static int add(int a, int b) {
        return a + b;
    }

    // Return types: methods can return different data types.
    public static void returnTypesDemo() {
        System.out.println("=== Return Types ===");

        int squareValue = square(4);
        boolean evenCheck = isEven(11);
        String message = buildMessage("Java", 8);

        System.out.println("square(4) = " + squareValue);
        System.out.println("isEven(11) = " + evenCheck);
        System.out.println("buildMessage(\"Java\", 8) = " + message);
        System.out.println("Return types demo finished.\n");
    }

    public static int square(int number) {
        return number * number;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static String buildMessage(String language, int level) {
        return "Learning " + language + " at level " + level;
    }

    // static vs instance methods.
    public static void staticVsInstanceDemo() {
        System.out.println("=== static vs instance methods ===");

        // Static method call: no object needed.
        int maxValue = Math.max(12, 25);
        System.out.println("Static call Math.max(12, 25) = " + maxValue);

        // Instance method call: object is required.
        MethodsDemo demo = new MethodsDemo();
        String fullName = demo.combineNames("Nguyen", "An");
        System.out.println("Instance call combineNames(...) = " + fullName);

        System.out.println("static vs instance demo finished.\n");
    }

    // Instance method (non-static).
    public String combineNames(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
