package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        // Add more variables here!
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        int next;
        while (true) {
            next = queue.remove();
            if (next == t) {
                targetFound = true;
                return;
            }
//            if (targetFound == true) {
//                return;
//            }
            for (int w: maze.adj(next)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.add(w);
                    edgeTo[w] = next;
                    distTo[w] = distTo[next] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

