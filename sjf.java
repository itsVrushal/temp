// import java.util.*;
//CHecked ok(NON Preemptive)
class sjf{
    public static void main(String[] args) {
        int[] AT = {3,1,4,0,2};
        int[] BT = {1,4,2,6,3};
        int n = AT.length;
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        // Integer[] pid = new Integer[n];
        // for(int i=0;i<n;i++){
        //     pid[i] = i+1;
        // }
        // Arrays.sort(pid,Comparator.comparingInt(i->AT[i-1]));
        boolean[]  completed = new boolean[n];
        int currentTime = 0;
        for(int i=0;i<n;i++){
            int minBT= Integer.MAX_VALUE;
            int index =-1;

            for(int j=0;j<n;j++){
                if(!completed[j] && BT[j]<minBT && AT[j]<=currentTime){
                    minBT = BT[j];
                    index = j;
                }
            }
            if(index == -1){
                currentTime++;
                index--;
                continue;
            }

            CT[index] = currentTime + BT[index];
            currentTime =CT[index];
            completed[index] =true;
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





// import java.util.*;


// class sjf {
//     public static void main(String[] args) {
//         int[] AT = {3,1,4,0,2};
//         int[] BT = {1,4,2,6,3}; // Burst times
//         int n = AT.length;

//         int[] CT = new int[n];
//         int[] TAT = new int[n];
//         int[] WT = new int[n];

//         // Process IDs
//         Integer[] pid = new Integer[n];
//         for (int i = 0; i < n; i++) {
//             pid[i] = i + 1;
//         }

//         // Sort processes by Arrival Time initially
//         Arrays.sort(pid, Comparator.comparingInt(i -> AT[i - 1]));

//         boolean[] completed = new boolean[n];
//         int currentTime = 0;

//         for (int i = 0; i < n; i++) {
//             int minBT = Integer.MAX_VALUE;
//             int index = -1;

//             // Find the process with the minimum burst time that has arrived and is not completed
//             for (int j = 0; j < n; j++) {
//                 if (AT[j] <= currentTime && !completed[j] && BT[j] < minBT) {
//                     minBT = BT[j];
//                     index = j;
//                 }
//             }

//             // If no process has arrived yet, move to the next time unit
//             if (index == -1) {
//                 currentTime++;
//                 i--; // Repeat the iteration for the same i
//                 continue;
//             }

//             // Calculate Completion Time
//             CT[index] = currentTime + BT[index];
//             currentTime = CT[index];
//             completed[index] = true;
//         }

//         // Turnaround Time and Waiting Time calculation
//         for (int i = 0; i < n; i++) {
//             TAT[i] = CT[i] - AT[i];
//             WT[i] = TAT[i] - BT[i];
//         }

//         // Print formatted output
//         System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s%n", "P id", "AT", "BT", "CT", "TAT", "WT");
//         for (int i = 0; i < n; i++) {
//             System.out.printf("%-5d %-5d %-5d %-5d %-5d %-5d%n", (i + 1), AT[i], BT[i], CT[i], TAT[i], WT[i]);
//         }
//     }
// }

   
