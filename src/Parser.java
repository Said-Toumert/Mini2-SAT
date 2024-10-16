import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private int nbVariables;
    private int nbClauses;
    private ArrayList<int[]> clauses;


    public ArrayList<int[]> getClauses() {
        return clauses;
    }
    public int getNbClauses() {
        return nbClauses;
    }
    public int getNbVariables() {
        return nbVariables;
    }


    public Parser(){
        this.clauses = new ArrayList<int[]>();
    }


    public void parseFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = reader.readLine()) != null){
            /// retirer les espaces au debut et fin de la ligne
            line = line.trim();

            /// pour ignorer la ligne cmmancant par un "C"
            if(line.startsWith("c")) continue;
            else if (line.startsWith("p")) {
                String[]parts =  line.split("\\s+");
                nbVariables = Integer.parseInt(parts[2]);
                nbClauses = Integer.parseInt(parts[3]);
            }
            else{
                String[] literals = line.split("\\s+");
                int [] clause = new int[2];
                for(int index = 0; index < literals.length-1; index++){
                    clause[index] = Integer.parseInt(literals[index]);
                }
                clauses.add(clause);
            }

        }

    }

    public String printClauses() {
        StringBuilder result = new StringBuilder();
        for (int[] clause : clauses) {
            result.append("[").append(clause[0]).append(",").append(clause[1]).append("]").append("\n");
        }
        return result.toString();
    }


}
