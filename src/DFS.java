import java.util.*;

public class DFS {

    private boolean[] visited;
    private Stack<Integer> finishStack;
    private int[] componentIds;
    private int currentComponent;

    // Constructeur pour initialiser les attributs
    public DFS(int graphSize) {
        this.visited = new boolean[graphSize];
        this.finishStack = new Stack<>();
        this.componentIds = new int[graphSize];
        this.currentComponent = 0;
    }

    // Getter pour le tableau de composants
    public int[] getComponentIds() {
        return componentIds;
    }

    // Getter pour la pile des temps de fin
    public Stack<Integer> getFinishStack() {
        return finishStack;
    }

    // Getter pour le tableau de visités
    public boolean[] getVisited() {
        return visited;
    }

    public boolean isVisited(int vertex) {
        return visited[vertex];
    }

    // Réinitialiser le tableau de visités pour la deuxième passe
    public void resetVisited() {
        Arrays.fill(visited, false);
    }

    // Incrémenter l'identifiant de la composante actuelle
    public void incrementComponent() {
        currentComponent++;
    }

    // DFS pour la première passe : remplir la pile des temps de fin
    public void dfsFirstPass(Graph<String> graph, int vertex) {
        visited[vertex] = true;

        for (Graph<String>.Edge e : graph.getAdjacencyList(vertex)) {
            if (!visited[e.destination]) {
                dfsFirstPass(graph, e.destination);
            }
        }

        finishStack.push(vertex);  // Enregistrer le sommet à la fin de son traitement
    }

    // DFS pour la deuxième passe : explorer les CFCs sur le graphe transposé
    public void dfsSecondPass(Graph<String> transposedGraph, int vertex) {
        visited[vertex] = true;
        componentIds[vertex] = currentComponent;  // Marquer le sommet avec l'identifiant de la composante

        for (Graph<String>.Edge e : transposedGraph.getAdjacencyList(vertex)) {
            if (!visited[e.destination]) {
                dfsSecondPass(transposedGraph, e.destination);
            }
        }
    }
}
