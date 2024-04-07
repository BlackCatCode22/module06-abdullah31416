import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Animal {
    private String name;
    private int age;
    private String birthSeason;

    public Animal(String name, int age, String birthSeason) {
        this.name = name;
        this.age = age;
        this.birthSeason = birthSeason;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthSeason() {
        return birthSeason;
    }

    // Calculate birthdate based on age and birth season
    public String calculateBirthdate(int currentYear) {
        // Determine the year of birth based on the current year and age
        int birthYear = currentYear - age;

        // Calculate the month and day of birth based on the birth season
        int month;
        int day;
        switch (birthSeason.toLowerCase()) {
            case "spring":
                month = 3; // March
                day = 21; // Spring equinox
                break;
            case "summer":
                month = 6; // June
                day = 21; // Summer solstice
                break;
            case "fall":
                month = 9; // September
                day = 23; // Fall equinox
                break;
            case "winter":
                month = 12; // December
                day = 21; // Winter solstice
                break;
            default:
                month = 1;
                day = 1;
                break;
        }

        // Construct and return the birthdate string
        return String.format("%04d-%02d-%02d", birthYear, month, day);
    }
}

public class BirthdayCalculatorWithNames {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<String> names = new ArrayList<>();
        int currentYear = 2024; // Change this to the current year

        // Read names from animalNames.txt
        try (BufferedReader namesReader = new BufferedReader(new FileReader("animalNames.txt"))) {
            String line;
            while ((line = namesReader.readLine()) != null) {
                String[] parts = line.split(":");
                String[] namesArray = parts[1].split(", ");
                for (String name : namesArray) {
                    names.add(name.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read data from the arrivingAnimals.txt file and create Animal objects
        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            int nameIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int age = Integer.parseInt(parts[0].split(" ")[0]); // Extract age
                String birthSeason = parts[1].split(" ")[3]; // Extract birth season
                String name = names.get(nameIndex++); // Assign name from the names list
                Animal animal = new Animal(name, age, birthSeason);
                animals.add(animal);
                // Reset nameIndex if it exceeds the size of the names list
                if (nameIndex >= names.size()) {
                    nameIndex = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate and print birthdays for each animal
        for (Animal animal : animals) {
            String birthdate = animal.calculateBirthdate(currentYear);
            System.out.println("Name: " + animal.getName() + ", Age: " + animal.getAge() + ", Birth Season: " + animal.getBirthSeason() + ", Birthday: " + birthdate);
        }
    }
}
