import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static java.util.Objects.nonNull;

public class Manager implements Runnable {

    private ArrayList<Person> awaitables;
    private ArrayList<Elevator> elevators = new ArrayList<>();

    public Manager(ArrayList<Person> awaitables) {
        this.awaitables = awaitables;
        elevators.add(new Elevator(220));
        elevators.add(new Elevator(320));
        elevators.add(new Elevator(600));
    }

    @Override
    public void run() {
        Person readyPassenger = null;

        while (true) {
            try {
                readyPassenger = awaitables.get(0);
            } catch (IndexOutOfBoundsException e) {
            }

            if (nonNull(readyPassenger)) {
                Elevator readyElevator = null;

                for (Elevator elevator : elevators) {
                    elevator.move();

                    if (isElevatorAbleToTake(elevator, readyPassenger)) {
                        readyElevator = elevator;
                    }

                    if (nonNull(readyElevator)) {
                        if (readyElevator.addPassenger(readyPassenger)) {
                            awaitables.remove(readyPassenger);
                            break;
                        } else {
                            continue;
                        }
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        System.out.println("\n");
                    }
                }
            }
        }
    }

    private boolean isElevatorAbleToTake(Elevator elevator, Person readyPassenger) {
        return (elevator.getTo() == 0)
            || (elevator.getFrom() < elevator.getTo()
            && readyPassenger.getFrom() < readyPassenger.getTo()
            && readyPassenger.getFrom() >= elevator.getCurFloor())
            || (elevator.getFrom() > elevator.getTo()
            && readyPassenger.getFrom() > readyPassenger.getTo()
            && readyPassenger.getFrom() <= elevator.getCurFloor());
    }
}
