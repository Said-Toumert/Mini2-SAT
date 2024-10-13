import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Kosaraju {
    private Graph graph;

    public Kosaraju(ImplicationsGraphe graph) {
        this.graph = graph;
    }

    public ArrayList<ArrayList<Integer>> findStronglyConnectedComponents() throws Exception {
        // Premier DFS
        DFS dfs = new DFS(graph.order());
        for (int v = 0; v < graph.order(); v++) {
            if (!dfs.isVisited(v)) {
                dfs.performDFS(graph, v);
            }
        }

        Graph transposedGraph = graph.transpose();
        ArrayList<ArrayList<Integer>> stronglyConnectedComponents = new ArrayList<>();
        Stack<Integer> finishOrder = dfs.getFinishOrder();

        dfs = new DFS(transposedGraph.order());

        while (!finishOrder.isEmpty()) {
            int v = finishOrder.pop();
            if (!dfs.isVisited(v)) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs.performDFS(transposedGraph, v);

                for (int i = 0; i < transposedGraph.order(); i++) {
                    if (dfs.isVisited(i)) {
                        component.add(i);
                    }
                }
                stronglyConnectedComponents.add(component);
            }
        }

        return stronglyConnectedComponents;
    }
}
