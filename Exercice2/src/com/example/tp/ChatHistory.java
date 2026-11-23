
package com.example.tp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ChatHistory : gère la sauvegarde
 * et le chargement d'un historique de chat.
 */
public class ChatHistory {

    /**
     * Sauvegarde une liste de messages dans un fichier.
     */
    public static void save(String path, List<ChatMessage> history)
            throws IOException {

        // Ouvrir le flux de sortie
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(path))) {

            // Enregistrer le nombre de messages
            oos.writeInt(history.size());

            // Enregistrer chaque message manuellement
            for (ChatMessage msg : history) {
                msg.writeExternal(oos);
            }
        }
    }

    /**
     * Charge les messages depuis un fichier.
     */
    public static List<ChatMessage> load(String path)
            throws IOException, ClassNotFoundException {

        // Créer une liste vide pour récupérer les messages
        List<ChatMessage> history = new ArrayList<>();

        // Ouvrir le flux de lecture
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(path))) {

            // Lire le nombre de messages enregistrés
            int count = ois.readInt();

            // Lire chaque message
            for (int i = 0; i < count; i++) {

                // Créer un nouvel objet
                ChatMessage msg = new ChatMessage();

                // Restaurer ses informations
                msg.readExternal(ois);

                // Ajouter le message à la liste
                history.add(msg);
            }
        }

        return history;
    }
}
