import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Generator implements Runnable {

    private ArrayList<Person> awaitables;

    public Generator(ArrayList<Person> arr) {
        awaitables = arr;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Person person = new Person(
                random.nextInt(8) + 1,
                 random.nextInt(8) + 1,
                40 + random.nextInt(80)
            );
            if (person.getFrom().equals(person.getTo())) {
                continue;
            }
            awaitables.add(person);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("\n");
            }
        }
    }
}
