package lab3;

public class DijkstraElement <E extends Edge> implements Comparable{
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
    public int compareTo(Object o) {
        if (o.getClass().equals(this.getClass())){
            if (this.totalWeight > ((DijkstraElement) o).totalWeight)
                return 1;
            else if (this.totalWeight < ((DijkstraElement) o).totalWeight)
                return -1;
            else
                return 0;
        }else {
            throw new IllegalArgumentException(){   };
        }

    }
}
