package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Integer, Set<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public boolean addVertex(int vertex) {
        if (adjacencyList.containsKey(vertex)) return false;
        adjacencyList.put(vertex, new HashSet<>());
        return true;
    }

    public boolean addEdge(int source, int destination) {
        if (!hasVertex(source) || !hasVertex(destination)) return false;
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
        return true;
    }

    public boolean removeVertex(int vertex) {
        if (!hasVertex(vertex)) return false;
        adjacencyList.values().forEach(neighbors -> neighbors.remove(vertex));
        adjacencyList.remove(vertex);
        return true;
    }

    public boolean removeEdge(int source, int destination) {
        if (!hasVertex(source) || !hasVertex(destination)) return false;
        if (adjacencyList.get(source).contains(destination)) {
            adjacencyList.get(source).remove(destination);
            adjacencyList.get(destination).remove(source);
            return true;
        }
        return false;
    }

    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(int source, int destination) {
        if (!hasVertex(source) || !hasVertex(destination)) return false;
        return adjacencyList.get(source).contains(destination);
    }

    // Метод для проверки, пустой ли граф (нужно для подсказок)
    public int getVertexCount() {
        return adjacencyList.size();
    }

    // --- ОБНОВЛЕННЫЙ ВИЗУАЛ ---
    @Override
    public String toString() {
        if (adjacencyList.isEmpty()) {
            return "   ( Graph is empty )";
        }
        StringBuilder builder = new StringBuilder();
        for (Integer vertex : adjacencyList.keySet()) {
            Set<Integer> neighbors = adjacencyList.get(vertex);

            // Рисуем красивую вершину ( V )
            builder.append("   ( ").append(vertex).append(" ) ");

            if (neighbors.isEmpty()) {
                builder.append("   (no connections)"); // Если одинока
            } else {
                // Рисуем стрелочку <===>
                builder.append("<====> ").append(neighbors);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}