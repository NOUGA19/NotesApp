NotesApp - Gestionnaire de Notes Personnelles (Android)
Présentation du Projet
NotesApp est une application Android moderne développée dans un cadre académique (Génie Logiciel). Elle permet aux utilisateurs de gérer leurs notes quotidiennes de manière structurée et sécurisée. L'application met l'accent sur la fluidité de l'expérience utilisateur (UX) et la robustesse de l'architecture logicielle.
 Fonctionnalités
Cycle CRUD complet : Création, lecture, modification et suppression de notes.
Organisation par catégories : Classement sémantique (Cours, Travail, Personnel) avec code couleur dédié.
Priorisation par épinglage : Possibilité d'épingler les notes importantes en haut de liste via un appui long.
Recherche réactive : Filtrage des notes en temps réel par mots-clés.
Filtrage dynamique : Tri instantané par catégories via des onglets interactifs.
Expérience de premier démarrage : Système d'Onboarding persistant pour accueillir les nouveaux utilisateurs.
Persistance locale : Données sauvegardées localement pour un fonctionnement 100% hors-ligne.
 Stack Technique
L'application repose sur les dernières recommandations de Google pour le développement Android natif :
Langage : Kotlin (Coroutines, Flow, Extension Functions).
Interface Utilisateur : Jetpack Compose (UI Déclarative).
Design : Material Design 3 (Material You).
Architecture : MVVM (Model-View-ViewModel) pour une séparation stricte des responsabilités.
Base de données : Room Persistence Library (Abstraction SQLite).
Stockage de préférences : Jetpack DataStore (remplaçant SharedPreferences).
Navigation : Compose Navigation avec passage de paramètres typés.
Traitement Asynchrone : Kotlin Coroutines & Flow pour une UI réactive et non-bloquante.
 Architecture du Projet
Le projet respecte les principes de la Clean Architecture avec un découpage par paquets (packages) :
code
Text
com.example.notesapp
├── data/
│   ├── model/         # Entités Room et Enums (Data Classes)
│   ├── database/      # DAO, Converters et Singleton de la DB
│   └── repository/    # Médiateur de données (Source unique de vérité)
├── viewmodel/         # Logique métier et gestion d'état (State)
├── ui/
│   ├── theme/         # Couleurs, Typographie et Thème Material 3
│   ├── components/    # Composants réutilisables (NoteItem, Dialogs)
│   └── screens/       # Écrans complets (List, Detail, Edit, Onboarding)
└── navigation/        # Gestionnaire de routes (NavGraph)
 Installation
Cloner le dépôt : git clone [[URL_DU_REPO]](https://github.com/NOUGA19/NotesApp.git)
Ouvrir le projet dans Android Studio (version Ladybug ou supérieure).
Synchroniser le projet avec les fichiers Gradle.
Lancer l'application sur un émulateur ou un appareil physique (SDK minimum : API 26 / Android 8.0).
