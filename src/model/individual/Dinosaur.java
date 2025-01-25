package src.model.individual;

import src.model.stats.Stats;
import src.model.registry.SpeciesActionRegistry;
import src.model.actions.Action;
import java.util.Map;

public class Dinosaur extends Individual {

  private boolean canFly;

  public Dinosaur(String id, String name, Stats stats, boolean canFly) {
    super(id, name, "Dinosaur", stats);
    setCanFly(canFly);
  }

  public boolean getCanFly() {
    return canFly;
  }

  public void setCanFly(boolean canFly) {
    this.canFly = canFly;
  }

  public void performAction(String actionName, Individual target) {
    Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies("Dinosaur");
    Action action = actions.get(actionName);
    if (action != null && action.validate(this, target)) {
      action.execute(this, target);
    }
  }

  public void performAction(String actionName) {
    Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies("Dinosaur");
    Action action = actions.get(actionName);
    if (action != null && action.validate(this, null)) {
      action.execute(this, null);
    }
  }

  @Override
  public String toString() {
    return String.format("Dinosaur [ID: %s, Name: %s, CanFly: %b]", getId(), getName(), canFly);
  }
}