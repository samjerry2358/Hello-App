public class HelloApp {
    public static void main(String[] args) {

        String names = "World"; // Default value

        // If arguments are provided
        if (args.length > 0) {
            names = String.join(", ", args);
        }

        // Output
        System.out.println("Hello, " + names + "!");
    }
}