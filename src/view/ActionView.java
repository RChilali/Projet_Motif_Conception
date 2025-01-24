package src.view;

import src.controller.ActionController;
import src.model.simulation.ActionModel;
import src.observer.Observer;
import src.observer.Subject;

import java.util.Scanner;

public class ActionView implements SimulationView, Observer {

    private Scanner scanner = new Scanner(System.in);

    private ActionController controller;

    private ActionModel model;

    public ActionView(ActionController controller, ActionModel model) {
        this.controller = controller;
        this.model = model;

        if (model instanceof Subject) {
            ((Subject) model).addObserver(this);
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        System.err.println("! ERROR: " + message);
    }

    @Override
    public String insertName() {
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    @Override
    public String insertSpecies() {
        System.out.print("Enter species (Dog/Dinosaur): ");
        return scanner.nextLine();
    }

    public String insertIndividualId() {
        System.out.print("Enter id: ");
        return scanner.nextLine();
    }

    public String insertActionName() {
        System.out.print("Enter action (e.g., 'Bite'): ");
        return scanner.nextLine();
    }

    public void insertAction() {
        controller.manageInsertAction();
    }

    @Override
    public void update() {
        System.out.println("Action added");
    }
}
