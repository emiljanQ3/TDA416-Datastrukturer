package tentaplugg;

public class QuickSorter implements IntSorter{
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length -1);
    }

    private  void quickSort(int[] array, int startIndex, int endIndex){
        if (endIndex <= startIndex){
            return;
        }
        int pivot = array[startIndex];
        swap(array, startIndex, endIndex);

        int leftItemIndex = startIndex;
        int rightItemIndex = endIndex -1;
        while (true){
            //Find leftItem
            while(leftItemIndex <= endIndex -1 && array[leftItemIndex] <= pivot){
                leftItemIndex++;
            }
            //Find rightItem
            while(rightItemIndex >= leftItemIndex && array[rightItemIndex] >= pivot){
                rightItemIndex--;
            }
            //Compare index
            if(leftItemIndex > rightItemIndex){
                break;
            }

            swap(array, leftItemIndex, rightItemIndex);
        }
        swap(array, leftItemIndex, endIndex);
        //recursion
        quickSort(array, startIndex, leftItemIndex -1);
        quickSort(array, leftItemIndex +1, endIndex);
    }

    private void swap(int[] array, int posOne, int posTwo ){
        int temp = array[posOne];
        array[posOne] = array[posTwo];
        array[posTwo] = temp;
    }
}
