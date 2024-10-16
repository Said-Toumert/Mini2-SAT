import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label>  {

    class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
        public int getSource(){
            return this.source;
        }
        public int getDestination(){
            return this.destination;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) throws Exception {
        if (Math.max(source,dest) >= this.cardinal){
            throw new Exception("Sommets trop gros pour la taille du graphe");
        }
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = new String("");
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");
        for (int i = 0; i<cardinal;i++) {
            result = result.concat(i + " ");
        }
        result = result.concat("\nArcs : \n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " -> " + e.destination + /*", étiquette : "
                        + e.label.toString() +*/ "\n");
            }
        }
        return result;

    }



    // Méthode pour obtenir la liste des arêtes sortantes d'un sommet donné
    public LinkedList<Edge> getAdjacencyList(int vertex) {
        return incidency.get(vertex); // Retourne la liste des arêtes sortantes
    }


    public Graph<String> transpose() throws Exception {
        Graph<String> transposedGraph = new Graph<>(this.cardinal);

        for (int i = 0; i < this.cardinal; i++) {
            for (Edge e : this.incidency.get(i)) {
                transposedGraph.addArc(e.destination, e.source,"->" );
            }
        }

        return transposedGraph;
    }


}
