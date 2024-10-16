import java.sql.SQLOutput;
import java.util.ArrayList;

public class ImplicationsGraphe extends Graph{

    public ImplicationsGraphe(int nbvariables) {

        super(2 * nbvariables);
    }

    /// pour indexer les litereaux, les positif de 0 jusqua n-1/2, les negatifs de n/2 jusqu' a n-1
    private int  literalToIndex(int literal){
        int index;
        if(literal > 0){
            index = literal - 1;
        }else{
            index = -literal - 1 + this.order()/2;
        }
        return index;
    }
    private void addImplication(int literalSource, int literalTarget) throws Exception{
        int source = literalToIndex(literalSource);
        int target = literalToIndex(literalTarget);
        this.addArc(source, target, null);
    }

    public void grapheimplication(Parser parser) throws Exception{
        ArrayList<int[]> clauses = parser.getClauses();

        for(int[] clause : clauses){
            int literal1 = clause[0];
            int literal2 = clause[1];
            addImplication(-literal1, literal2);
            addImplication(-literal2, literal1);
        }
    }
}
