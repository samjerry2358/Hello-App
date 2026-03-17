public class HelloApp {
    public static void main(String[] args) {

        String name;

        // Check if arguments are provided
        if (args.length == 0) {
            name = "Guest"; // Default value
        } else {
            // Build string from multiple arguments
            StringBuilder nameBuilder = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                nameBuilder.append(args[i]);

                // Add comma between names
                if (i < args.length - 1) {
                    nameBuilder.append(", ");
                }
            }

            name = nameBuilder.toString();
        }

        // Output
        System.out.println("Hello, " + name + "!");
    }
}