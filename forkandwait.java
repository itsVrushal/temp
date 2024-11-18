import java.util.*;

public class forkandwait {
    public static void main(String[] args) {
        int[] arr = { 5, 8, 7, 2, 1, 4 };
        Thread childthThread = new Thread(new childprocess(arr));
        childthThread.start();
        System.out.println("Parent process started");
        Arrays.sort(arr);
        System.out.println("Parent process sorted res: " + Arrays.toString(arr));
        try {
            childthThread.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Parent process finished");
    }

    static class childprocess implements Runnable {
        int[] arr;

        childprocess(int[] arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            System.out.println("Child process started");
            Arrays.sort(arr);

            System.out.println("Child process sorted array: " + Arrays.toString(arr));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);

            }
            System.out.println("Child process finished");
        }
    }
}
