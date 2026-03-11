public class LoopsDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Execute repeated logic.\n");

        forLoopDemo();
        whileLoopDemo();
        doWhileLoopDemo();
        breakAndContinueDemo();
    }

    // for: best when you know how many iterations you need.
    public static void forLoopDemo() {
        System.out.println("=== for ===");

        for (int i = 1; i <= 5; i++) {
            System.out.println("for iteration: " + i);
        }

        System.out.println("for demo finished.\n");
    }

    // while: repeats while condition is true.
    public static void whileLoopDemo() {
        System.out.println("=== while ===");

        int count = 1;
        while (count <= 3) {
            System.out.println("while count: " + count);
            count++;
        }

        System.out.println("while demo finished.\n");
    }

    // do-while: runs at least one time before checking condition.
    public static void doWhileLoopDemo() {
        System.out.println("=== do-while ===");

        int value = 5;
        do {
            System.out.println("do-while value: " + value);
            value++;
        } while (value < 5);

        System.out.println("do-while demo finished.\n");
    }

    // break exits loop early; continue skips current iteration.
    public static void breakAndContinueDemo() {
        System.out.println("=== break, continue ===");

        System.out.println("break example:");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                System.out.println("Stop at i = " + i);
                break;
            }
            System.out.println("i = " + i);
        }

        System.out.println("\ncontinue example:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println("i = " + i);
        }

        System.out.println("break/continue demo finished.\n");
    }
}
