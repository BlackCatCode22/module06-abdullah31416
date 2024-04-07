import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Animal {
    private String id;
    private String name;
    private int age;
    private String birthSeason;

    public Animal(String id, String name, int age, String birthSeason) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthSeason = birthSeason;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthSeason() {
        return birthSeason;
    }
}

class Hyena extends Animal {
    private static int hyenaCount = 0;

    public Hyena(String id, String name, int age, String birthSeason) {
        super(id, name, age, birthSeason);
        hyenaCount++;
    }

    // Getters
    public static int getHyenaCount() {
        return hyenaCount;
    }
}

class Lion extends Animal {
    private static int lionCount = 0;

    public Lion(String id, String name, int age, String birthSeason) {
        super(id, name, age, birthSeason);
        lionCount++;
    }

    // Getters
    public static int getLionCount() {
        return lionCount;
    }
}

class Tiger extends Animal {
    private static int tigerCount = 0;

    public Tiger(String id, String name, int age, String birthSeason) {
        super(id, name, age, birthSeason);
        tigerCount++;
    }

    // Getters
    public static int getTigerCount() {
        return tigerCount;
    }
}

class Bear extends Animal {
    private static int bearCount = 0;

    public Bear(String id, String name, int age, String birthSeason) {
        super(id, name, age, birthSeason);
        bearCount++;
    }

    // Getters
    public static int getBearCount() {
        return bearCount;
    }
}

public class UniqueIDGenerator {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Map<String, Integer> speciesCounts = new HashMap<>();
        int currentYear = 2024; // Change this to the current year

        // Read data from the arrivingAnimals.txt file and create Animal objects
        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int age = Integer.parseInt(parts[0].split(" ")[0]); // Extract age
                String birthSeason = parts[1].split(" ")[3]; // Extract birth season
                String species = parts[parts.length - 1].split(" ")[2]; // Extract species
                String id = generateUniqueID(species, speciesCounts);
                String name = ""; // You can assign names here from animalNames.txt
                Animal animal;
                switch (species.toLowerCase()) {
                    case "hyena":
                        animal = new Hyena(id, name, age, birthSeason);
                        break;
                    case "lion":
                        animal = new Lion(id, name, age, birthSeason);
                        break;
                    case "tiger":
                        animal = new Tiger(id, name, age, birthSeason);
                        break;
                    case "bear":
                        animal = new Bear(id, name, age, birthSeason);
                        break;
                    default:
                        animal = new Animal(id, name, age, birthSeason);
                        break;
                }
                animals.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print animal details
        for (Animal animal : animals) {
            System.out.println("ID: " + animal.getId() + ", Name: " + animal.getName() + ", Age: " + animal.getAge() + ", Birth Season: " + animal.getBirthSeason());
        }
    }

    // Generate unique ID based on species and count
    private static String generateUniqueID(String species, Map<String, Integer> speciesCounts) {
        int count = speciesCounts.getOrDefault(species, 0) + 1;
        speciesCounts.put(species, count);
        return species.substring(0, 1).toUpperCase() + count;
    }
}
