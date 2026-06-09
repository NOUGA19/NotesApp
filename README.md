#  NotesApp — Gestionnaire de Notes Personnelles

> Application Android académique · Génie Logiciel · ENSPM Maroua

---

## Présentation

**NotesApp** est une application Android moderne développée dans un cadre académique (Génie Logiciel). Elle permet aux utilisateurs de gérer leurs notes quotidiennes de manière structurée et sécurisée. L'application met l'accent sur la fluidité de l'expérience utilisateur (UX) et la robustesse de l'architecture logicielle.

---

##  Fonctionnalités

| Fonctionnalité | Description |
|---|---|
|  **CRUD complet** | Création, lecture, modification et suppression de notes |
|  **Catégories** | Classement sémantique (Cours, Travail, Personnel) avec code couleur |
|  **Épinglage** | Notes importantes épinglées en haut de liste via appui long |
|  **Recherche réactive** | Filtrage en temps réel par mots-clés |
|  **Filtrage dynamique** | Tri instantané par catégories via onglets interactifs |
|  **Onboarding** | Écran d'accueil persistant pour les nouveaux utilisateurs |
|  **Hors-ligne** | Données sauvegardées localement, fonctionnement 100% offline |

---

##  Stack Technique

> Basée sur les dernières recommandations Google pour le développement Android natif.

| Couche | Technologie |
|---|---|
| **Langage** | Kotlin — Coroutines, Flow, Extension Functions |
| **UI** | Jetpack Compose — UI déclarative |
| **Design** | Material Design 3 (Material You) |
| **Architecture** | MVVM — séparation stricte des responsabilités |
| **Base de données** | Room Persistence Library (abstraction SQLite) |
| **Préférences** | Jetpack DataStore (remplaçant SharedPreferences) |
| **Navigation** | Compose Navigation avec paramètres typés |
| **Asynchrone** | Kotlin Coroutines & Flow — UI réactive et non-bloquante |

---

## 🗂️ Architecture du Projet

```
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
```

---

## 🚀 Installation

1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/NOUGA19/NotesApp.git
   ```

2. **Ouvrir le projet** dans Android Studio (version *Ladybug* ou supérieure)

3. **Synchroniser** le projet avec les fichiers Gradle

4. **Lancer** sur un émulateur ou un appareil physique
   - SDK minimum : **API 26 / Android 8.0**
