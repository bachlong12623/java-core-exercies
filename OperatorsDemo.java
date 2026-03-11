public class OperatorsDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Perform operations on data.\n");

        arithmeticOperatorsDemo();
        comparisonOperatorsDemo();
        logicalOperatorsDemo();
        assignmentOperatorsDemo();
    }

    // Arithmetic operators: +, -, *, /, %
    public static void arithmeticOperatorsDemo() {
        System.out.println("=== Arithmetic Operators ===");

        int a = 10;
        int b = 3;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b)); // integer division
        System.out.println("a % b = " + (a % b) + "\n");
    }

    // Comparison operators: ==, !=, >, <, >=, <=
    public static void comparisonOperatorsDemo() {
        System.out.println("=== Comparison Operators ===");

        int x = 7;
        int y = 10;

        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x == y: " + (x == y));
        System.out.println("x != y: " + (x != y));
        System.out.println("x > y: " + (x > y));
        System.out.println("x < y: " + (x < y));
        System.out.println("x >= y: " + (x >= y));
        System.out.println("x <= y: " + (x <= y) + "\n");
    }

    // Logical operators: &&, ||, !
    public static void logicalOperatorsDemo() {
        System.out.println("=== Logical Operators ===");

        boolean isAdult = true;
        boolean hasTicket = false;

        System.out.println("isAdult = " + isAdult + ", hasTicket = " + hasTicket);
        System.out.println("isAdult && hasTicket: " + (isAdult && hasTicket));
        System.out.println("isAdult || hasTicket: " + (isAdult || hasTicket));
        System.out.println("!hasTicket: " + (!hasTicket) + "\n");
    }

    // Assignment operators: =, +=, -=, *=, /=, %=
    public static void assignmentOperatorsDemo() {
        System.out.println("=== Assignment Operators ===");

        int score = 20;
        System.out.println("Initial score = " + score);

        score += 5;  // score = score + 5
        System.out.println("After += 5: " + score);

        score -= 3;  // score = score - 3
        System.out.println("After -= 3: " + score);

        score *= 2;  // score = score * 2
        System.out.println("After *= 2: " + score);

        score /= 4;  // score = score / 4
        System.out.println("After /= 4: " + score);

        score %= 3;  // score = score % 3
        System.out.println("After %= 3: " + score + "\n");
    }
}
