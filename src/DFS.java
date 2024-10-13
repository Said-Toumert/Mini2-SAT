import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DFS {
    private boolean[] visited;
    private Stack<Integer> finishOrder;

    public DFS(int numVertices) {
        this.visited = new boolean[numVertices];
        this.finishOrder = new Stack<>();
    }

    public void performDFS(Graph graph, int v) {
        visited[v] = true;
        LinkedList<Integer> voisins = graph.getVoisins(v);
        for (int neighbor : voisins) {
            if (!visited[neighbor]) {
                performDFS(graph, neighbor);
            }
        }
        finishOrder.push(v);
    }

    public Stack<Integer> getFinishOrder() {
        return finishOrder;
    }

    public boolean isVisited(int v) {
        return visited[v];
    }
}
