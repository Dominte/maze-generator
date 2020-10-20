public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30);

        BFS bfs = new BFS(mazeGenerator.generateMazeWithoutStates(),1,1,4,4);
        bfs.Start();
        System.out.println(bfs.getSymbolicMaze());

        mazeGenerator.reset();

        MazeSolution BKT = new MazeSolution(mazeGenerator.generateMazeWithoutStates(),1,1,4,4);
        BKT.backTrackingMaze();
        System.out.println("-----------------------------------------------------");
        System.out.println(BKT.getSymbolicMaze());

        mazeGenerator.reset();

        MazeSolution HC = new MazeSolution(mazeGenerator.generateMazeWithoutStates(),1,1,4,4);
        HC.hillClimbing();
        System.out.println("-----------------------------------------------------");
        System.out.println(HC.getSymbolicMaze());


    }
}