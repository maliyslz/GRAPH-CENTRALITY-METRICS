import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private ArrayList<String> vertices; // to keep vertex names
	private int[][] adjacency; // to keep edges
	private int size;
	int[][] verticescount;
	int[] visited;

	public Graph(int size) {
		vertices = new ArrayList<String>();
		adjacency = new int[size][size];
		this.size = size;
	}

	public void addEdge(String source, String destination) {
		int weight = 1;
		if (!vertices.contains(source))
			vertices.add(source);
		if (!vertices.contains(destination))
			vertices.add(destination);

		int source_index = vertices.indexOf(source);
		int destination_index = vertices.indexOf(destination);
		adjacency[source_index][destination_index] = weight;
		adjacency[destination_index][source_index] = weight;

	}

	public int size() {
		return this.size;
	}

	public int[][] getAdjacency() {
		return adjacency;
	}

	public ArrayList<String> getVertices() {
		return vertices;
	}

	public void mainfunction(String name) {
		int[] count = new int[vertices.size()];
		for (int i = 0; i < count.length; i++)
			count[i] = 0;

		double closenesscount = 9999999;
		String closenumber = " ";

		for (String i : vertices) {
			int sum = 0;
			int[] distance = ShortestPath(count, vertices.indexOf(i));
			for (int a = 0; a < distance.length; a++) {
				if (distance[a] < 1000)
					sum += distance[a];
			}

			if (sum < closenesscount && sum > 1) {
				closenesscount = sum;
				closenumber = i;
			}
		}
		double result = (vertices.size() - 1) / closenesscount;
		int betwennesnumber = 0;
		String betwennes = " ";
		for (int i = 0; i < vertices.size(); i++) {
			if (count[i] > betwennesnumber) {
				betwennesnumber = count[i];
				betwennes = vertices.get(i);
			}
		}
		String name1 = "";
		if (name.equalsIgnoreCase("zachary"))
			name1 = "Zachary Karate Club Network ";
		else
			name1 = "Facebook Social Network ";
		System.out.println(name1 + "-The Highest Node for Betweennes and the value   " + betwennes + " - " + betwennesnumber);
		System.out.println(name1 + "-The Highest Node for Closeness and the value   " + closenumber + " - " + result+"\n");

	}

	public int getClosestVertex(int[] distance, boolean[] visited, int its) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] < min)
				if (visited[i] == false) {
					min = distance[i];
					index = i;
				}
		}
		if (index == -1)
			return its;
		return index;
	}

	public int[] ShortestPath(int[] count, int src) {
		int[] distance = new int[vertices.size()];
		boolean[] visited = new boolean[vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			distance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		int lastdistance = -1;
		distance[src] = 0;
		Queue<String> temp = new LinkedList<String>();// for betweennes
		for (int i = vertices.size() - 1; i >= 0; i--) {
			int its = src;
			int closestVertex = getClosestVertex(distance, visited, its);
			visited[closestVertex] = true;
			Queue<String> queue = new LinkedList<String>();
			boolean more = false;
			for (int j = (vertices.size() - 1); j >= 0; j--) {
				if (visited[j] == false) {
					if (adjacency[closestVertex][j] != 0) {
						int d = distance[closestVertex] + adjacency[closestVertex][j];
						if (d < distance[j]) {
							if (j > src) {
								count[src]++;
							}
							while (!temp.isEmpty()) {
								queue.add(temp.poll());
							}
							distance[j] = d;
							if (lastdistance == d)
								more = true;
							else
								more = false;
							queue.add(vertices.get(j));
							if (more == true) {
								for (int a = 0; a < queue.size() - 2; a++) {
									queue.add(queue.poll());
								}
								queue.poll();
								queue.add(queue.poll());
								while (!queue.isEmpty()) {
									if (j > src) {
										count[vertices.indexOf(queue.peek())]++;
									}
									temp.add(queue.poll());
								}
							} else {
								while (!queue.isEmpty()) {
									if (j > src)
										count[vertices.indexOf(queue.peek())]++;
									temp.add(queue.poll());
								}
							}
							lastdistance = d;
						}
					}
				}
			}
		}
		return distance;
	}

	public void print() {
		for (String v : vertices) {
			System.out.print("\t(" + v + ")");
		}
		System.out.println();
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print("(" + vertices.get(i) + ")\t");
			for (int j = 0; j < adjacency.length; j++) {
				System.out.print(adjacency[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Edges");
		int edge_count = 0;
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (adjacency[i][j] > 0) {
					System.out.println(vertices.get(i) + "-" + vertices.get(j) + ":" + adjacency[i][j]);
					edge_count++;
				}
			}
		}
		System.out.println("Total " + edge_count + " edges.");
		System.out.println();
	}

}
