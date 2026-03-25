public class HelloApp {
    public static void main(String[] args) {

        String name;

        if (args.length == 0) {
            name = "World"; // Default value
        } else {
            StringBuilder nameBuilder = new StringBuilder();

            // Add all names with ", "
            for (String n : args) {
                nameBuilder.append(n).append(", ");
            }

            // Remove last ", "
            name = nameBuilder.substring(0, nameBuilder.length() - 2);
        }

        System.out.println("Hello, " + name + "!");
    }
}