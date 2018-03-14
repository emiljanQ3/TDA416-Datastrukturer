package tentaplugg;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapSorterTest {
    @Test
    void sort() {
        HeapSorter heapSort = new HeapSorter();
        int[] arrayToSort =  {8,3,1,7,0,10,2};
        System.out.println("Sorting: " + Arrays.toString(arrayToSort));
        heapSort.sort(arrayToSort);
        System.out.println("\tResult: " + Arrays.toString(arrayToSort));

        int[] arrayToSort2 =  {40,35,80,75,60,90,70,75,50,22};
        System.out.println("Sorting: " + Arrays.toString(arrayToSort2));
        heapSort.sort(arrayToSort2);
        System.out.println("\tResult: " + Arrays.toString(arrayToSort2));
    }

}