public class DataTypesDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Understand how Java stores and handles data.\n");

        primitiveDataTypesDemo();
        wrapperClassesDemo();
        variableDeclarationAndInitializationDemo();
        typeCastingDemo();
    }

    // Primitive data types: store raw values directly.
    public static void primitiveDataTypesDemo() {
        System.out.println("=== Primitive Data Types ===");

        int age = 25;                    // 32-bit integer
        long population = 8000000000L;   // 64-bit integer (use L suffix)
        double price = 19.99;            // 64-bit floating point
        float rating = 4.5f;             // 32-bit floating point (use f suffix)
        boolean isJavaFun = true;        // true or false
        char grade = 'A';                // single Unicode character

        System.out.println("int age = " + age);
        System.out.println("long population = " + population);
        System.out.println("double price = " + price);
        System.out.println("float rating = " + rating);
        System.out.println("boolean isJavaFun = " + isJavaFun);
        System.out.println("char grade = " + grade + "\n");
    }

    // Wrapper classes: object versions of primitive types.
    public static void wrapperClassesDemo() {
        System.out.println("=== Wrapper Classes ===");

        Integer score = 100;
        Long distance = 12000L;
        Double temperature = 36.6;

        System.out.println("Integer score = " + score);
        System.out.println("Long distance = " + distance);
        System.out.println("Double temperature = " + temperature);

        // Useful wrapper methods.
        String scoreText = "250";
        int parsedScore = Integer.parseInt(scoreText);
        System.out.println("Integer.parseInt(\"250\") = " + parsedScore + "\n");
    }

    // Variable declaration and initialization.
    public static void variableDeclarationAndInitializationDemo() {
        System.out.println("=== Variables: Declaration and Initialization ===");

        // Declaration (create variable name + type)
        int level;

        // Initialization (assign first value)
        level = 1;

        // Declaration + initialization in one line
        double progress = 75.5;

        System.out.println("level = " + level);
        System.out.println("progress = " + progress + "\n");
    }

    // Type casting: convert one type to another.
    public static void typeCastingDemo() {
        System.out.println("=== Type Casting ===");

        // Implicit casting (widening): smaller -> larger compatible type.
        int smallNumber = 42;
        double widerNumber = smallNumber;
        System.out.println("Implicit cast int -> double: " + widerNumber);

        // Explicit casting (narrowing): larger -> smaller, may lose data.
        double decimalValue = 9.78;
        int wholeNumber = (int) decimalValue;
        System.out.println("Explicit cast double -> int: " + wholeNumber);

        long bigValue = 130L;
        byte narrowedByte = (byte) bigValue; // overflow can happen
        System.out.println("Explicit cast long -> byte (possible overflow): " + narrowedByte + "\n");
    }
}
