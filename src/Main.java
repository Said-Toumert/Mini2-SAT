import java.io.IOException;
import java.util.Stack;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {

        String filename = "formulas/formule-2-sat.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser parser = new Parser();
        parser.parseFile(filename);
        System.out.println(parser.printClauses());
        ImplicationsGraphe graphe = new ImplicationsGraphe(parser.getNbVariables());

        graphe.grapheimplication(parser);
        System.out.println(graphe.toString());
        //DFS dfs = new DFS(graphe.order());
        Kosaraju SCC = new Kosaraju(graphe);
        System.out.println( SCC.findStronglyConnectedComponents());




    }


}
