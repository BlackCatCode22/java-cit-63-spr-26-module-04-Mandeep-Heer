package Zoo;

public class Lion extends Animal {
    public Lion(String name, int age) {
        super(name, age, "Lion");
    }
    public String getAnimalSound() {
        return "Roars";
    }
}