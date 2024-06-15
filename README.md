# YnovWebServices : ReservationApp

Cette API permet de Reserver des seance pour voir des films.

## Docker

### Prérequis

- Avoir docker
- Avoir un outil permettant d'écrire des caractères sur un CLI

### Utilisation

- Cloner le projet
- Se déplacer dans la racine du projet
- Executer la commande
 ```sh
   docker compose up -d
```

## DevMod

### Prérequis

- Avoir Java 17
- Avoir Gradle
- Avoir PostgresSQL

### Utilisation 

- Cloner le projet
- Créé une Bdd reservationapp dans votre postgres
- Ouvrez le projet dans votre IDE préféré (par exemple, IntelliJ IDEA).
- Dans le `application.yml` vérifier que les configurations postgres sont les bonnes
- Executer le fichier ReservationAppApplication

L'application sera déployée par défaut sur `http://localhost:8082`

## Documentations
Lors de la creation d'une demande de reservation l'utilisateur à 15 min pour accepter.
Pour un soucis de simplicité les emails seront tous envoyer dans une queue (fake-email)

### Swagger (OpenAPI)

Vous pouvez consulter la documentation de l'API à l'adresse suivante : http://localhost:8082/swagger-ui/index.html

Ou bien la récupérer au format json à l'adresse suivante : http://localhost:8082/v3/api-docs

## Auteurs

- Thomas Dubuis
- Ancelot Fayolle
- Adam Ouerfelli
- Bryan Battu

## Licence

Ce projet est sous licence MIT.
