package Zoo;

public class Tiger extends Animal {
    public Tiger(String name, int age) {
        super(name, age, "Tiger");
    }
    public String getAnimalSound() {
        return "Growls";
    }
}