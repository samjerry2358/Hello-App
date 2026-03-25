public class HelloApp {
    public static void main(String[] args) {

        String name;

        // Default value if no arguments
        if (args.length == 0) {
            name = "Guest";
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            boolean first = true;

            // Enhanced for loop
            for (String n : args) {
                if (!first) {
                    nameBuilder.append(", ");
                }
                nameBuilder.append(n);
                first = false;
            }

            name = nameBuilder.toString();
        }

        // Output
        System.out.println("Hello, " + name + "!");
    }
}