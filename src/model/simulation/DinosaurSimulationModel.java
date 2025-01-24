package src.model.simulation;

import src.model.individual.Dinosaur;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Observer;
import src.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class DinosaurSimulationModel implements Subject, SimulationModel {
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

    @Override
    public List<? extends Individual> getIndividuals() {
        return dinosaurs;
    }

    @Override
    public void addIndividual(String name) {
        Stats stats = new Stats(100, 50, 50); // Default stats
        Dinosaur dino = new Dinosaur("dn" + System.currentTimeMillis(), name, stats, false); // Default canFly
        dinosaurs.add(dino);
        notifyObservers(); // Notify observers after adding
    }

    @Override
    public boolean supports(String species) {
        return "Dinosaur".equals(species);
    }

    @Override
    public Individual getIndividualById(String id) {
        for (Individual individual : dinosaurs) {
            if (individual.getId().equals(id)) {
                return individual;
            }
        }
        return null;
    }
}