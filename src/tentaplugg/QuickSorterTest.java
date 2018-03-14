package tentaplugg;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSorterTest {
    @org.junit.jupiter.api.Test
    void sort() {
        QuickSorter quickSort = new QuickSorter();
        int[] arrayToSort =  {8,3,1,7,0,10,2};
        System.out.println("Sorting: " + Arrays.toString(arrayToSort));
        quickSort.sort(arrayToSort);
        System.out.println("\tResult: " + Arrays.toString(arrayToSort));

        int[] arrayToSort2 =  {40,35,80,75,60,90,70,75,50,22};
        System.out.println("Sorting: " + Arrays.toString(arrayToSort2));
        quickSort.sort(arrayToSort2);
        System.out.println("\tResult: " + Arrays.toString(arrayToSort2));
    }

}