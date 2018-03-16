package lab3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DijkstraElement <E extends Edge> implements Comparable<DijkstraElement>{
    private  DijkstraElement previous;
    private  double totalWeight;
    private  E lastEdge;
    private  final int nodeIndex;

    public DijkstraElement(DijkstraElement previous, int nodeIndex, double totalWeight, E lastEdge){
        this.previous = previous;
        this.totalWeight = totalWeight;
        this.lastEdge = lastEdge;
        this.nodeIndex = nodeIndex;
    }

    public Iterator<E> getPath(){
        LinkedList<E> path = new LinkedList<>();
        for (DijkstraElement<E> element = this; element.getPrevious() != null; element = element.getPrevious()){
            path.addFirst( element.getLastEdge());
        }

        return path.iterator();
    }

    public DijkstraElement getPrevious() {
        return previous;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public E getLastEdge() {
        return lastEdge;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setPrevious(DijkstraElement previous) {
        this.previous = previous;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setLastEdge(E lastEdge) {
        this.lastEdge = lastEdge;
    }

    @Override
    public int compareTo(DijkstraElement o) {
        if(o != null){

            if (o.getClass().equals(this.getClass())){
                if (this.totalWeight > o.totalWeight)
                    return 1;
                else if (this.totalWeight < o.totalWeight)
                    return -1;
                else
                    return 0;
            }else {
                System.out.println("Invalid argument in DijkstraEmement.compareTo");
                return 0;
            }
        }else{
            System.out.println("Invalid argument in DijkstraEmement.compareTo");
            return 0;
        }

    }
}
