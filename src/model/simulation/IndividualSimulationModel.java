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
 * Modèle lié aux commandes sur les individus.
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
                            .orElse("") + "\n");
            notifyObservers();
        } else if (subClass.getSimpleName().equals(species)) {
            if (verifyNameAvailability(name)) {
                Stats stats = new Stats(100, 50, 50);
                Individual individual = subClass.getDeclaredConstructor(String.class, String.class,
                        String.class, Stats.class).newInstance(name, name, species, stats);
                getIndividuals().add(individual);
                setOutputToDisplay("Individu " + name + " est ajouté avec succès\n" + name + " : "
                        + individual.getStats() + "\n");
                notifyObservers();
            }
        }
    }


    @Override
    public void simulateAction(String firstIndividualName, String actionName) {
        Individual firstIndividual = getIndividualByName(firstIndividualName);

        if (firstIndividual == null) {
            setOutputToDisplay("Individu " + firstIndividualName + " n'existe pas\n");
            notifyObservers();
            return;
        }

        String firstIndividualSpecies = firstIndividual.getSpecies();
        if (firstIndividualSpecies == null) {
            setOutputToDisplay("Espèce de l'individu " + firstIndividualName + " n'existe pas\n");
            notifyObservers();
            return;
        }

        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(firstIndividualSpecies);
        Action action = actions.get(actionName);
        if (action == null) {
            String speciesActions = SpeciesActionRegistry.getSpeciesActions(firstIndividualSpecies);
            setOutputToDisplay("Actions possibles pour " + speciesActions);
            notifyObservers();
            return;
        }
        boolean actionIsSuccessful = firstIndividual.performAction(action);
        if (!actionIsSuccessful) {
            setOutputToDisplay("Action n'est pas réussie pour " + firstIndividualName + "\n");
            notifyObservers();
            return;
        }
        String actionMessage = action.getActionMessage();
        setOutputToDisplay("==*==\n"
                + actionMessage + "\n" +
                "Action est réussie pour " + firstIndividualName + "\n" + firstIndividualName + " : "
                + firstIndividual.getStats() + "\n"
                + "==*==\n");
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

        String firstIndividualSpecies = firstIndividual.getSpecies();
        if (firstIndividualSpecies == null) {
            return;
        }

        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(firstIndividualSpecies);
        Action action = actions.get(actionName);

        if (action == null) {
            String speciesActions = SpeciesActionRegistry.getSpeciesActions(firstIndividualSpecies);
            setOutputToDisplay("Actions possibles pour " + speciesActions);
            notifyObservers();
            return;
        }

        boolean actionIsSuccessful = firstIndividual.performAction(action, secondIndividual);
        if (!actionIsSuccessful) {
            setOutputToDisplay(
                    "Action n'est pas réussie entre " + firstIndividualName + " et " + secondIndividualName + "\n");
            notifyObservers();
            return;
        }
        String actionMessage = action.getActionMessage();
        setOutputToDisplay("==*==\n"
                + actionMessage + "\n"
                + "Action est réussie entre " + firstIndividualName + " et " + secondIndividualName
                + "\n"
                + firstIndividualName + " : " + firstIndividual.getStats() + "\n"
                + secondIndividualName
                + " : " + secondIndividual.getStats()
                + "\n"
                + "==*==\n");

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
                setOutputToDisplay("Individu " + id + " est modifié avec succès\n" + id + " : "
                        + individual.getStats());
                notifyObservers();
                return;
            }
        }
        setOutputToDisplay("Individu " + id + " n'est pas trouvé\n");
        notifyObservers();

    }

    @Override
    public void deleteIndividual(String id) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(id)) {
                individuals.remove(individual);
                setOutputToDisplay("Individu " + id + " est supprimé avec succès\n");
                notifyObservers();
                return;
            }
        }
        setOutputToDisplay("Individu " + id + " n'est pas trouvé\n");
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
