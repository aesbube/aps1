package labs.aps.lab10.ex3;
//implementation from exercises

import java.util.*;
import java.util.Map.Entry;

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

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while (!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for (T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if (!visited.contains(vertex)) {
                    break;
                }
            }

            if (!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            } else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while (!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

    public void findSeparationDegree(T user1, T user2) {
        Map<T, Integer> dist = new HashMap<>();
        Queue<T> people = new LinkedList<>();
        dist.put(user1, 0);
        people.add(user1);
        while (!people.isEmpty()) {
            T curr = people.poll();
            if (curr.equals(user2)) System.out.println(dist.get(curr));
            for (T neb : getNeighbors(curr)) {
                if (!dist.containsKey(neb)) {
                    dist.put(neb, dist.get(curr) + 1);
                    people.add(neb);
                }
            }
        }
        return;
    }
}

public class SocialMedia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfUsers = Integer.parseInt(scanner.nextLine());
        AdjacencyListGraph<String> socialMedia = new AdjacencyListGraph<>();
        for (int i = 0; i < numOfUsers; i++) {
            String user = scanner.nextLine();
            socialMedia.addVertex(user);

            int userFriends = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < userFriends; j++) {
                String friend = scanner.nextLine();
                socialMedia.addEdge(user, friend);
            }
        }

        String u1 = scanner.nextLine();
        String u2 = scanner.nextLine();

        socialMedia.findSeparationDegree(u1, u2);

    }

}
