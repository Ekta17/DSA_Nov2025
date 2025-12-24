package dsa;

public class Utils {

    public static void printArray(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i: arr){
            sb.append(i).append(",");
        }
        sb.append("]");

        System.out.println(sb);
    }
}
