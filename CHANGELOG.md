# Historique des changements

(TODO correspond à l'identifiant d'un problème à traiter pour la prochaine version)

## TODO - Blindage de la construction des arcs

On remarque que l'on dispose d'un modèle qui ne protège pas contre les erreurs la création des sommets et des arcs. Il est tout à fait possible de créer par erreur un `Edge` avec une `source` ou une `target` nulle.

On procède comme suit :

* [ ] Ajout d'un constructeur `Edge(source: Vertex, target: Vertex)`
* [ ] Suppression du constructeur par défaut sur `Edge`




