package lab3;

public class KruskalElement<E extends Edge> implements Comparable<KruskalElement> {
    private E edge;

    public KruskalElement(E edge) {
        this.edge = edge;
    }

    public E getEdge() {
        return edge;
    }

    @Override
    public int compareTo(KruskalElement o) {
        if (o != null) {
                if (this.edge.getWeight() > o.edge.getWeight()) {
                    return 1;
                } else if (this.edge.getWeight() < o.edge.getWeight()) {
                    return -1;
                } else {
                    return 0;
                }

        } else {
            System.out.println("Invalid argument in KruskalEmement.compareTo");
            return 0;
        }
    }
}
