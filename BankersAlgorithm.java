import java.util.Scanner;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] max = new int[n][m];
        int[][] alloc = new int[n][m];
        int[][] need = new int[n][m];
        int[] avail = new int[m];

        System.out.println("Enter the Max matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max[i][j] = sc.nextInt();

        System.out.println("Enter the Allocation matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                alloc[i][j] = sc.nextInt();

        System.out.println("Enter the Available resources:");
        for (int i = 0; i < m; i++)
            avail[i] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                need[i][j] = max[i][j] - alloc[i][j];

        boolean[] finish = new boolean[n];
        int[] work = new int[m];
        System.arraycopy(avail, 0, work, 0, m);

        int[] safeSequence = new int[n];
        int count = 0;

        boolean safe = true;
        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++)
                        if (need[i][j] > work[j])
                            break;

                    if (j == m) {
                        for (int k = 0; k < m; k++)
                            work[k] += alloc[i][k];

                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                safe = false;
                break;
            }
        }

        if (safe) {
            System.out.println("System is in a safe state.\nSafe sequence is: ");
            for (int i = 0; i < n; i++)
                System.out.print(safeSequence[i] + " ");
            System.out.println();
        } else {
            System.out.println("System is not in a safe state.");
        }

        sc.close();
    }
}
