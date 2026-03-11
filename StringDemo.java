public class StringDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Learn how Java handles text.\n");

        stringClassDemo();
        immutabilityDemo();
        commonMethodsDemo();
    }

    // String class: used to store text data.
    public static void stringClassDemo() {
        System.out.println("=== String Class ===");

        String message = "Hello Java";
        String name = "Alice";

        System.out.println("message = " + message);
        System.out.println("name = " + name + "\n");
    }

    // Immutability: String objects cannot be changed after creation.
    public static void immutabilityDemo() {
        System.out.println("=== Immutability ===");

        String original = "java";
        String upper = original.toUpperCase();

        System.out.println("original = " + original);
        System.out.println("upper = " + upper);
        System.out.println("original after toUpperCase() = " + original);
        System.out.println("Note: original did not change because String is immutable.\n");
    }

    // Common String methods.
    public static void commonMethodsDemo() {
        System.out.println("=== Common Methods ===");

        String text = "Hello, Java!";

        int length = text.length();
        String sub = text.substring(7, 11); // "Java"

        String a = "java";
        String b = "java";
        String c = "JAVA";

        boolean equalsSameCase = a.equals(b);
        boolean equalsDifferentCase = a.equals(c);

        String upper = a.toUpperCase();
        String lower = c.toLowerCase();

        System.out.println("text = " + text);
        System.out.println("length() = " + length);
        System.out.println("substring(7, 11) = " + sub);
        System.out.println("\"java\".equals(\"java\") = " + equalsSameCase);
        System.out.println("\"java\".equals(\"JAVA\") = " + equalsDifferentCase);
        System.out.println("toUpperCase() = " + upper);
        System.out.println("toLowerCase() = " + lower + "\n");
    }
}
