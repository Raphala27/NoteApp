# Application de prise de note

## Objectif
L'objectif de ce projet est de développer une application Android simple et efficace permettant aux utilisateurs de créer, gérer et organiser leurs notes.

## Fonctionnalités principales
L'application propose les fonctionnalités suivantes :

1. **Afficher toutes les notes** : L'utilisateur peut visualiser l'ensemble des notes créées, organisées par ordre de création ou de mise à jour.
2. **Insérer une nouvelle note** : L'utilisateur peut ajouter une nouvelle note. Chaque note comporte un identifiant unique, un titre et un texte.
3. **Supprimer une note existante** : L'utilisateur a la possibilité de supprimer une note indésirable de manière définitive.
4. **Mettre à jour une note existante** : L'utilisateur peut modifier le titre et/ou le contenu d'une note existante.

## Structure des Notes
- **Identifiant** : Un identifiant unique généré automatiquement pour chaque note.
- **Titre** : Un titre court permettant de décrire rapidement le contenu de la note.
- **Texte** : Le contenu principal de la note.

## Logo de l'application
Pour rendre **NoteApp** plus professionnelle et visuellement attrayante, nous avons ajouté un logo personnalisé à l'application, lui donnant l'apparence d'une véritable application de prise de notes.
![Logo](NoteApp.jpeg)
## Technologies utilisées

### RecyclerView
Nous avons utilisé **RecyclerView** pour afficher la liste des notes de manière efficace. RecyclerView permet de gérer des ensembles de données volumineux tout en offrant des performances optimisées. Il s'agit d'un composant flexible permettant de recycler les vues pour minimiser les ressources utilisées par l'application, surtout lors du défilement des notes.

### RoomDatabase
Pour stocker localement les notes de l'utilisateur, nous avons implémenté une base de données à l'aide de **RoomDatabase**, qui est une bibliothèque ORM (Object-Relational Mapping) légère pour Android. Room permet de structurer et de gérer les données de manière sécurisée et persistante, garantissant que les notes sont enregistrées même après la fermeture de l'application. Cela facilite aussi les opérations CRUD (Create, Read, Update, Delete) sur les notes tout en assurant la gestion des threads et la sécurité des transactions.

| Plateforme | Langage de programmation | Outils de développement |
|------------|--------------------------|-------------------------|
| ![Android](https://img.icons8.com/ios-filled/50/000000/android-os.png) Android |  ![Kotlin](https://img.icons8.com/color/50/000000/kotlin.png) Kotlin | ![Android Studio](https://img.icons8.com/color/50/000000/android-studio--v2.png) Android Studio |


## Installation et utilisation
1. Cloner ce dépôt dans votre environnement local.
    ```bash
    git clone https://github.com/nom-utilisateur/note-app.git
    ```
    ![GitHub](https://img.icons8.com/ios-glyphs/30/000000/github.png)
2. Ouvrir le projet dans Android Studio.
3. Compiler et exécuter l'application sur un appareil ou un émulateur Android.

## Auteurs
Ce projet a été réalisé par Ewan Hamond, Raphaël Issartial et Mathis Sergent
