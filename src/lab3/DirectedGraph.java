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
		DijkstraElement<E>[] allElements = new DijkstraElement[edgeArray.length];
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

		for (DijkstraElement<E> element = allElements[to]; element.getPrevious() != null; element = element.getPrevious()){
			path.add(0, element.getLastEdge());
		}

		return path.iterator();
	}
		
	public Iterator<E> minimumSpanningTree() {


		//1. Skapa en array med booleans som representerar huruvida noden är en del av trädet //False från början
		boolean[] nodeIsIncluded = new boolean[edgeArray.length];
		for (boolean nodeStatus : nodeIsIncluded) {
			nodeStatus = false;
		}
		//2. Lista över included edges
		List<E> mst =  new LinkedList<>();

		//2. Skapa en priorityqueue med Kruskal-element
		PriorityQueue<KruskalElement<E>> kruskalQueue = new PriorityQueue<>();
		for (List<E> edgeList: edgeArray) {
			for (E edge : edgeList) {
				kruskalQueue.add(new KruskalElement(edge));
			}

		}
		while(!kruskalQueue.isEmpty()){ //TODO annan check? se om alla noder är valda
			E currentEdge = kruskalQueue.poll().getEdge(); 
			if(!(nodeIsIncluded[currentEdge.to] && nodeIsIncluded[currentEdge.from])){
				mst.add(currentEdge);
				nodeIsIncluded[currentEdge.to] = true;
				nodeIsIncluded[currentEdge.from] = true;
			}

			//Kolla om båda noderna är true i vår array
			//Om inte, lägg till i trädet
			//annars kasta
		}


		//3. Lägg till det första elementet ifrån PriorityQueuen, om båda noderna inte redan är true i array 1.

		//4. Upprepa 3 tills priorityqueuen är tom?

		return mst.iterator();
	}

}
  
