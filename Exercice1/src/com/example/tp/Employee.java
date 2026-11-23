
package com.example.tp;

import java.io.Serializable;

/**
 * Classe Employee : représente un employé.
 * Serializable permet de sauvegarder et restaurer les objets.
 */
public class Employee implements Serializable {

    // Identifiant de version de la classe
    private static final long serialVersionUID = 1L;

    // Attributs de l'employé
    private final int id;
    private final String name;
    private final double salary;

    // Le mot de passe ne sera pas sauvegardé
    private transient String password;

    // Constructeur
    public Employee(int id, String name, double salary, String password) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getPassword() {
        return password;
    }

    // Afficher les informations de l'employé
    @Override
    public String toString() {
        return String.format(
            "Employee[id=%d, name=%s, salary=%.2f, password=%s]",
            id, name, salary,
            (password == null ? "null" : password)
        );
    }
}
