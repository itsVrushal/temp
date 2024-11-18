import java.util.*;
class sjf_preemptive{
    public static void main(String[] args) {
        int[] AT = {3,1,4,0,2};
        int[] BT = {1,4,2,6,3};
        int n = AT.length;
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];
        
        int[] remainingBT = Arrays.copyOf(BT, n);
        boolean[] completed = new boolean[n];
        int currentTime =0, completedcount=0;
        int minBT,index;
        while(completedcount<n){
            minBT = Integer.MAX_VALUE;
            index =-1;
            for(int j=0;j<n;j++){
                if(AT[j]<=currentTime && !completed[j] && remainingBT[j]<minBT){
                    minBT = remainingBT[j];
                    index =j;
                }
            }
            if(index == -1){
                currentTime++;
                continue;
            }


            remainingBT[index]--;
            currentTime++;
            if(remainingBT[index]==0){
                completed[index] = true;
                completedcount++;
                CT[index] = currentTime;
            }
        }
        for(int i=0;i<n;i++){
            TAT[i] = CT[i]-AT[i];
            WT[i] = TAT[i] - BT[i];
        }
        System.out.printf("%-5s |  %-5s |  %-5s |  %-5s |  %-5s |  %-5s%n", "P id", "AT", "BT", "CT", "TAT", "WT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-5d |  %-5d |  %-5d |  %-5d |  %-5d |  %-5d%n", (i + 1), AT[i], BT[i], CT[i], TAT[i], WT[i]);
        }
    }
}