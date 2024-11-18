//checked ok
class fcfs{
    public static void main(String[] args){
        int[] AT = {0,1,2,3,4};
        int[] BT = {2,3,5,4,6};
        int n = AT.length;
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        //copletion time
        int buffer=0;
        for(int i=0;i<n;i++){
            CT[i] = BT[i]+ buffer;
            buffer += BT[i];
        }
        //Total arrivat and burst time
        for(int i=0;i<n;i++){
            TAT[i] = CT[i] - AT[i];
            WT[i] = TAT[i] - BT[i];
        }
        System.out.printf("%-5s |  %-5s |  %-5s |  %-5s |  %-5s |  %-5s%n", "P id", "AT", "BT", "CT", "TAT", "WT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-5d |  %-5d |  %-5d |  %-5d |  %-5d |  %-5d%n", (i + 1), AT[i], BT[i], CT[i], TAT[i], WT[i]);
        }
    }
}