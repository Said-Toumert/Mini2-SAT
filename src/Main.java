import java.io.IOException;
import java.util.Stack;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {
// Vérification si un fichier est fourni en ligne de commande
        if (args.length == 0) {
            System.out.println("Veuillez fournir un fichier                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       en argument.");
            exit(1);  // On arrête le programme si aucun fichier n'est fourni
        }

        String filename = args[0];  // Le fichier spécifié en argument

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
        Kosaraju scc = new Kosaraju(graphe);

        if(TwoSat.checkConsistency(scc.findSCCs())){
            System.out.println("Satisfiable");
        }else System.out.println("not satisfiable");




    }


}
