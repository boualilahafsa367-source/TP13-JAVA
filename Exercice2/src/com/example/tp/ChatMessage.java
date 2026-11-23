
package com.example.tp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Instant;

/**
 * Classe ChatMessage : représente un message de chat.
 * Externalizable permet de contrôler manuellement
 * les données sauvegardées et récupérées.
 */
public class ChatMessage implements Externalizable {

    // Identifiant de version de la classe
    private static final long serialVersionUID = 2L;

    // Version du format de sauvegarde
    private static final int FORMAT_VERSION = 1;

    // Attributs du message
    private String user;
    private String message;
    private Instant timestamp;

    // Champ temporaire : il sera recalculé après lecture
    private transient int length;

    // Constructeur sans argument obligatoire pour Externalizable
    public ChatMessage() {
    }

    // Constructeur avec paramètres
    public ChatMessage(String user, String message) {
        this.user = user;
        this.message = message;
        this.timestamp = Instant.now();
        this.length = message.length();
    }

    /**
     * Écriture manuelle des données dans le fichier.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        // Écrire la version du format
        out.writeInt(FORMAT_VERSION);

        // Écrire les informations du message
        out.writeUTF(user);
        out.writeUTF(message);
        out.writeLong(timestamp.toEpochMilli());

        // length n'est pas sauvegardé
    }

    /**
     * Lecture manuelle des données depuis le fichier.
     */
    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {

        // Lire et vérifier la version
        int version = in.readInt();

        if (version != FORMAT_VERSION) {
            throw new IOException("Version inconnue : " + version);
        }

        // Récupérer les informations dans le même ordre
        user = in.readUTF();
        message = in.readUTF();
        timestamp = Instant.ofEpochMilli(in.readLong());

        // Recalculer la longueur du message
        length = message.length();
    }

    // Getters
    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getLength() {
        return length;
    }

    // Affichage du message
    @Override
    public String toString() {
        return String.format(
            "%s [%s]: %s",
            timestamp, user, message
        );
    }
}
