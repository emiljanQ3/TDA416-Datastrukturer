package lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private LinkedList<E>[] edgeArray;

	public DirectedGraph(int noOfNodes) {
		edgeArray = new LinkedList[noOfNodes];
		for (int i = 0; i<edgeArray.length; i++){
			edgeArray[i] = new LinkedList<E>();
		}
	}

	/**
	 * Adds an edge to the LinkedList at the index in the array corresponding
	 * to the node which the edge originates from.
	 *
	 * @param e the object, which extends edge, to be added to the list
	 */

	public void addEdge(E e) {
		edgeArray[e.getSource()].add(e);
	}

	/**
	 * Calculates the shortest avaliable path from one node to another by using
	 * Dijkstras Shortest Path-algorithm
	 *
	 * @param from the index of the node which the path originates from
	 * @param to the index of the node the path should reach
	 * @return an iterator of a list including all the edges used to go from node "from"
	 * to node "to"
	 */
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

	/**
	 * Calculates the minimumSpanningTree using Kruskals algorithm
	 *
	 * @return an iterator of a list including all the edges used to create the minimum spanning tree.
	 */
	public Iterator<E> minimumSpanningTree() {

		// Skapa en priorityqueue med Kruskal-element
		PriorityQueue<KruskalElement<E>> kruskalQueue = new PriorityQueue<>();
		for (List<E> edgeList: edgeArray) {
			for (E edge : edgeList) {
				kruskalQueue.add(new KruskalElement(edge));
			}

		}
		// Skapa en array med kant-listor
		List<E>[] cc = new LinkedList[edgeArray.length];
		for ( int i = 0; i < cc.length; i++){
			cc[i] = new LinkedList<E>();
		}

		while(!kruskalQueue.isEmpty() && ( moreThanOneList(cc))){
			E currentEdge = kruskalQueue.poll().getEdge();
			List<E> mainList = cc[currentEdge.getSource()];
			List<E> secondaryList = cc[currentEdge.getDest()];
			if(mainList != secondaryList){
				if ( mainList.size() < secondaryList.size()){
					List<E> temp = mainList;
					mainList = secondaryList;
					secondaryList = temp;
				}

				mainList.addAll(secondaryList);
				mainList.add(currentEdge);

				for (E edge : secondaryList){ 			//TODO this does not work :'(
					cc[edge.getSource()] 	= mainList;
					cc[edge.getDest()]		= mainList;
				}
				/*for(int i=0; i< cc.length;i++) {  	//TODO this does but gives me angry remarks from the TAs
					if (cc[i] == secondaryList) {
						cc[i] = mainList;
					}

				}*/
			}
		}

		return cc[0].iterator();
	}

	private boolean moreThanOneList(List<E>[] cc) {
		List<E> firstReference = cc[0];
		for ( int i = 0; i<cc.length; i++){
			if (cc[i] != firstReference){
				return true;
			}

		}
		return  false;
	}


}
  
