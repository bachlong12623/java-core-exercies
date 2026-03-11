public class ClassDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Understand object structure.\n");

        // Create objects using different constructors.
        Student s1 = new Student();
        Student s2 = new Student("Alice", "Smith", 2024, 3.8);
        s1.printInfo();
        s2.printInfo();
        // Modify object state using method.
        System.err.println("After increasing graduation year:" + s1.increaseGraduationYear());
    }
}

// Class definition: blueprint for creating Student objects.
class Student {
    // Attributes (fields): data that belongs to each object.
    String firstName;
    String lastName;
    int expectedGraduationYear;
    double gpa;

    // Constructor 1: default constructor.
    public Student() {
        // this(...) calls another constructor in the same class.
        this("Unknown", "Student", 2025, 0.0);
    }

    // Constructor 2: parameterized constructor.
    public Student(String firstName, String lastName, int expectedGraduationYear, double gpa) {
        // this.firstName means the attribute of current object.
        this.firstName = firstName;
        this.lastName = lastName;
        this.expectedGraduationYear = expectedGraduationYear;
        this.gpa = gpa;
    }

    // Method: behavior of the object.
    public void printInfo() {
        System.out.println("Student{Full Name: " + firstName + " " + lastName +
                ", Graduation Year: " + expectedGraduationYear +
                ", GPA: " + gpa + "}");
    }

    public int increaseGraduationYear() {
         expectedGraduationYear++;
         return expectedGraduationYear;
    }
}
