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
		//Create djikstra elements for each node and set their weight to inf
		DijkstraElement[] allElements = new DijkstraElement[edgeArray.length];
		for (int i = 0; i<allElements.length; i++ ) {
			allElements[i] = new DijkstraElement(null, i, Double.POSITIVE_INFINITY , null);
		}
		//Set one dijkstra node as starting point and set its weight to 0
		Queue<DijkstraElement> activeElements = new PriorityQueue<>();
		allElements[from].setTotalWeight(0);
		activeElements.add(allElements[from]);

		//algorithm
		algorithm:
		while (true){
			DijkstraElement currentElement = activeElements.poll();
			List<E> listOfNeighbours = edgeArray[currentElement.getNodeIndex()];

			for ( E edge : listOfNeighbours){
				DijkstraElement nextElement = allElements[edge.to];
				double weightToNextStep = currentElement.getTotalWeight() + edge.getWeight();
				if (weightToNextStep < nextElement.getTotalWeight()){

					//Update dijkstra element
					nextElement.setTotalWeight(weightToNextStep);
					nextElement.setPrevious(currentElement);
					nextElement.setLastEdge(edge);

					if (nextElement.getNodeIndex() == to)
						break algorithm;

					//Add dijkstra element to active elements
					activeElements.add(nextElement);
				}
			}
		}

		List<E> path = new LinkedList<>();

		for (DijkstraElement element = allElements[to]; element.getPrevious() != null; element = element.getPrevious()){
			path.add(0,(E) element.getLastEdge()); //TODO think about this later
		}

		return path.iterator();
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
