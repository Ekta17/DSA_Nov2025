package dsa.topten;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FindTopKElementsInUnsortedArray {
    public static int[] getKLargestElements(int[] arr, int k){
        int[] result = new int[k];

        //Adding comparator was difficult for me
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for(int i: arr)
            maxHeap.add(i);

        for(int i =0; i<k; i++){
            result[i] = maxHeap.poll();
        }

        return result;
    }

    public static void main(String[] args){
        int[] arr = new int[]{9,4,0,8,2,1};
        int k = 3; //{9,8,4}
        assertArrayEquals(new int[]{9,8,4}, getKLargestElements(arr, k));
    }
}
