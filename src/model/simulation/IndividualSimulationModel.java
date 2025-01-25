package src.model.simulation;

import src.model.individual.Dinosaur;
import src.model.individual.Dog;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Observer;
import src.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class IndividualSimulationModel implements Subject, SimulationModel {
    private List<Individual> individuals = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private List<Class<? extends Individual>> availableClasses = List.of(Dog.class, Dinosaur.class);

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void addIndividual(String species, String name) throws ReflectiveOperationException {

        for (Class<? extends Individual> subClass : availableClasses) {
            String className = subClass.getSimpleName();
            if (className.equals(species)) {
                Stats stats = new Stats(100, 50, 50);
                Individual individual = subClass
                        .getDeclaredConstructor(String.class, String.class, String.class, Stats.class)
                        .newInstance(name, name, species, stats);
                getIndividuals().add(individual);
                notifyObservers();
            }
        }
    }

    @Override
    public boolean simulateAction(String firstIndividualName, String action) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);

        if (firstIndividual == null) {
            return false;
        }

        return firstIndividual.performAction(action);
    }

    @Override
    public boolean simulateAction(String firstIndividualName, String action, String secondIndividualName) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);
        Individual secondIndividual = getIndividualByName(secondIndividualName);

        if (firstIndividual == null || secondIndividual == null) {
            return false;
        }

        return firstIndividual.performAction(action, secondIndividual);
    }

    public Individual getIndividualByName(String name) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(name)) {
                return individual;
            }
        }
        return null;
    }

    @Override
    public void updateIndividual(String id, Individual individual) {

    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
