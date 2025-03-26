# YouSave - Blood Donation Platform

## Problématique

La gestion des dons de sang est un processus essentiel mais souvent complexe. Il y a des lacunes dans la mise en relation rapide entre les donneurs et les receveurs, ainsi que dans la gestion des informations critiques, ce qui peut entraîner des retards et des erreurs. La difficulté de vérifier l'éligibilité des donneurs aggrave cette situation.

## Solution Proposée

**YouSave** est une plateforme numérique qui facilite la mise en relation entre les donneurs de sang ("YouSavers") et les receveurs. L'application permet une gestion simple, rapide et sécurisée des dons de sang, en y ajoutant des fonctionnalités telles que la vérification d'éligibilité des donneurs, des outils de recherche, ainsi que des ressources de sensibilisation et d'éducation.

## Fonctionnalités Principales

- **Vérification d'éligibilité des donneurs** : Permet de vérifier en temps réel si un donneur est éligible à faire un don.
- **Gestion des dons** : Suivi des dons effectués et planification des futurs dons.
- **Mise en relation entre donneurs et receveurs** : Connecte rapidement les donneurs et les receveurs pour assurer des transferts de sang efficaces.
- **Éducation et sensibilisation** : Fournit des ressources pour informer les utilisateurs sur les bonnes pratiques en matière de don de sang.

## Technologies

### Backend

- **Framework** : [Spring Boot](https://spring.io/projects/spring-boot) (Spring Data, Spring Security)
- **Base de données** : [PostgreSQL](https://www.postgresql.org/)
- **API REST** : Pour la communication entre le backend et le frontend
- **Sécurité** : [JWT](https://jwt.io/) pour l'authentification et la gestion des rôles

### Frontend

- **Framework** : [Angular](https://angular.io/)
- **UI Library** : [Bootstrap](https://getbootstrap.com/) ou [Material Design](https://material.angular.io/)

### CI/CD et DevOps

- **CI/CD** : [Jenkins](https://www.jenkins.io/) ou [GitLab CI](https://about.gitlab.com/stages-devops-lifecycle/continuous-integration/)
- **Containerisation** : [Docker](https://www.docker.com/) pour conteneuriser l'application

## Prérequis

Avant de commencer à travailler sur ce projet, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- **JDK 11+** (Pour le développement backend avec Spring Boot)
- **Node.js** et **npm** (Pour le développement frontend avec Angular)
- **PostgreSQL** (Base de données pour stocker les informations)
- **Docker** (Pour la gestion des containers)
- **Jenkins** ou **GitLab CI** pour la gestion de l'intégration continue

## Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/MaryemKhaoua/YouSaveBackend.git
cd yousave