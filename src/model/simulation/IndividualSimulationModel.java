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
    private String OutputToDisplay;

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void addIndividual(String species, String name) throws ReflectiveOperationException {

        for (Class<? extends Individual> subClass : availableClasses) {
            String className = subClass.getSimpleName();
            if (className.equals(species)) {
                Stats stats = new Stats(100, 50, 50);
                Individual individual = subClass.getDeclaredConstructor(String.class, String.class,
                    String.class, Stats.class).newInstance(name, name, species, stats);
                getIndividuals().add(individual);
                setOutputToDisplay("Individual " + name + " added successfully\n"
                + name + " : " + individual.getStats());
                notifyObservers();
            }
        }
    }

    @Override
    public void simulateAction(String firstIndividualName, String action) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);

        if (firstIndividual == null) {
            return;
        }

        boolean actionIsSuccessful = firstIndividual.performAction(action);
        if (!actionIsSuccessful) {
            setOutputToDisplay("Action failed between " + firstIndividualName);
            notifyObservers();
            return;
        }
        setOutputToDisplay(
            "Action successful between " + firstIndividualName + "\n" + firstIndividualName + " : "
                + firstIndividual.getStats() + "\n");
        notifyObservers();
    }

    @Override
    public void simulateAction(String firstIndividualName, String action,
        String secondIndividualName) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);
        Individual secondIndividual = getIndividualByName(secondIndividualName);

        if (firstIndividual == null || secondIndividual == null) {
            return;
        }

        boolean actionIsSuccessful = firstIndividual.performAction(action, secondIndividual);
        if (!actionIsSuccessful) {
            setOutputToDisplay(
                "Action failed between " + firstIndividualName + " and " + secondIndividualName);
            notifyObservers();
            return;
        }
        setOutputToDisplay(
            "Action successful between " + firstIndividualName + " and " + secondIndividualName
                + "\n" + firstIndividualName + " : " + firstIndividual.getStats() + "\n"
                + secondIndividualName + " : " + secondIndividual.getStats());
        notifyObservers();
    }

    public Individual getIndividualByName(String name) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(name)) {
                return individual;
            }
        }
        return null;
    }

    public void setOutputToDisplay(String OutputToDisplay) {
        this.OutputToDisplay = OutputToDisplay;
    }

    public String getOutputToDisplay() {
        return OutputToDisplay;
    }

    @Override
    public void updateIndividual(String id,String life, String food, String water) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(id)) {
                individual.setStats(new Stats(Float.parseFloat(life), Float.parseFloat(food),
                    Float.parseFloat(water)));
                setOutputToDisplay("Individual " + id + " updated successfully\n"
                + id + " : " + individual.getStats());
                notifyObservers();
                return;
            }
        }
        setOutputToDisplay("Individual " + id + " not found\n");
        notifyObservers();

    }

    @Override
    public void deleteIndividual(String id) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(id)) {
                individuals.remove(individual);
                setOutputToDisplay("Individual " + id + " deleted successfully\n");
                notifyObservers();
                return;
            }
        }
        setOutputToDisplay("Individual " + id + " not found\n");
        notifyObservers();
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
