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

		//2. Skapa en priorityqueue med Kruskal-element
		PriorityQueue<KruskalElement<E>> kruskalQueue = new PriorityQueue<>();
		for (List<E> edgeList: edgeArray) {
			for (E edge : edgeList) {
				kruskalQueue.add(new KruskalElement(edge));
			}

		}
		//3. Skapa en array med kant-listor
		List<E>[] cc = new LinkedList[edgeArray.length];
		for ( int i = 0; i < cc.length; i++){
			cc[i] = new LinkedList<E>();
		}

		while(!kruskalQueue.isEmpty() && ( moreThanOneList(cc))){
			E currentEdge = kruskalQueue.poll().getEdge();
			List<E> fromList = cc[currentEdge.getSource()];
			List<E> toList = cc[currentEdge.getDest()];
			if(fromList != toList){
				if ( fromList.size() >= toList.size()){
					toList.addAll(fromList);
					toList.add(currentEdge);
					for(int i=0; i< cc.length;i++){
						if(cc[i] == fromList){
							cc[i]=toList;
						}
					}
				}else {
					fromList.addAll(toList);
					fromList.add(currentEdge);
					for(int i=0; i< cc.length;i++){
						if(cc[i] == toList){
							cc[i]=fromList;
						}
					}
				}


			}
		}
		if(cc[0] == null){
			System.out.println("LMAO");
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
  
