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

	public Iterator shortestPath(int from, int to) {
		boolean[] visited = new boolean[edgeArray.length];
		PriorityQueue<DijkstraElement> queue = new PriorityQueue<>();

		// Put initial element in queue
		queue.add(new DijkstraElement<E>(null, from, 0,null));


		while(!queue.isEmpty()) {
			DijkstraElement element = queue.poll();

			if(!visited[element.getNodeIndex()]) {
				// If goal is reached, return path
				if(element.getNodeIndex() == to) {
					return element.getPath();
				}

				visited[element.getNodeIndex()] = true;

				for(E edge : edgeArray[element.getNodeIndex()]) {
					if(!visited[edge.to]) {
						queue.add(new DijkstraElement<E>( element, edge.to, element.getTotalWeight()+edge.getWeight(), edge));
					}
				}
			}
		}
		return null;
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


				cc[currentEdge.getDest()] = mainList;
				cc[currentEdge.getSource()] = mainList;
				for (E edge : secondaryList){
					cc[edge.getSource()] 	= mainList;
					cc[edge.getDest()]		= mainList;
				}

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
  
