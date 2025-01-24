package src.model.simulation;

import src.model.individual.Dog;
import src.model.stats.Stats;
import observer.Subject;
import observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class DogSimulationModel implements Subject {
    private List<Dog> dogs = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Add a new dog to the simulation
    public void addDog(String name) {
        Stats stats = new Stats(100, 50, 50); // Default stats
        Dog dog = new Dog("d" + System.currentTimeMillis(), name, stats, 'M'); // Default gender
        dogs.add(dog);
        notifyObservers(); // Notify observers after adding
    }

    // Get all dogs in the simulation
    public List<Dog> getDogs() {
        return dogs;
    }
}