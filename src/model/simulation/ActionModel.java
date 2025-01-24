package src.model.simulation;

import src.model.individual.Individual;
import src.observer.Observer;
import src.observer.Subject;

import java.util.*;

public class ActionModel implements Subject {

    Map<Individual, String> actions;

    private List<Observer> observers = new ArrayList<>();

    public ActionModel() {
        actions = new HashMap<>();
    }

    public void addAction(String id, String actionName) {
        Individual individual = getIndividualById(id);
        actions.put(individual, actionName);
        notifyObservers();
    }


    public List<String> getActions() {
        return actions.values().stream().toList();
    }

    public Individual getIndividualById(String id) {
        Set<Individual> individuals = actions.keySet();

        for (Individual i : individuals) {
            if (id.equals(i.getId())) {
                return i;
            }
        }

        return null;
    }

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
}
