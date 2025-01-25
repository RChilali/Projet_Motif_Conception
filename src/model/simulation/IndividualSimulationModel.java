package src.model.simulation;

import java.util.ArrayList;
import java.util.List;
import src.model.individual.Dog;
import src.model.individual.Dog.Gender;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Observer;
import src.observer.Subject;

public abstract class IndividualSimulationModel implements Subject, SimulationModel  {
  private List<Individual> individuals = new ArrayList<>();
  private List<Observer> observers = new ArrayList<>();

  public List<Individual> getIndividuals() {
    return individuals;
  }

  public void addIndividual(String name) {}

  public Individual getIndividualById(String id) {
    for (Individual individual : individuals) {
      if (individual.getId().equals(id)) {
        return individual;
      }
    }
    return null;
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
