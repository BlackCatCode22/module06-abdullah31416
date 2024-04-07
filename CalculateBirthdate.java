import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Animal {
    private int age;
    private String birthSeason;

    public Animal(int age, String birthSeason) {
        this.age = age;
        this.birthSeason = birthSeason;
    }

    // Getters
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

public class BirthdayCalculator {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        int currentYear = 2024; // Change this to the current year

        // Read data from the arrivingAnimals.txt file and create Animal objects
        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int age = Integer.parseInt(parts[0].split(" ")[0]); // Extract age
                String birthSeason = parts[1].split(" ")[3]; // Extract birth season
                Animal animal = new Animal(age, birthSeason);
                animals.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate and print birthdays for each animal
        for (Animal animal : animals) {
            String birthdate = animal.calculateBirthdate(currentYear);
            System.out.println("Age: " + animal.getAge() + ", Birth Season: " + animal.getBirthSeason() + ", Birthday: " + birthdate);
        }
    }
}
