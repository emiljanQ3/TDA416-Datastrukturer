package lab3;

public class KruskalElement implements Comparable {
    private Edge edge;

    public KruskalElement(Edge edge){
        this.edge = edge;
    }

    public Edge getEdge() {
        return edge;
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass().equals(this.getClass())){
            if(this.edge.getWeight() > ((KruskalElement)o).edge.getWeight()){
                return 1;
            }else if(this.edge.getWeight() < ((KruskalElement)o).edge.getWeight()){
                return -1;
            }else{
                return 0;
            }
        }else{
            throw new IllegalArgumentException(){   };
        }
    }
}
