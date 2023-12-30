package labs.aps.lab10.ex2;
//implementation from exercises

import java.util.*;

import java.util.ArrayList;
import java.util.List;

class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<String> getNeighbors(int vertexIndex) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] == 1) {
                neighbors.add((String) vertices[i]);
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

    public void printPath(String start, String end, Map<String, Integer> map) {
        Set<Integer> visited = new HashSet<>();
        Stack<String> inverted = new Stack<>();
        visited.add(map.get(start));
        inverted.push(start);

        while (!inverted.isEmpty() && !inverted.peek().equals(end)) {
            String curr = inverted.peek();
            String temp = curr;

            for (String ver : getNeighbors(map.get(curr))) {
                temp = ver;
                if (!visited.contains(map.get(ver))) {
                    break;
                }
            }

            if (!visited.contains(map.get(temp))) {
                visited.add(map.get(temp));
                inverted.push(temp);
            } else inverted.pop();
        }

        Stack<String> regularPath = new Stack<>();
        while (!inverted.isEmpty()) {
            regularPath.push(inverted.pop());
        }
        if (!regularPath.isEmpty())
            while (!regularPath.isEmpty()) {
                System.out.println(regularPath.pop());
            }
    }


}

public class graph2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int rows = Integer.parseInt(line.split(",")[0]);
        int cols = Integer.parseInt(line.split(",")[1]);
        AdjacencyMatrixGraph<String> mazeGraph = new AdjacencyMatrixGraph<>(rows * cols);
        String start = "";
        String end = "";
        Map<String, Integer> map = new HashMap<>();
        String[] lines = new String[rows];
        int ct = 0;
        for (int i = 0; i < rows; i++) {
            lines[i] = scanner.nextLine();
            for (int j = 0; j < cols; j++) {
                if (lines[i].charAt(j) != '#') {
                    mazeGraph.addVertex(ct, i + "," + j);
                    map.put(i + "," + j, ct++);
                    if (lines[i].charAt(j) == 'S') {
                        start = i + "," + j;
                    }
                    if (lines[i].charAt(j) == 'E') {
                        end = i + "," + j;
                    }
                    if (i > 0 && lines[i - 1].charAt(j) != '#') {
                        mazeGraph.addEdge(map.get(i + "," + j), map.get((i - 1) + "," + j));
                    }
                    if (j > 0 && lines[i].charAt(j - 1) != '#') {
                        mazeGraph.addEdge(map.get(i + "," + (j - 1)), map.get(i + "," + j));
                    }
                }
            }
        }
        scanner.close();
        mazeGraph.printPath(start, end, map);
    }

}
