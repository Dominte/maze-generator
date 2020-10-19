import java.util.Arrays;

public class MazeSolution {
    int[][] maze;
    int dimension;
    Node finish;
    Node start;

    MazeSolution(int[][] maze) {
        this.maze = maze;
        dimension = maze.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze[i][j] == 1)
                    start = new Node(i, j);
                if (maze[i][j] == -2)
                    finish = new Node(i, j);
            }
        }
    }

    MazeSolution(int[][] maze, int xStart, int xFinish, int yStart, int yFinish) {
        this.maze = maze;
        dimension = maze.length;
        maze[xStart][xFinish]=1;
        maze[yStart][yFinish]=-2;
        start = new Node(xStart, xFinish);
        finish = new Node(yStart, yFinish);
    }

    public void getSpecialStates() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze[i][j] == 1)
                    System.out.println("Initial state: i:" + i + " j:" + j);
                if (maze[i][j] == -2)
                    System.out.println("Final state: i:" + i + " j:" + j);
            }
        }
    }

    public boolean isSolved() {

        if (maze[finish.x - 1][finish.y] != -1 && maze[finish.x - 1][finish.y] != 0)
            return true;
        if (maze[finish.x][finish.y - 1] != -1 && maze[finish.x - 1][finish.y] != 0)
            return true;
        if (maze[finish.x][finish.y + 1] != -1 && maze[finish.x - 1][finish.y] != 0)
            return true;
        if (maze[finish.x + 1][finish.y] != -1 && maze[finish.x - 1][finish.y] != 0)
            return true;

        return false;
    }

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                if (maze[i][j] == -1)
                    sb.append("*");
                else if (maze[i][j] == -2)
                    sb.append("F");
                else if (maze[i][j] == 1)
                    sb.append("S");
                else
                    sb.append(" ");

                sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
