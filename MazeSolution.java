import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class MazeSolution {
    int[][] maze;
    int[][] reset;
    int dimension;
    Node finish;
    Node start;
    int pas;

    MazeSolution(int[][] maze) {
        this.maze = maze;
        dimension = maze.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.maze[i][j] == -3)
                    start = new Node(i, j);
                if (this.maze[i][j] == -2)
                    finish = new Node(i, j);
            }
        }
    }

    MazeSolution(int[][] maze, int xStart, int xFinish, int yStart, int yFinish) {
        this.maze = maze;
        this.reset= maze;
        dimension = maze.length;
        this.maze[xStart][xFinish] = -3;
        this.maze[yStart][yFinish] = -2;
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

    public boolean isInitialStateSolved() {
        return finish.x == start.x && finish.y == start.y;
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
                    sb.append("▀");
                else if (maze[i][j] == -3)
                    sb.append("S");
                else
                    sb.append(" ");

                sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isSafe(int x, int y) {
        if (x >= 0 && x < dimension && y >= 0 && y < dimension && maze[x][y] == 0)
            return true;
        if (x >= 0 && x < dimension && y >= 0 && y < dimension && maze[x][y] == -2)
            return true;
        if (x >= 0 && x < dimension && y >= 0 && y < dimension && maze[x][y] == -3)
            return true;
        return false;
    }

    public void backTrackingMaze() {
        pas = 0;
        if (solveMaze(start.x, start.y)) {
            System.out.println("Solution exists");

        } else System.out.println("Solution doesn't exist");
        ;
    }

    public boolean solveMaze(int x, int y) {
        if (x == finish.x && y == finish.y) {
            return true;
        }

        if (isSafe(x, y)) {
            maze[x][y] = 1;

            if (solveMaze(x + 1, y))
                return true;
            if (solveMaze(x, y + 1))
                return true;
            if (solveMaze(x, y - 1))
                return true;
            if (solveMaze(x - 1, y))
                return true;

            maze[x][y] = 0;
            return false;
        }

        return false;
    }

    public ArrayList<Node> expandNode(Node n) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        if (isSafe(n.x + 1, n.y))
            nodes.add(new Node(n.x + 1, n.y));
        if (isSafe(n.x - 1, n.y))
            nodes.add(new Node(n.x - 1, n.y));
        if (isSafe(n.x, n.y + 1))
            nodes.add(new Node(n.x , n.y + 1));
        if (isSafe(n.x, n.y - 1))
            nodes.add(new Node(n.x, n.y - 1));

        return nodes;
    }

    public int heuristic(Node n) {
        return abs(n.x - finish.x) + abs(n.y - finish.y);
    }


    public boolean hillClimbing() {
            Node n = start;
            if (n.x == finish.x && n.y == finish.y)
                return true;

        int currentHeuristic=heuristic(n);

        ArrayList<Node> nodes = expandNode(n);
        int min = dimension * dimension + 1;
        for (Node node : nodes)
            if (heuristic(node) < min)
                min = heuristic(node);

        if(min>currentHeuristic)
            return false;

        int finalMin = min;
        nodes.removeIf((Node node) -> heuristic(node)!= finalMin);

        boolean result=false;

        for(Node node: nodes)
            result= result | hillClimbingUtil(node);

        if(result) {
            System.out.println("Solution found");
            return true;
        }

        System.out.println("No solution found");
        return false;
    }

    public boolean hillClimbingUtil(Node n) {

        if (n.x == finish.x && n.y == finish.y)
            return true;

        maze[n.x][n.y]=1;
        int currentHeuristic=heuristic(n);

        ArrayList<Node> nodes = expandNode(n);
        int min = dimension * dimension + 1;
        for (Node node : nodes)
            if (heuristic(node) < min)
                min = heuristic(node);

        if(min>currentHeuristic)
            return false;

        int finalMin = min;
        nodes.removeIf((Node node) -> heuristic(node)!= finalMin);

        boolean result=false;

        for(Node node: nodes)
            result= result | hillClimbingUtil(node);

        return result;
    }


}
