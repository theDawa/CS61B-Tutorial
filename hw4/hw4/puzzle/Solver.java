package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private MinPQ<SearchNode> a;
    private int totMoves;
    private SearchNode node;
    private ArrayList<WorldState> b;
    private static class SearchNode implements Comparable<SearchNode> {
        public WorldState current;
        public SearchNode previous;
        public int m;

        public SearchNode(WorldState current, int m, SearchNode previous){
            this.current = current;
            this.previous = previous;
            this.m = m;
        }

        public int getMoveToState(){
            return m;
        }


        @Override
        public int compareTo(SearchNode o) {
            return this.m + this.current.estimatedDistanceToGoal() - o.m - o.current.estimatedDistanceToGoal();
        }
    }
    private void getAnswer(SearchNode goal) {
        totMoves = goal.m;
        b = new ArrayList<>();
        SearchNode p = goal;
        while (p != null) {
            b.add(p.current);
            p = p.previous;
        }
    }
    public Solver(WorldState initial){

        a = new MinPQ<>();
        a.insert(new SearchNode(initial, 0, null));
        while(true){
            SearchNode node = a.delMin();
            if(node.current.isGoal()){
                getAnswer(node);
                return;
            }
            else{
                for(WorldState i : node.current.neighbors()) {
                    if(node.previous == null || !i.equals(node.previous.current)){
                        a.insert(new SearchNode(i, node.m + 1, node));
                    }
                }
            }
        }

    }
    public int moves(){
        return totMoves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> ret = new ArrayList<>();
        for (int i = totMoves; i >= 0; i--) {
            ret.add(b.get(i));
        }
        return ret;
    }

}
