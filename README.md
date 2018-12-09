# tp-refactoring-graph


## Description

Correction du [tp-refactoring-graph](http://mborne.github.io/cours-patron-conception/annexe/tp-graph/index.html) associé au cours 
sur [les patrons de conception](http://mborne.github.io/cours-patron-conception/)


## Principes

* La branche par défaut ("initial") permet de récupérer un projet maven pour commencer à travailler
* Les branches 0.1, 0.2, etc. correspondent aux corrections pour chaque question du TP
* junit et mockito sont présents pour permettre l'écriture de tests unitaires


## Organisation du code

Le projet contient deux points d'entrée :

* `cli.FindPath` : Calcul de plus court chemin en ligne de commande (CLI = *Command Line Interpreter*)
* `Application` : Application sous forme d'une API springboot

Le code est organisé en package :

* `model` : Classes correspondant à la modélisation des données
* ̀`io` : Lecture/écriture de graphe dans différents formats
* `routing` : Classes correspondant aux algorithmes de calcul de plus court chemin
* `controllers` : Contrôleurs de l'application springboot
* `config` : Configuration de l'application springboot (initialisation des beans)


## Remarque

Un extrait de [ROUTE500](http://professionnels.ign.fr/route500) est présent dans `src/test/resources/idf/troncon_route.shp` à des fins de tests.


### En mode ligne CLI

Lancer l'application `cli.FindPath` dans eclipse.

### En mode API

Lancer "Application.java" dans eclipse ou :

```bash
mvn clean package
java -cp target -jar target/tp-refactoring-graph-0.1.0-SNAPSHOT.jar
http://localhost:8080/find-path?origin=a&destination=c
```
