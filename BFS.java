import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    int[][] maze;
    Node finish;
    Node start;
    Queue<Node> queue = new LinkedList<Node>();
    boolean ok = false;
    int[] moveX = {-1, 0, 1, 0};
    int[] moveY = {0, 1, 0, -1};

    public BFS(int[][] maze, int xStart, int xFinish, int yStart, int yFinish) {
        this.maze = maze;

        start = new Node(xStart, xFinish);
        finish = new Node(yStart, yFinish);
        this.maze[finish.x][finish.y] = -2;
    }

    public void Start() {
        queue.add(start);
        maze[start.x][start.y] = 1;
        Iterate();
    }

    private void Iterate() {
        while (queue.size() > 0 && !ok) {
            Node current = queue.remove();
            for (int i = 0; i < 4; i++) {
                if (current.x + moveX[i] >= 0 && current.x + moveX[i] < maze.length
                        && current.y + moveY[i] >= 0 && current.y + moveY[i] < maze.length
                        && maze[current.x + moveX[i]][current.y + moveY[i]] == 0) {
                    queue.add(new Node(current.x + moveX[i], current.y + moveY[i]));
                    maze[current.x + moveX[i]][current.y + moveY[i]] = maze[current.x][current.y] + 1;
                }
            }
            if (isSolved()) {
                ok = true;
                break;
            }
        }
    }

    public boolean isSolved() {

        if (maze[finish.x - 1][finish.y] != -1 && maze[finish.x - 1][finish.y] > 0)
            return true;
        if (maze[finish.x][finish.y - 1] != -1 && maze[finish.x][finish.y - 1] > 0)
            return true;
        if (maze[finish.x][finish.y + 1] != -1 && maze[finish.x][finish.y + 1] > 0)
            return true;
        if (maze[finish.x + 1][finish.y] != -1 && maze[finish.x + 1][finish.y] > 0)
            return true;

        return false;
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {

                if (maze[i][j] == -1)
                    sb.append("*");
                else if (maze[i][j] == -2)
                    sb.append("F");
                else if (maze[i][j] == 1)
                    sb.append("S");
                else if (maze[i][j] == 0) sb.append(" ");
                else if (maze[i][j] < 10) sb.append("" + maze[i][j]);
                else sb.append(maze[i][j]);

                sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}