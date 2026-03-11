import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println("Objective: Store and manipulate groups of data.\n");

        listArrayListDemo();
        setHashSetDemo();
        mapHashMapDemo();
        iterationTechniquesDemo();
    }

    // List (ArrayList): ordered collection, allows duplicates.
    public static void listArrayListDemo() {
        System.out.println("=== List -> ArrayList ===");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple"); // duplicate is allowed

        System.out.println("fruits = " + fruits);
        System.out.println("size = " + fruits.size());
        System.out.println("get(1) = " + fruits.get(1));

        fruits.remove("Banana");
        System.out.println("after remove(\"Banana\") = " + fruits + "\n");
    }

    // Set (HashSet): unique elements, no guaranteed order.
    public static void setHashSetDemo() {
        System.out.println("=== Set -> HashSet ===");

        Set<Integer> numbers = new HashSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(10); // duplicate is ignored

        System.out.println("numbers = " + numbers);
        System.out.println("size = " + numbers.size());
        System.out.println("contains(20) = " + numbers.contains(20));

        numbers.remove(20);
        System.out.println("after remove(20) = " + numbers + "\n");
    }

    // Map (HashMap): key-value pairs, unique keys.
    public static void mapHashMapDemo() {
        System.out.println("=== Map -> HashMap ===");

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Alice", 95); // overwrite value for same key

        System.out.println("scores = " + scores);
        System.out.println("size = " + scores.size());
        System.out.println("get(\"Alice\") = " + scores.get("Alice"));
        System.out.println("containsKey(\"Bob\") = " + scores.containsKey("Bob"));

        scores.remove("Bob");
        System.out.println("after remove(\"Bob\") = " + scores + "\n");
    }

    // Iteration techniques across List, Set, and Map.
    public static void iterationTechniquesDemo() {
        System.out.println("=== Iteration Techniques ===");

        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");

        System.out.println("for-each on List:");
        for (String color : colors) {
            System.out.println("  " + color);
        }

        Set<String> cities = new HashSet<>();
        cities.add("Hanoi");
        cities.add("Da Nang");
        cities.add("Ho Chi Minh City");

        System.out.println("\nIterator on Set:");
        Iterator<String> cityIterator = cities.iterator();
        while (cityIterator.hasNext()) {
            System.out.println("  " + cityIterator.next());
        }

        Map<String, String> capitals = new HashMap<>();
        capitals.put("Vietnam", "Hanoi");
        capitals.put("Japan", "Tokyo");
        capitals.put("France", "Paris");

        System.out.println("\nfor-each on Map entrySet:");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();
    }
}
