package lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private LinkedList<E>[] edgeArray;

	public DirectedGraph(int noOfNodes) {
		edgeArray = new LinkedList[noOfNodes];
		for (LinkedList<E> list : edgeArray){
			list = new LinkedList<E>();
		}
	}

	public void addEdge(E e) {
		edgeArray[e.getSource()].add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
