import java.util.ArrayList;
import java.util.List;

class Animal {
    private String id;
    private String name;
    private int age;
    private String birthSeason;
    private String species;

    public Animal(String id, String name, int age, String birthSeason, String species) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthSeason = birthSeason;
        this.species = species;
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

    public String getSpecies() {
        return species;
    }
}

class Habitat {
    private String name;
    private List<Animal> animals;

    public Habitat(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}

public class AnimalHabitatOrganizer {
    public static void main(String[] args) {
        // Create habitats
        Habitat savanna = new Habitat("Savanna");
        Habitat jungle = new Habitat("Jungle");
        Habitat arctic = new Habitat("Arctic");
        Habitat forest = new Habitat("Forest");

        // Create animals and assign them to habitats
        List<Animal> animals = createAnimals();

        for (Animal animal : animals) {
            switch (animal.getSpecies().toLowerCase()) {
                case "hyena":
                case "lion":
                    savanna.addAnimal(animal);
                    break;
                case "tiger":
                    jungle.addAnimal(animal);
                    break;
                case "bear":
                    arctic.addAnimal(animal);
                    break;
                default:
                    forest.addAnimal(animal);
                    break;
            }
        }

        // Print animals in each habitat
        printHabitatDetails(savanna);
        printHabitatDetails(jungle);
        printHabitatDetails(arctic);
        printHabitatDetails(forest);
    }

    // Create sample animals
    private static List<Animal> createAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("H1", "Simba", 5, "Spring", "Lion"));
        animals.add(new Animal("H2", "Baloo", 8, "Fall", "Bear"));
        animals.add(new Animal("H3", "Shere Khan", 6, "Summer", "Tiger"));
        animals.add(new Animal("H4", "Scar", 10, "Winter", "Hyena"));
        animals.add(new Animal("H5", "Mufasa", 12, "Spring", "Lion"));
        animals.add(new Animal("H6", "Bagheera", 7, "Fall", "Bear"));
        animals.add(new Animal("H7", "Rajah", 4, "Summer", "Tiger"));
        animals.add(new Animal("H8", "Shenzi", 9, "Winter", "Hyena"));
        return animals;
    }

    // Print habitat details
    private static void printHabitatDetails(Habitat habitat) {
        System.out.println("Habitat: " + habitat.getName());
        System.out.println("Animals:");
        for (Animal animal : habitat.getAnimals()) {
            System.out.println("ID: " + animal.getId() + ", Name: " + animal.getName() + ", Species: " + animal.getSpecies() + ", Age: " + animal.getAge() + ", Birth Season: " + animal.getBirthSeason());
        }
        System.out.println();
    }
}
