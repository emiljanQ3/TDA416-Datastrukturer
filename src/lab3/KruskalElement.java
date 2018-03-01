package lab3;

public class KruskalElement<E extends Edge> implements Comparable {
    private E edge;

    public KruskalElement(E edge) {
        this.edge = edge;
    }

    public E getEdge() {
        return edge;
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            if (o.getClass().equals(this.getClass())) {
                if (this.edge.getWeight() > ((KruskalElement) o).edge.getWeight()) {
                    return 1;
                } else if (this.edge.getWeight() < ((KruskalElement) o).edge.getWeight()) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                throw new IllegalArgumentException() {
                };
            }
        } else {
            throw new NullPointerException();
        }
    }
}
