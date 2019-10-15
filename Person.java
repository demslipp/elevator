public class Person {

    private Integer from;
    private Integer to;
    private Integer weight;

    public Person(Integer from, Integer to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Request from: " + from + " to: " + to + " weight: " + weight;
    }

    public Integer getFrom() {
        return this.from;
    }

    public Integer getTo() {
        return this.to;
    }

    public Integer getWeight() {
        return this.weight;
    }
}
