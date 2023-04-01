package after;

public class TaekwonV implements Robot{
    private String name;

    public TaekwonV(String name) {
        this.name = name;
    }

    @Override
    public void attack() {
        System.out.println("I have Missile and can attack with it");
    }

    @Override
    public void move() {
        System.out.println("I can only walk");
    }
}
