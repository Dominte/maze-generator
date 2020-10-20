public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30);

        //BFS bfs = new BFS(mazeGenerator.generateMazeWithoutStates(),1,3,1,3);
        //bfs.Start();
        //System.out.println(bfs.getSymbolicMaze());

        /* MazeSolution mazeSolution = new MazeSolution(mazeGenerator.generateMazeWithoutStates(),1,1,3,3);
        System.out.println(mazeSolution.getSymbolicMaze());

        mazeSolution.getSpecialStates();
        mazeSolution.backTrackingMaze(); */

        MazeSolution mazeSolution = new MazeSolution(mazeGenerator.generateMazeWithoutStates(),1,1,4,4);
        System.out.println(mazeSolution.getSymbolicMaze());
        mazeSolution.hillClimbing();
        System.out.println("-----------------------------------------------------");
        System.out.println(mazeSolution.getSymbolicMaze());


    }
}