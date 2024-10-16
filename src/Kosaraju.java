import java.util.*;

public class Kosaraju {

    private Graph<String> graph;
    private DFS dfsHelper;

    // Constructeur prenant le graphe en paramètre
    public Kosaraju(Graph<String> graph) {
        this.graph = graph;
        this.dfsHelper = new DFS(graph.order());
    }

    // Méthode principale pour trouver les composantes fortement connexes
    public int[] findSCCs() throws Exception {
        int n = graph.order();

        // Première passe DFS sur le graphe original pour remplir la pile
        for (int i = 0; i < n; i++) {
            if (!dfsHelper.getVisited()[i]) {
                dfsHelper.dfsFirstPass(graph, i);
            }
        }

        // Transposer le graphe
        Graph<String> transposedGraph = graph.transpose();

        // Réinitialiser le tableau de visités pour la deuxième passe
        dfsHelper.resetVisited();

        // Deuxième passe DFS sur le graphe transposé
        Stack<Integer> finishStack = dfsHelper.getFinishStack();
        while (!finishStack.isEmpty()) {
            int vertex = finishStack.pop();

            if (!dfsHelper.getVisited()[vertex]) {
                dfsHelper.dfsSecondPass(transposedGraph, vertex);
                dfsHelper.incrementComponent();
            }
        }

        return dfsHelper.getComponentIds();  // Retourner les identifiants des composantes
    }
}
