package src.model.simulation;

import java.util.Random;
import src.model.individual.Dog;
import src.model.individual.Dog.Gender;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Subject;

public class DogSimulationModel extends IndividualSimulationModel  {

    @Override
    public void addIndividual(String name) {
        Random random = new Random();
        Gender gender = random.nextBoolean() ? Gender.male : Gender.female;
        Dog dog = new Dog(name,name, new Stats(100, 50, 50),gender); // Default gender
        getIndividuals().add(dog);
        notifyObservers(); // Notify observers after adding
    }

    public void updateIndividual(String id, Individual individual) {

    }

    @Override
    public boolean supports(String species) {
        return "Dog".equals(species);
    }
}