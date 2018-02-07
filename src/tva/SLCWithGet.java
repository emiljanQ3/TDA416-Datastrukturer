package tva;

public class SLCWithGet<E extends Comparable<? super E>> extends LinkedCollection<E> implements CollectionWithGet<E> {


    /**
     * Adds an element to the collection.
     * The element will be sorted into the right place
     * in the list. If the list is empty it will be
     * added as the head
     *
     * @param element the object to add into the list
     * @return true if the object has been added to the list.
     * @throws NullPointerException if parameter <tt>element<tt> is null.
     */
    @Override
    public boolean add(E element) {
        if (element == null)
            throw new NullPointerException();
        else if (this.isEmpty()) {
            head = new Entry( element, head );
            return true;
        }else{
            for ( Entry p = head; p != null; p = p.next ){
                if (element.compareTo(p))
            }
        }
    }

    @Override
    public E get(E e) {
        return null;
    }
}