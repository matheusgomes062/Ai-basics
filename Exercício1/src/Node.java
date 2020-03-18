import java.util.ArrayList;
import java.util.List;

public class Node {
    int data;
    int x;
    int y;
    boolean visited;
    List<Node> neighbours;

    Node(int data, int x, int y)
    {
        this.data=data;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();

    }
    public void addNeighbours(Node neighbourNode)
    {
        this.neighbours.add(neighbourNode);
    }
    public List<Node> getNeighbours() {
        return neighbours;
    }
    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }
    public int[] getCoordinates() {
        int []coordinates = new int[2];
        coordinates[0] = this.x;
        coordinates[1] = this.y;
        return coordinates;
    }
    public int getValue() {
        return data;
    }
}
