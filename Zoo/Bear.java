package Zoo;

public class Bear extends Animal {
    public Bear(String name, int age) {
        super(name, age, "Bear");
    }
    public String getAnimalSound() {
        return "Grunts";
    }
}