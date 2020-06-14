package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    public int[] edgeTo;
    */
    private int s;
    private Maze maze;
    private int lastind;
    private int beginind;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private void dfs(int v) {
        marked[v] = true;
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                distTo[w] = distTo[v] + 1;
                edgeTo[w] = v;
                announce();
                dfs(w);
            }
            if (marked[w] && w != edgeTo[v]) {
                lastind = v;
                beginind = w;
                edgeTo[w] = v;
                announce();
                return;
//                edgeTo[w] = lastind;
            }
        }
    }

    @Override
    public void solve() {
        dfs(s);
//        drawcircle();
    }

    // Helper methods go here
//    private void setting(int x) {
//        int pre;
//        pre = edgeTo[x];
//        if (x == pre) {
//            return;
//        }
//        edgeTo[x] = x;
//        setting(pre);
//    }
//    private void drawcircle() {
//        setting(beginind);
//        edgeTo[beginind] = lastind;
//        announce();
//    }

}

