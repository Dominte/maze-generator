public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30);


        MazeSolution mazeSolution = new MazeSolution(mazeGenerator.generateMazeWithoutStates(),1,1,1,2);
        System.out.println(mazeSolution.getSymbolicMaze());

        mazeSolution.getSpecialStates();
        mazeSolution.backTrackingMaze();

    }
}