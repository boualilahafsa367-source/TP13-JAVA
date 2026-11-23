
package com.example.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Programme principal pour tester
 * la sérialisation et la désérialisation.
 */
public class Main {

    public static void main(String[] args) {

        // Chemin du fichier de sauvegarde
        final String filePath = "employees.ser";

        // 1. Création d'une liste d'employés
        List<Employee> staff = new ArrayList<>();

        staff.add(new Employee(101, "Sara", 4500.0, "sara123"));
        staff.add(new Employee(102, "Imane", 5200.5, "imane456"));
        staff.add(new Employee(103, "Adam", 3800.75, "adam789"));

        // Affichage de la liste initiale
        System.out.println("=== Liste initiale des employés ===");

        for (Employee emp : staff) {
            System.out.println(emp);
        }

        // 2. Sérialisation : sauvegarde des employés
        try {
            SerializationUtil.serializeEmployees(filePath, staff);

            System.out.println("\nSérialisation réussie !");
            System.out.println("Fichier créé : " + filePath);

        } catch (IOException e) {
            System.err.println("Erreur de sérialisation : " + e.getMessage());
            return;
        }

        // 3. Désérialisation : récupération des employés
        List<Employee> loaded;

        try {
            loaded = SerializationUtil.deserializeEmployees(filePath);

            System.out.println("\nDésérialisation réussie !");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur de désérialisation : " + e.getMessage());
            return;
        }

        // 4. Affichage des objets récupérés
        System.out.println("\n=== Employés après désérialisation ===");

        for (Employee emp : loaded) {
            System.out.println(emp);
        }

        // 5. Vérification de l'attribut transient
        System.out.println("\n=== Vérification du mot de passe ===");

        for (Employee emp : loaded) {
            System.out.println(
                emp.getName() + " : " + emp.getPassword()
            );
        }
    }
}
