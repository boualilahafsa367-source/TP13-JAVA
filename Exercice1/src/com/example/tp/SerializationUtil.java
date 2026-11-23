
package com.example.tp;

import java.io.*;
import java.util.List;

/**
 * Classe utilitaire pour la sérialisation
 * et la désérialisation des employés.
 */
public class SerializationUtil {

    /**
     * Sauvegarde une liste d'employés dans un fichier binaire.
     */
    public static void serializeEmployees(String path, List<Employee> data)
            throws IOException {

        // Ouverture du flux de sortie
        // try-with-resources ferme automatiquement le flux
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(path))) {

            // Écriture de la liste dans le fichier
            oos.writeObject(data);
        }
    }

    /**
     * Récupère une liste d'employés depuis un fichier binaire.
     */
    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeEmployees(String path)
            throws IOException, ClassNotFoundException {

        // Ouverture du flux d'entrée
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(path))) {

            // Lecture et conversion de l'objet récupéré
            return (List<Employee>) ois.readObject();
        }
    }
}
