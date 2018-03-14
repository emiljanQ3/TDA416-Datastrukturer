package tentaplugg;

public class HeapSorter implements IntSorter {
    @Override
    public void sort(int[] array) {
        for (int nodeIndex = (array.length -2)/2; nodeIndex>=0; nodeIndex--){
            maxify(array, nodeIndex, array.length);
        }
        for (int logicalSize = array.length; logicalSize > 0; logicalSize--){
            swap(array, 0, logicalSize -1 );
            maxify(array, 0, logicalSize -1 );
        }
    }

    private void maxify(int[] array, int nodeIndex, int logicalSize) {
        int largestNodeIndex = nodeIndex;
        if(nodeIndex*2 +1 < logicalSize && array[nodeIndex*2 +1] > array[largestNodeIndex]){
            largestNodeIndex = nodeIndex*2 +1;
        }

        if(nodeIndex*2 +2 < logicalSize && array[nodeIndex*2 +2] > array[largestNodeIndex]){
            largestNodeIndex = nodeIndex*2 +2;
        }
        if (largestNodeIndex != nodeIndex){
            swap(array, largestNodeIndex, nodeIndex);
            maxify(array, largestNodeIndex, logicalSize);
        }

    }

    private void swap(int[] array, int posOne, int posTwo ){
        int temp = array[posOne];
        array[posOne] = array[posTwo];
        array[posTwo] = temp;
    }
}
