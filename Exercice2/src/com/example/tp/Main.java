
package com.example.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Programme principal pour tester
 * la sérialisation avec Externalizable.
 */
public class Main {

    public static void main(String[] args) {

        // Nom du fichier de sauvegarde
        String file = "chat.ser";

        // 1. Création d'un historique de chat
        List<ChatMessage> history = new ArrayList<>();

        history.add(new ChatMessage(
            "Sara", "Bonjour tout le monde"
        ));

        history.add(new ChatMessage(
            "Imane", "Comment allez-vous ?"
        ));

        history.add(new ChatMessage(
            "Adam", "Je vais très bien merci"
        ));

        // 2. Sauvegarde de l'historique
        try {
            ChatHistory.save(file, history);

            System.out.println(
                "Historique sauvegardé dans " + file
            );

        } catch (IOException e) {
            System.err.println(
                "Erreur de sauvegarde : " + e.getMessage()
            );
            return;
        }

        // 3. Chargement de l'historique
        List<ChatMessage> loaded;

        try {
            loaded = ChatHistory.load(file);

            System.out.println("Historique chargé :");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(
                "Erreur de chargement : " + e.getMessage()
            );
            return;
        }

        // 4. Affichage des messages récupérés
        for (ChatMessage msg : loaded) {

            System.out.printf(
                "  [%d caractères] %s%n",
                msg.getLength(), msg
            );
        }
    }
}
