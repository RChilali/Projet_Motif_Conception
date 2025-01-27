package src.model.individual;

import src.model.stats.Stats;

/**
 * Individu chien.
 */
public class Dog extends Individual {

    private Gender gender;

    /**
     * Constructor champ à champ définissant le genre par défault.
     *
     * @param id      idéntifiant du chien
     * @param name    nom du chien
     * @param species espèce du chien
     * @param stats   statistics du chien
     */
    public Dog(String id, String name, String species, Stats stats) {
        this(id, name, species, stats, Gender.male);
    }

    /**
     * Constructor champ à champ.
     *
     * @param id      idéntifiant du chien
     * @param name    nom du chien
     * @param species espèce du chien
     * @param stats   statistics du chien
     * @param gender  genre du chien
     */
    public Dog(String id, String name, String species, Stats stats, Gender gender) {
        super(id, name, species, stats);
        setGender(gender);
    }

    /**
     * @return le genre du chien
     */
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return la chaîne de caractères formatée décrivant le chien.
     */
    @Override
    public String toString() {
        return String.format("Dog [ID: %s, Name: %s, Gender: %c]", getId(), getName(), gender);
    }

    public enum Gender {
        male,
        female
    }
}