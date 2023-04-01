package after;

public class Atom implements Robot{
    String name;

    public Atom(String name) {
        this.name = name;
    }

    @Override
    public void attack() {
        System.out.println("I have strong punch and can attack with it.");
    }

    @Override
    public void move() {
        System.out.println("I can fly.");
    }
}
