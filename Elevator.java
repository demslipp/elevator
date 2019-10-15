import java.util.ArrayList;
import java.util.stream.Collectors;

public class Elevator {

    private Integer from = 0;
    private Integer to = 0;
    private Integer curFloor = 0;
    private Integer curWeight = 0;
    private Integer capacity;
    private ArrayList<Person> passengers = new ArrayList<>();
    private ArrayList<Integer> stops = new ArrayList<>();

    public Elevator(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return this.to;
    }

    public Integer getCurFloor() {
        return curFloor;
    }

    public boolean addPassenger(Person passenger) {
        System.out.println("Person's request: " + passenger + " capacity: " + capacity);
        if (this.curWeight + passenger.getWeight() <= this.capacity) {
            this.passengers.add(passenger);
            if (this.from == 0) {
                this.from = passenger.getFrom();
                this.to = passenger.getTo();
            }
            this.stops.add(passenger.getTo());
            if ((this.from < this.to && this.to < passenger.getTo())
                || (this.from > this.to && this.to > passenger.getTo())) {
                this.to = passenger.getTo();
            }
            this.curWeight += passenger.getWeight();
            return true;
        } else {
            System.out.println("Elevator " + this.capacity + " is overweight");
            for (Person p : this.passengers) {
                System.out.println(p);
            }
            return false;
        }
    }

    public void move() {
        if (this.from < this.to) {
            for (int i = 0; i <= this.to; i++) {
                processFloorMatching(i);
            }
        } else {
            for (int i = this.from; i >= this.to; i--) {
                processFloorMatching(i);
            }
        }
        if (this.stops.isEmpty()) {
            this.flush();
        }
    }

    private void processFloorMatching(int i) {
        this.curFloor = i;
        if (this.stops.contains(i)) {
            System.out.println("Stops at " + i);
            System.out.println(this.passengers.size());
            passengers.remove(
                passengers
                    .stream()
                    .filter(p -> p.getTo().equals(this.curFloor))
                    .collect(Collectors.toList()));
            this.stops.remove((Integer) i);
            try {
                this.curWeight -= this.passengers.get(i).getWeight();
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    private void flush() {
        this.from = to;
        this.to = 0;
        this.curWeight = 0;
    }
}
