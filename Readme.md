# Projet 7


# Formation "Développeur d'application JAVA", OpenClassrooms.

	Création d'un site de gestion de bibliothèque de la ville.


## Le projet :

**Le site web**<br/>
Le but est de permettre aux usagers de suivre les prêts de leur ouvrages à travers une
interface web:
* Rechercher des ouvrages et voir le nombre d'exemplaire disponible.
* Consulter leurs prêts en cours.
* Le prêt d'un ouvrage est prolongeable une seule fois.
  La prolongation ajoute une nouvelle période de prêt(4 semaines) à la période initiale.


**Un batch**<br/>
Ce logiciel pour le traitement automatisé permettra d'envoyer des mails de relance
aux usagers n'ayant pas rendu les livres en fin de période de prêt. L'envoi sera automatique
à la fréquence d'un par jour.

**L'API web**<br/>
Le site web ainsi que le batch communiqueront avec ce logiciel en REST afin de connaitre
les informations liées à la base de donnée.


## Les contraintes fonctionnelles

	- Application web avec un framework MVC.
	- API web en microservices REST (Les clients (site web, batch) communiqueront avec cette API web) 
		-> factorisation de la logique métier.
	- Packaging avec Maven.

## Développement

	Cette application a été développée avec :
	- Intellij IDEA
	- Java 12 (version xxxxXXXXXXXX)
	- Tomcat 9
	- MySQL (version 8.0.21)
	- le framework Spring (version 5.2.1)
	- Spring boot
	- LOMBOK
	- Spring DATA JPA

L'application a été développée suivant une architecture microservice


![architecture diagram](Architecture-P7-Diagram.jpg)


## Déploiement

1- Importez tous les microservices du repository.

2- Créez une base de données pour chaque microservice. Les scripts sont dans le dossier "livrables/script".
Vous devez modifier les propriétés de la datasource dans chaque fichier "application.properties" de chaque
microservice (dans src/main/resources/).

3- Lancez l'application : démarrez "library-eureka" en premier et "library-batch" en dernier.

Dans votre navigateur, vous pourrez accéder au site à l'adresse localhost:9090, si vous n'avez pas modifié
les propriétés "server.port".

## Auteur

Olivier Morlotti