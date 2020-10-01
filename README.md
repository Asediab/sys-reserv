[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=Asediab_sys-reserv&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=Asediab_sys-reserv)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=Asediab_sys-reserv&metric=alert_status)](https://sonarcloud.io/dashboard?id=Asediab_sys-reserv) 
[![Build Status](https://travis-ci.com/Asediab/sys-reserv.svg?branch=develop)](https://travis-ci.com/Asediab/sys-reserv)


# OCR - Projet 12

### Objectifs du projet

Pendant le confinement de Covid-19, chaque lieu public peut desservir un nombre limité de visiteurs en même temps. Ce projet offre un système de réservation amélioré aux lieux publics d’une ville. Ce qui aidera à gérer le nombre de visiteurs sur une période de temps spécifique et fournira aux utilisateurs un système de réservation pratique.
Cela réduira par la suite le nombre d’infections.

### Description du projet

Un système d’information de lieux publics municipaux (médiatiques, piscines, centres sportifs) comportant les fonctions suivantes :

    • Un site web (User)
      — Consulter le liste de lieux publics de ville et laisser un commentaire
      — Réserver une visite à l’heure souhaitée
      — Gérer les réservations enregistrées
    • Un site pour des personnels (Employee)
      — Gerer des réservations
    • Un site pour administrateur (Admin)
      — Modifier/Supprimer/Ajouter des données de lieux publics
      — Modifier/Supprimer des commentaires enregistrés
      — Enregistrer nouvel employé
      


## Déploiement de l'application

La procédure est la suivante :
1) Télécharger le fichier `docker-compose.yml` et le dossier `postgresd` 
2) S'assurer que Docker est installé
3) Exécuter la ligne de commande dans le dossier où se trouvent les fichiers téléchargés du point 1
4) Démarrer le système en utilisant les commandes :
5) Ouvrir un browser web à l'adresse http://localhost:8888

`Lancement` 

docker-compose up

`Arrêt` 

docker-compose stop

`Remise à zero`

docker-compose stop

docker-compose rm -v


   
## Jeu de donné de démo
Le système dispose de 3 espaces personnels différents en fonction des droits de l'utilisateur dans le système.
Pour accéder à chacun de ces espaces, connectez-vous au système avec l'un des utilisateurs pré-enregistrés avec les droits désignés.

User - Email :`user@user.com` Mot de pass :`user`
 
Employee - Email :`user@user0.com` Mot de pass :`user`

Admin - Email :`admin@admin.com` Mot de pass :`user`


# Description technique

L'application utilise les frameworks & projets suivants :

1) _SpringBoot 2.2.6.RELEASE_ 

2) _Spring MVC_ 

3) _Spring Data_ 

4) _Spring Security_ & _spring-cloud-starter-oauth2_ & _spring-cloud-starter-security_

5) _Angular 10_

6) _Docker_

7) _PostgreSQL_ 

8) _Flyway_ 

9) _Bootstrap_ 

10) _Eureka_

11) _Ribbon_

12) _Zuul_
