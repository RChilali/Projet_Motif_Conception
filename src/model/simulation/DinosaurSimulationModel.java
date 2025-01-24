package src.model.simulation;

import src.model.individual.Dinosaur;
import src.model.stats.Stats;
import observer.Subject;
import observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class DinosaurSimulationModel implements Subject {
    private List<Dinosaur> dinosaurs = new ArrayList<>();
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

    // Add a new dinosaur to the simulation
    public void addDinosaur(String name) {
        Stats stats = new Stats(100, 50, 50); // Default stats
        Dinosaur dino = new Dinosaur("dn" + System.currentTimeMillis(), name, stats, false); // Default canFly
        dinosaurs.add(dino);
        notifyObservers(); // Notify observers after adding
    }

    // Get all dinosaurs in the simulation
    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }
}