package app;

import java.util.Scanner;

public class GraphRunner {

    private final Graph graph = new Graph();
    private final Scanner scanner = new Scanner(System.in);
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    public void run() {
        System.out.println(CYAN + "\n>> WELCOME TO GRAPH MANAGER <<" + RESET);

        while (true) {
            System.out.println(CYAN + "\n[ Current Graph Map ]:" + RESET);
            System.out.println(graph);
            System.out.println(CYAN + "-------------------------" + RESET);

            printMenu();
            int choice = getSafeInteger();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }
            handleMenuChoice(choice);
        }
    }

    private void printMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Add Vertex (Create a dot)");
        System.out.println("2. Add Edge (Connect two dots)");
        System.out.println("3. Remove Vertex");
        System.out.println("4. Remove Edge");
        System.out.println("5. Check Info");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> {
                System.out.print("Enter new vertex number: ");
                int v = getSafeInteger();
                if (graph.addVertex(v))
                    System.out.println(GREEN + "SUCCESS: Vertex (" + v + ") created." + RESET);
                else
                    System.out.println(RED + "FAIL: Vertex (" + v + ") already exists." + RESET);
            }
            case 2 -> {
                if (graph.getVertexCount() < 2) {
                    System.out.println(YELLOW + "WARNING: You need at least 2 vertices to create a connection!" + RESET);
                    System.out.println("Hint: Use option '1' to add more vertices first.");
                    return;
                }

                System.out.print("Connect vertex: ");
                int src = getSafeInteger();
                System.out.print("With vertex: ");
                int dest = getSafeInteger();

                if (graph.addEdge(src, dest))
                    System.out.println(GREEN + "SUCCESS: Connected (" + src + ") <==> (" + dest + ")" + RESET);
                else
                    System.out.println(RED + "FAIL: One of the vertices doesn't exist." + RESET);
            }
            case 3 -> {
                System.out.print("Enter vertex ID to remove: ");
                int v = getSafeInteger();
                if (graph.removeVertex(v)) System.out.println(GREEN + "SUCCESS: Vertex removed." + RESET);
                else System.out.println(RED + "FAIL: Not found." + RESET);
            }
            case 4 -> {
                System.out.print("Vertex A: ");
                int src = getSafeInteger();
                System.out.print("Vertex B: ");
                int dest = getSafeInteger();
                if (graph.removeEdge(src, dest)) System.out.println(GREEN + "SUCCESS: Connection broken." + RESET);
                else System.out.println(RED + "FAIL: No connection found." + RESET);
            }
            case 5 -> {
                System.out.print("Check vertex ID: ");
                int v = getSafeInteger();
                if (graph.hasVertex(v)) System.out.println(GREEN + "YES! Vertex (" + v + ") is in the graph." + RESET);
                else System.out.println(RED + "NO! Vertex (" + v + ") does not exist." + RESET);
            }
            default -> System.out.println(RED + "Invalid option." + RESET);
        }
    }

    private int getSafeInteger() {
        while (!scanner.hasNextInt()) {
            String trash = scanner.next();
            System.out.println(RED + "Error: '" + trash + "' is not a number." + RESET);
            System.out.print("Value: ");
        }
        return scanner.nextInt();
    }
}