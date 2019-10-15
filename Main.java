import java.util.ArrayList;

public class Main {
    public static void main ( String[] args ) {
        ArrayList<Person> awaitables = new ArrayList<>();
        Runnable home = new Generator(awaitables);
        Runnable manager = new Manager(awaitables);

        new Thread(manager).start();
        new Thread(home).start();
    }
}
