package src.model.simulation;

import src.model.actions.Action;
import src.model.individual.Dinosaur;
import src.model.individual.Dog;
import src.model.individual.Individual;
import src.model.registry.SpeciesActionRegistry;
import src.model.stats.Stats;
import src.observer.Observer;
import src.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Modèle lié aux commandes liées aux individus.
 *
 * @see SimulationModel
 */
public class IndividualSimulationModel implements Subject, SimulationModel {

    private List<Individual> individuals = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private List<Class<? extends Individual>> availableClasses = List.of(Dog.class, Dinosaur.class);
    private String outputToDisplay;

    /**
     * Renvoie la liste de tous les individus présents dans le programme.
     */
    public List<Individual> getIndividuals() {
        return individuals;
    }

    /**
     * Setter : définie le message que le modèle va communiquer à la vue.
     *
     * @param outputToDisplay message contenant le changement de l'état du modèle
     */
    public void setOutputToDisplay(String outputToDisplay) {
        this.outputToDisplay = outputToDisplay;
    }

    /**
     * Renvoie le message contenant le changement de l'état du modèle.
     */
    public String getOutputToDisplay() {
        return outputToDisplay;
    }

    /**
     * Ajoute un individu au modèle.
     *
     * @param species espèce de l'individu
     * @param name    nom de l'individu
     * @throws ReflectiveOperationException
     */
    public void addIndividual(String species, String name) throws ReflectiveOperationException {

        Class<? extends Individual> subClass = availableClasses.stream()
                .filter(cls -> cls.getSimpleName().equals(species))
                .findFirst()
                .orElse(null);

        if (subClass == null) {
            setOutputToDisplay("Aucune espèce de ce type : " + species + "\n" + "Espèces : " +
                    availableClasses.stream()
                            .map(Class::getSimpleName)
                            .reduce((a, b) -> a + " " + b)
                            .orElse(""));
            notifyObservers();
        } else if (subClass.getSimpleName().equals(species)) {
            if (verifyNameAvailability(name)) {
                Stats stats = new Stats(100, 50, 50);
                Individual individual = subClass.getDeclaredConstructor(String.class, String.class,
                        String.class, Stats.class).newInstance(name, name, species, stats);
                getIndividuals().add(individual);
                setOutputToDisplay("Individual " + name + " added successfully\n" + name + " : "
                        + individual.getStats());
                notifyObservers();
            }
        }
    }


    @Override
    public void simulateAction(String firstIndividualName, String actionName) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);

        if (firstIndividual == null) {
            return;
        }

        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(firstIndividual.getSpecies());
        Action action = actions.get(actionName);
        if (action == null) {
            String speciesActions = SpeciesActionRegistry.getSpeciesActions(firstIndividual.getSpecies());
            setOutputToDisplay("Actions possibles : " + speciesActions);
            notifyObservers();
            return;
        }
        boolean actionIsSuccessful = firstIndividual.performAction(action);
        if (!actionIsSuccessful) {
            setOutputToDisplay("Action n'est pas réussie pour " + firstIndividualName);
            notifyObservers();
            return;
        }
        String actionMessage = action.getActionMessage();
        setOutputToDisplay("\u001B[32m" + actionMessage + "\u001B[0m\n" +
                "Action est réussie pour " + firstIndividualName + "\n" + firstIndividualName + " : "
                + firstIndividual.getStats());
        notifyObservers();
    }

    @Override
    public void simulateAction(String firstIndividualName, String actionName,
                               String secondIndividualName) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);
        Individual secondIndividual = getIndividualByName(secondIndividualName);

        if (firstIndividual == null || secondIndividual == null) {
            return;
        }

        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(firstIndividual.getSpecies());
        Action action = actions.get(actionName);
        boolean actionIsSuccessful = firstIndividual.performAction(action, secondIndividual);
        if (!actionIsSuccessful) {
            setOutputToDisplay(
                    "Action n'est pas réussie entre " + firstIndividualName + " et " + secondIndividualName);
            notifyObservers();
            return;
        }
        String actionMessage = action.getActionMessage();
        setOutputToDisplay("\u001B[32m" + actionMessage + "\u001B[0m\n" +
                "Action successful between " + firstIndividualName + " and " + secondIndividualName
                + "\n"
                + firstIndividualName + " : " + firstIndividual.getStats() + "\n"
                + secondIndividualName
                + " : " + secondIndividual.getStats());
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

    @Override
    public void updateIndividual(String id, String life, String food, String water) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(id)) {
                individual.setStats(
                        new Stats(Float.parseFloat(life), Float.parseFloat(food),
                                Float.parseFloat(water)));
                setOutputToDisplay("Individual " + id + " updated successfully\n" + id + " : "
                        + individual.getStats());
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

    /**
     * Vérifie que le nom de l'individu n'est pas encore pris
     *
     * @param name nom de l'individu
     * @return true si le nom est unique, false sinon
     */
    public boolean verifyNameAvailability(String name) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(name)) {
                setOutputToDisplay("Le nom " + name + " est déjà pris\n");
                notifyObservers();
                return false;
            }
        }
        return true;
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
