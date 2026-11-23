# Exercice 1 : Sérialisation et Désérialisation
**Résultat de l'exécution:**
<img width="1920" height="783" alt="image" src="https://github.com/user-attachments/assets/74e966f6-2e4a-4aa3-bc4f-3d59bd0ee9c6" />

La capture d'écran suivante présente l'exécution du programme dans le terminal. Elle montre la liste initiale des employés avec leurs informations, la confirmation de la sauvegarde dans le fichier employees.ser et la récupération des objets après désérialisation.

On remarque que les identifiants, les noms et les salaires sont conservés après la restauration des objets. En revanche, les mots de passe deviennent null, car l'attribut password est déclaré transient et n'est donc pas enregistré pendant la sérialisation.

**Description du fonctionnement:**

Employee.java : définit la classe métier Employee avec les attributs id, name, salary et password. La classe implémente l'interface Serializable afin de permettre la sérialisation de ses objets. L'attribut password est déclaré transient pour éviter son enregistrement dans le fichier binaire. La classe contient également un constructeur, des getters et une méthode toString() pour afficher les informations des employés.

SerializationUtil.java : contient deux méthodes statiques. La méthode serializeEmployees() utilise ObjectOutputStream pour enregistrer une liste d'employés dans un fichier. La méthode deserializeEmployees() utilise ObjectInputStream pour récupérer et restaurer cette liste. Les flux sont gérés avec try-with-resources afin de garantir leur fermeture automatique.

Main.java : crée une liste contenant trois employés, lance la sérialisation dans le fichier employees.ser, puis effectue la désérialisation pour récupérer les objets sauvegardés. Enfin, le programme affiche les employés restaurés et vérifie que le champ password possède la valeur null après la désérialisation.


# Exercice 2 : Sérialisation Avancée avec Externalizable et Contrôle de Flux
**Résultat de l'exécution:**
<img width="957" height="209" alt="image" src="https://github.com/user-attachments/assets/bdce10eb-3003-4331-b9fa-02e580f862fc" />

La capture d'écran suivante présente l'exécution du programme dans le terminal. Elle montre la confirmation de la sauvegarde de l'historique dans le fichier chat.ser, suivie du chargement des messages enregistrés.

L'affichage permet de vérifier que les messages, les noms des utilisateurs et les dates d'envoi sont correctement restaurés. La longueur de chaque message est également affichée. Cette valeur est recalculée après la lecture du fichier, car elle n'est pas directement enregistrée dans le flux de sérialisation.

La version du format binaire est également vérifiée pendant la lecture afin de détecter toute incompatibilité.


**Description du fonctionnement:**

ChatMessage.java : représente un message de conversation avec les attributs user, message, timestamp et length. La classe implémente l'interface Externalizable et définit les méthodes writeExternal() et readExternal() pour contrôler manuellement les données enregistrées et récupérées. Le champ length est déclaré transient, car il est recalculé à partir du contenu du message après la désérialisation. Un constructeur public sans argument est également défini conformément aux exigences de Externalizable.

ChatHistory.java : permet de gérer la sauvegarde et le chargement d'un historique de messages. La méthode save() utilise ObjectOutputStream pour enregistrer le nombre de messages, puis écrit les informations de chaque message à l'aide de writeExternal(). La méthode load() utilise ObjectInputStream pour lire le nombre de messages, reconstruire les objets ChatMessage avec readExternal() et les ajouter dans une nouvelle liste.

Main.java : crée un historique contenant trois messages échangés entre différents utilisateurs. Le programme sauvegarde cet historique dans le fichier chat.ser, le charge ensuite et affiche les messages récupérés avec leur date d'envoi et leur nombre de caractères.



