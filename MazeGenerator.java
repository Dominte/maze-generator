import java.util.*;

class MazeGenerator {

    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private final int dimension;

    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
    }

    public void reset() {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze.length; j++)
                if (maze[i][j] != 0 && maze[i][j] != -1)
                    maze[i][j] = 0;
    }

    public int[][] generateMazeWithoutStates() {
        stack.push(new Node(0, 0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = -1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }

        return maze;
    }

    public int[][] generateMaze() {
        stack.push(new Node(0, 0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = -1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }

        Random random = new Random();
        int xStart = random.nextInt(30);
        int yStart = random.nextInt(30);
        int xFinish = random.nextInt(30);
        int yFinish = random.nextInt(30);

        maze[xStart][yStart] = -3;
        maze[xFinish][yFinish] = -2;

        return maze;
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

    private boolean validNextNode(Node node) {
        int numNeighboringOnes = 0;
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == -1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[node.y][node.x] != -1;
    }

    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(node, x, y)
                        && pointNotNode(node, x, y)) {
                    neighbors.add(new Node(x, y));
                }
            }
        }
        return neighbors;
    }

    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }

    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }
}