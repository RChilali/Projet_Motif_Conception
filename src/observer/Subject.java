package src.observer;

public interface Subject {

    /**
     * Ajoute un observateur au sujet
     *
     * @param observer l'observateur à ajouter s'abonnant aux notifications de modification de l'état du sujet.
     */
    void addObserver(Observer observer);

    /**
     * Supprime un des observateurs de sujet
     *
     * @param observer l'observateur à supprimer se désabonnant des notifications de modification de l'état du sujet.
     */
    void removeObserver(Observer observer);

    /**
     * Notifie tous les abonnés d'une modification de l'état du sujet.
     */
    void notifyObservers();
}