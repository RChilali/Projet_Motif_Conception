package src.controller;

public interface SimulationController<T> {

    void setView(T view);
    void manageRequest(String request);

}