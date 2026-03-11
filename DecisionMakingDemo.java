public class DecisionMakingDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Control program flow.\n");

        ifDemo();
        ifElseDemo();
        elseIfDemo();
        switchDemo();
    }

    // if: run a block only when condition is true.
    public static void ifDemo() {
        System.out.println("=== if ===");

        int age = 20;
        if (age >= 18) {
            System.out.println("You are an adult.");
        }

        System.out.println("if demo finished.\n");
    }

    // if-else: choose one of two blocks.
    public static void ifElseDemo() {
        System.out.println("=== if-else ===");

        int number = 7;
        if (number % 2 == 0) {
            System.out.println(number + " is even.");
        } else {
            System.out.println(number + " is odd.");
        }

        System.out.println("if-else demo finished.\n");
    }

    // else-if: test multiple conditions in order.
    public static void elseIfDemo() {
        System.out.println("=== else-if ===");

        int score = 82;
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }

        System.out.println("else-if demo finished.\n");
    }

    // switch: select one branch based on exact value.
    public static void switchDemo() {
        System.out.println("=== switch ===");

        int day = 3;
        String dayName;

        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid day";
        }

        System.out.println("Day " + day + " is " + dayName + ".");
        System.out.println("switch demo finished.\n");
    }
}
