import java.util.*;
import java.io.*;

public class Exercicio1 {


    public List<Node> graph = new ArrayList<>();
    public int longestPath = 0;
    public int thisPath = 0;
    public int _nodeValue = 0;

    public void executeAllDfs(){
        for(Node node : graph) {

//            System.out.print("NODE DATA " + node.data + " ");
            dfs(node);
            for(Node n: graph){
                n.visited = false;
            }

        }

        System.out.println(longestPath);

    }

    // Recursive DFS
    public void dfs (Node node)
    {
        _nodeValue = node.data;

//        System.out.print("NODE DATA " + node.data + " ");
        List<Node> neighbours = node.getNeighbours();
        node.visited = true;
//        System.out.println("NEIGHBOURS SIZE " + neighbours.size());
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
//            System.out.println("THE I IS " + i);
            if(n!=null && !n.visited)
            {
//                System.out.print("NODE DATA " + node.data + " ");
//                System.out.print("THIS PATH " + thisPath + " ");
                thisPath++;
                dfs(n);
                thisPath--;
            }
        }
//        System.out.println("THISPATH " + thisPath);
//        System.out.println("LONGESTPATH " + longestPath + "\n");
        if(longestPath < thisPath) {
            longestPath = thisPath;
        }

//        thisPath = 0;
    }

    public Node getNode(int nodeValue) {
        for (Node node : graph) {
            if (node.getValue() == (nodeValue)) {
                return node;
            }
        }
        return null;
    }

    public Node getNode(int i, int j) {
        for(Node node: graph) {
            int []coordinates = node.getCoordinates();
            if(coordinates[0] == i && coordinates[1] == j) {
                return node;
            }
        }
        return null;
    }

    public void getAllNeighbours(char [][]maze, int r, int c) {
        int nodeCounter = 0;
        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == '.') {
//                    Node node = getNode(nodeCounter);
                    Node node = getNode(i, j);
//                    System.out.println("NODE VALUE " + node.data);
//                    System.out.println("POSITION " + i + " " + j);
                    //check up
//                    System.out.print("NODE DATA ORIGINAL " + node.data + " ");
                    if(i-1 >= 0 && i-1 < r && maze[i-1][j] != '#'){
                        Node neighbour = getNode(i-1, j);
//                        System.out.print("NODE DATA UP " + neighbour.data + " ");
                        node.addNeighbours(neighbour);
                    }
                    //check right
                    if(j+1 >= 0 && j+1 < c && maze[i][j+1] != '#'){
                        Node neighbour = getNode(i, j+1);
//                        System.out.print("NODE DATA RIGHT " + neighbour.data + " ");
                        node.addNeighbours(neighbour);
                    }
                    //check down
                    if(i+1 >= 0 && i+1 < r && maze[i+1][j] != '#'){
                        Node neighbour = getNode(i+1, j);
//                        System.out.print("NODE DATA DOWN " + neighbour.data + " ");
                        node.addNeighbours(neighbour);
                    }
                    //check left
                    if(j-1 >= 0 && j-1 < c && maze[i][j-1] != '#'){
                        Node neighbour = getNode(i, j-1);
//                        System.out.print("NODE DATA LEFT " + neighbour.data + " ");
                        node.addNeighbours(neighbour);
                    }
                }
            }
        }
    }

    public int sweepMaze(char[][] maze, int r, int c) {
        int nodeCounter = 0;

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(maze[i][j] == '.') {
                    Node newNode = new Node(nodeCounter, i, j); // create node with value nodeCounter
                    graph.add(newNode); // add node to graph
                    nodeCounter++;
                }
            }
        }

        getAllNeighbours(maze, r, c);
        executeAllDfs();
        return longestPath;
    }

    public void mazeSearch(char[][] maze) {
        int rowSize = maze.length;
        int columnSize = maze[0].length;

        sweepMaze(maze, rowSize, columnSize);
    }

    //OK
    public static void printMatrix(int[][] arr) {
        System.out.println("matrix after insert");
        for(int i=0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++){

                System.out.print(arr[i][j] + " ");
                if(j == (arr.length -1)){
                    System.out.println("\n");
                }
            }
        }
    }
    //OK
    public static void printMatrix(char[][] arr) {
        System.out.println("matrix after insert");
        for(int i=0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++){

                System.out.print(arr[i][j] + " ");
                if(j == (arr.length -1)){
                    System.out.println("\n");
                }
            }
        }
    }
    //OK
    public static void printMatrix(boolean[][] arr) {
        System.out.println("matrix after insert");
        for(int i=0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++){

                System.out.print(arr[i][j] + " ");
                if(j == (arr.length -1)){
                    System.out.println("\n");
                }
            }
        }
    }

    // OK
    public static char [][] readCharsFromCommandLine(int row, int column) {


        char [][] map = new char[row][column];

        Scanner sChar = new Scanner(System.in);
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
//                System.out.println("Enter values for [" + i + "][" + j + "]");
                map[i][j] = sChar.next().charAt(0);
            }
        }
//        sChar.close();
//        printMatrix(map);
        return map;
    }

    // OK
    public static void main(String[] args) {

        Exercicio1 maze = new Exercicio1();
        boolean close = false;

        Scanner sInt = new Scanner(System.in);
        while(!close) {

//            System.out.println("Enter the value for number of rows");
            int row = sInt.nextInt();
//            System.out.println("Enter the value for number of columns");
            int column = sInt.nextInt();
            if(row == 0 && column == 0) {
                close = true;
            }

            char [][] arr = maze.readCharsFromCommandLine(row, column);
            maze.mazeSearch(arr);
        }
        sInt.close();

    }

}
