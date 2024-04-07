import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AnimalDetails {
    private int age;
    private String sex;
    private String species;
    private String color;
    private int weight;

    public AnimalDetails(int age, String sex, String species, String color, int weight) {
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.color = color;
        this.weight = weight;
    }

    // Getters
    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getSpecies() {
        return species;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}

public class AnimalDetailsExtractor {
    public static void main(String[] args) {
        List<AnimalDetails> animalDetailsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by comma and space to extract individual details
                String[] parts = line.split(", ");
                int age = Integer.parseInt(parts[0].split(" ")[0]); // Extract age
                String sex = parts[0].split(" ")[2]; // Extract sex
                String species = parts[parts.length - 1].split(" ")[2]; // Extract species
                String color = parts[3]; // Extract color
                int weight = Integer.parseInt(parts[6].split(" ")[0]); // Extract weight
                // Create an AnimalDetails object and add it to the list
                AnimalDetails animalDetails = new AnimalDetails(age, sex, species, color, weight);
                animalDetailsList.add(animalDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print extracted animal details
        for (AnimalDetails details : animalDetailsList) {
            System.out.println("Age: " + details.getAge());
            System.out.println("Sex: " + details.getSex());
            System.out.println("Species: " + details.getSpecies());
            System.out.println("Color: " + details.getColor());
            System.out.println("Weight: " + details.getWeight());
            System.out.println();
        }
    }
}
