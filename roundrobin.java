import java.util.Arrays;
// import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class roundrobin {
    public static void main(String[] args) {
        int[] AT = {5,4,3,1,2,6};
        int[] BT = {5,6,7,9,2,3};
        int n = AT.length;
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];
        int[] remainingBT = Arrays.copyOf(BT, n);
        int tQ = 3;
        int currentTime=0;
        int completedcount=0;
        
        

        Queue<Integer> readyQueue = new LinkedList<>();      
        boolean[] inQueue = new boolean[n]; 
        for(int i=0;i<n;i++){
            if(AT[i] <= currentTime && remainingBT[i] > 0 && !inQueue[i]){
                readyQueue.add(i);
                inQueue[i] = true;
            }
        }
        while(completedcount<n){
            
            if(readyQueue.isEmpty()){
                currentTime++;
                continue;
            }

            int currentProcess = readyQueue.poll();
            inQueue[currentProcess] = false;
            int executionTime = Math.min(tQ,remainingBT[currentProcess]);
            currentTime+=executionTime;
            remainingBT[currentProcess] -= executionTime;
            
            if(remainingBT[currentProcess]==0){
                completedcount++;
                CT[currentProcess] = currentTime;
            }else{

                readyQueue.add(currentProcess);
                inQueue[currentProcess] = true;
            }

        } 
        for (int i = 0; i < n; i++) {
            TAT[i] = CT[i] - AT[i];
            WT[i] = TAT[i] - BT[i];
        }

        // Print formatted output
        System.out.printf("%-5s |  %-5s |  %-5s |  %-5s |  %-5s |  %-5s%n", "P id", "AT", "BT", "CT", "TAT", "WT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-5d |  %-5d |  %-5d |  %-5d |  %-5d |  %-5d%n", (i + 1), AT[i], BT[i], CT[i], TAT[i], WT[i]);
        }
        
    }
}
