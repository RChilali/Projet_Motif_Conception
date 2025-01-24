package src.model.simulation.registry;

import src.model.simulation.DinosaurSimulationModel;
import src.model.simulation.DogSimulationModel;
import src.model.simulation.SimulationModel;
import src.model.simulation.UnknownSimulationModel;

import java.util.ArrayList;
import java.util.List;

public class SimulationModelRegistry {
    // Liste des implémentations enregistrées
    private static final List<SimulationModel> registeredModels = new ArrayList<>();

    // Enregistre une nouvelle implémentation
    static {
        registeredModels.add(new DogSimulationModel());
        registeredModels.add(new DinosaurSimulationModel());
        registeredModels.add(new UnknownSimulationModel());
    }

    // Trouve le bon modèle en parcourant les implémentations
    public static SimulationModel getInstance(String input) {
        return registeredModels.stream()
                .filter(model -> model.supports(input))
                .findFirst().orElse(new UnknownSimulationModel());
    }

    public static SimulationModel getInstance(List<SimulationModel> models, String input) {
        return models.stream()
                .filter(model -> model.supports(input))
                .findFirst().orElse(new UnknownSimulationModel());
    }
}