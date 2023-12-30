package labs.aps.lab10.ex1;
//implementation from exercises

import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void printGraph() {
        for (Map.Entry<T, Set<T>> node : adjacencyList.entrySet()) {
            System.out.println(String.format("%s: %s", node.getKey().toString(), new ArrayList<>(node.getValue())));
        }
        System.out.println();
    }
}

public class graph1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<Integer>();
        for (int i = 0; i < N; i++) {
            String[] line = sc.nextLine().split(" ");
            switch (line[0]) {
                case ("CREATE"):
                    graph = new AdjacencyListGraph<Integer>();
                    break;
                case ("ADDEDGE"):
                    graph.addEdge(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case ("DELETEEDGE"):
                    graph.removeEdge(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case ("PRINTGRAPH"):
                    graph.printGraph();
                    break;
                default:
                    System.out.println(graph.getNeighbors(Integer.parseInt(line[1])).contains(Integer.parseInt(line[2])));
                    break;
            }
        }
    }
}
