import java.util.LinkedList;
import java.util.Stack;

public class Kosaraju {
    private Graph<String> graph;
    private int[] sccs; // Array to store the SCC IDs of each node
    private boolean[] visited;
    private Stack<Integer> finishStack;

    public Kosaraju(Graph<String> graph) {
        this.graph = graph;
        int n = graph.order();
        sccs = new int[n];
        visited = new boolean[n];
        finishStack = new Stack<>();
    }

    public int[] sccs() {
        int n = graph.order();

        // Step 1: Fill the finish stack with nodes based on their finishing time
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsFill(i);
            }
        }

        // Step 2: Reverse the graph
        Graph<String> reverseGraph = reverseGraph(graph);

        // Step 3: Perform DFS on reversed graph in the order of finishStack
        visited = new boolean[n]; // Reset visited array for the second DFS
        int sccCount = 0;

        while (!finishStack.isEmpty()) {
            int node = finishStack.pop();
            if (!visited[node]) {
                dfsAssign(reverseGraph, node, sccCount);
                sccCount++;
            }
        }

        return sccs;
    }

    // DFS to populate the finish stack
    private void dfsFill(int node) {
        visited[node] = true;
        for (int neighbor : graph.getVoisins(node)) {
            if (!visited[neighbor]) {
                dfsFill(neighbor);
            }
        }
        finishStack.push(node);
    }

    // DFS to assign SCC IDs
    private void dfsAssign(Graph<String> reverseGraph, int node, int sccID) {
        visited[node] = true;
        sccs[node] = sccID;
        for (int neighbor : reverseGraph.getVoisins(node)) {
            if (!visited[neighbor]) {
                dfsAssign(reverseGraph, neighbor, sccID);
            }
        }
    }

    // Method to reverse the graph
    private Graph<String> reverseGraph(Graph<String> graph) {
        Graph<String> reverseGraph = new Graph<>(graph.order());
        try {
            for (int i = 0; i < graph.order(); i++) {
                for (int neighbor : graph.getVoisins(i)) {
                    reverseGraph.addArc(neighbor, i, ""); // Reverse the direction of the arc
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reverseGraph;
    }

    // Check consistency directly in the Kosaraju class
    public boolean checkConsistency() {
        int n = sccs.length / 2;

        for (int i = 0; i < n; i++) {
            if (sccs[i] == sccs[i + n]) {
                return false; // Unsatisfiable if variable and its negation are in the same SCC
            }
        }
        return true; // Satisfiable if no conflict
    }
}
