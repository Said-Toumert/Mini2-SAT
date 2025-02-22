Said Toumert
Melissa Messaoudi
Rapport sur l'implémentation de l'algorithme de Kosaraju et du graphe d'implication

Date : 2024-10-16
compiler :
javac -d out src/*.java
executer:
java -cp out Main formulas/testSet0/formula7.txt

1. Introduction

Le projet a consisté à implémenter un algorithme permettant de détecter les composantes fortement connexes (CFC) dans un graphe,
en particulier dans le cadre de la résolution du problème 2-SAT. Nous avons utilisé l'algorithme de Kosaraju, qui se base sur
deux parcours en profondeur (DFS) et l'inversion des arêtes du graphe. Le projet s'est articulé autour de plusieurs classes spécifiques,
chacune jouant un rôle bien défini pour organiser et structurer les données, ainsi que pour exécuter les algorithmes.
²
2. Principe de fonctionnement

L'algorithme de Kosaraju est un moyen efficace de trouver les CFC d'un graphe. Il est basé sur deux passes de DFS :
- Première passe DFS : Parcourir le graphe et enregistrer l'ordre d'achèvement des sommets dans une pile. Cet ordre permet d'explorer les sommets dans l'ordre inverse de leur achèvement lors de la deuxième passe.
- Inversion du graphe : Une fois la première passe effectuée, on inverse les arêtes du graphe, c'est-à-dire que chaque arête allant de A à B devient une arête allant de B à A.
- Deuxième passe DFS : On réexécute un DFS sur le graphe inversé, en suivant l'ordre des sommets donné par la pile. Chaque DFS révèle une composante fortement connexe.

3. Classes principales

3.1 Classe `Graph`

La classe Graph est une structure générale pour représenter un graphe orienté. Chaque sommet est associé à une liste chaînée d'arêtes (LinkedList), permettant d'accéder facilement aux voisins.

- Attributs :
    - `adjacencyList`: Représente les voisins de chaque sommet.
    - `order`: Nombre de sommets dans le graphe.

- Méthodes :
    - `addArc(int from, int to)`: Ajoute une arête orientée du sommet `from` vers le sommet `to`.
    - `transpose()`: Inverse les arêtes du graphe.

3.2 Classe `ImplicationsGraphe`

La classe ImplicationsGraphe hérite de la classe Graph et est dédiée spécifiquement à la construction d’un graphe d’implication pour résoudre des problèmes de 2-SAT. Elle se base sur des clauses logiques et traduit chaque clause sous forme d’arêtes dans le graphe.

- Attributs :
    - Hérite de `Graph`, mais avec une logique adaptée aux problèmes d'implication.

- Méthodes :
    - `buildGraph(Parser parser)`: Construit le graphe à partir des clauses fournies par la classe `Parser`.

3.3 Classe `Parser`

La classe Parser permet de lire des clauses à partir d'un fichier d'entrée. Ces clauses sont ensuite utilisées par la classe `ImplicationsGraphe` pour construire le graphe d'implication.

- Attributs :
    - `filename`: Nom du fichier à lire.
    - `clauses`: Liste des clauses lues à partir du fichier.

- Méthodes :
    - `parse()`: Lit les clauses depuis le fichier et les retourne sous forme d'une structure exploitable pour la construction du graphe.

3.4 Classe `DepthFirstSearch`

La classe DepthFirstSearch encapsule la logique du parcours en profondeur (DFS). Elle est utilisée à la fois pour la première et la deuxième passe de l'algorithme de Kosaraju.

- Attributs :
    - `visited`: Tableau de booléens pour marquer les sommets visités.
    - `finishStack`: Pile pour enregistrer l'ordre d'achèvement des sommets (utilisé lors de la première passe).

- Méthodes :
    - `dfsFirstPass()`: Parcourt le graphe original et enregistre l'ordre d'achèvement des sommets.
    - `dfsSecondPass()`: Parcourt le graphe transposé pour détecter les composantes fortement connexes.

3.5 Classe `Kosaraju`

La classe Kosaraju est le cœur de l'algorithme. Elle utilise les méthodes DFS et les opérations sur le graphe pour identifier les composantes fortement connexes.

- Attributs :
    - `graph`: Le graphe à analyser.
    - `componentIds`: Tableau qui stocke l'identifiant de la composante pour chaque sommet.

- Méthodes :
    - `findSCCs()`: Méthode principale qui exécute les deux passes de DFS, transpose le graphe, et identifie les CFC.

4. Logique suivie

1. Lecture des données : Les clauses logiques sont extraites d'un fichier à l'aide de la classe Parser. Ces clauses sont ensuite utilisées pour construire un graphe d'implication via la classe ImplicationsGraphe.
2. Première passe DFS : Un parcours en profondeur est effectué sur le graphe original afin de déterminer l'ordre de fin des sommets, qui est stocké dans une pile.
3. Inversion du graphe : Le graphe d'implication est ensuite transposé, c’est-à-dire que toutes ses arêtes sont inversées.
4. Deuxième passe DFS : Un second parcours en profondeur est effectué sur le graphe transposé, en suivant l'ordre des sommets dans la pile. Chaque composante fortement connexe est identifiée et marquée dans le tableau `componentIds`.
5. Résolution du problème 2-SAT : À partir des composantes fortement connexes identifiées, il est possible de déterminer si le problème 2-SAT possède une solution ou non. Si deux littéraux opposés appartiennent à la même CFC, alors le problème est insatisfiable.





Problème 1 : Indexation incorrecte des littéraux

Problème :
Dans la méthode literalToIndex, l'indexation des littéraux positifs et négatifs est gérée de manière manuelle, ce qui peut provoquer des erreurs si les littéraux dépassent la taille allouée. Par exemple, si les valeurs des littéraux dépassent l'ordre du graphe, cela pourrait causer un dysfonctionnement.

Solution :
Il faut s'assurer que les indices générés sont dans les limites du graphe en ajoutant des vérifications supplémentaires. On peut lever une exception si un littéral donné est hors des limites. Ainsi, on garantit que l'indice est correct avant d'ajouter une implication.

Probleme 2 : 
Affichage incorrect des composantes connexes quand cest pas satisfaisable,
solution : 
creaction d'une fonction get_compo qui affiche les composantes


probleme 3: 

5. Conclusion

Le projet a permis d'implémenter un algorithme efficace pour détecter les composantes fortement connexes,
en particulier dans le contexte de la résolution du problème 2-SAT. La séparation en classes a permis une organisation
claire du code, facilitant la gestion du graphe, du parcours en profondeur, et de l'analyse des résultats. Les structures de
données utilisées, telles que les listes chaînées pour les arêtes et les piles pour le stockage temporaire des sommets, ont offert
une gestion efficace de la mémoire et du temps d'exécution. Le projet respecte ainsi une complexité temporelle de O(V + E),
ce qui en fait une solution adaptée pour les grands graphes.

