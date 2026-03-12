public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from Jenkins Pipeline!");
        System.out.println("App is running...");
        // Keep running forever
        while (true) {
            Thread.sleep(30000);
            System.out.println("Still alive...");
        }
    }

    public static int add(int a, int b) {
        return a + b;
    }
}