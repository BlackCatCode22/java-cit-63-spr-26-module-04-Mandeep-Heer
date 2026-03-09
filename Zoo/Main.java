package Zoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animalList = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<>();
        ArrayList<String> availableNames = new ArrayList<>();

        try {
            File namesFile = new File("src/Zoo/animalNames.txt");
            Scanner namesScanner = new Scanner(namesFile);
            while (namesScanner.hasNextLine()) {
                String nameLine = namesScanner.nextLine();
                if (!nameLine.trim().isEmpty()) {
                    availableNames.add(nameLine.trim());
                }
            }
            namesScanner.close();
            File arrivingFile = new File("src/Zoo/arrivingAnimals.txt");
            Scanner arrivingScanner = new Scanner(arrivingFile);

            while (arrivingScanner.hasNextLine()) {
                String line = arrivingScanner.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] commaParts = line.split(", ");
                String[] words = commaParts[0].split(" ");
                int age = Integer.parseInt(words[0]);
                String species = words[4].toLowerCase();
                String animalName = "Unknown";
                if (!availableNames.isEmpty()) {
                    animalName = availableNames.remove(0);
                }
                Animal newAnimal = null;
                if (species.equals("hyena")) {
                    newAnimal = new Hyena(animalName, age);
                } else if (species.equals("lion")) {
                    newAnimal = new Lion(animalName, age);
                } else if (species.equals("tiger")) {
                    newAnimal = new Tiger(animalName, age);
                } else if (species.equals("bear")) {
                    newAnimal = new Bear(animalName, age);
                }
                if (newAnimal != null) {
                    animalList.add(newAnimal);
                    speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                }
            }
            arrivingScanner.close();
            System.out.println("Successfully processed all input files!");
            FileWriter fileWriter = new FileWriter("src/Zoo/newAnimals.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("--- Zoo Population Report ---");

            for (Animal a : animalList) {
                printWriter.println(a.getSpecies() + " Habitat:");
                printWriter.println("Name: " + a.getName() + ", Age: " + a.getAge() + ", Species: " + a.getSpecies());
            }

            printWriter.println("\n--- Total Species Counts ---");


            for (String key : speciesCount.keySet()) {
                printWriter.println(key + ": " + speciesCount.get(key));
            }

            printWriter.close();
            System.out.println("Successfully created newAnimals.txt report!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find an input file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Could not create the output file.");
            e.printStackTrace();
        }
    }
}