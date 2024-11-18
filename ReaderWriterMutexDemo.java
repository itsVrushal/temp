import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class ReaderWriterProblem {
    private int readers = 0;
    private Lock lock = new ReentrantLock();
    private Condition canWrite = lock.newCondition();
    private boolean writing = false;

    // Reader enters
    public void startReading() throws InterruptedException {
        lock.lock();
        try {
            while (writing) {
                canWrite.await();
            }
            readers++;
            System.out.println("Reader " + readers + " starts reading.");
        } finally {
            lock.unlock();
        }
    }

    // Reader exits
    public void stopReading() {
        lock.lock();
        try {
            System.out.println("Reader " + readers + " stops reading.");
            readers--;
            if (readers == 0) {
                canWrite.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    // Writer enters
    public void startWriting() throws InterruptedException {
        lock.lock();
        try {
            while (readers > 0 || writing) {
                canWrite.await();
            }
            writing = true;
            System.out.println("Writer starts writing.");
        } finally {
            lock.unlock();
        }
    }

    // Writer exits
    public void stopWriting() {
        lock.lock();
        try {
            System.out.println("Writer stops writing.");
            writing = false;
            canWrite.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class Reader implements Runnable {
    private ReaderWriterProblem problem;

    public Reader(ReaderWriterProblem problem) {
        this.problem = problem;
    }

    @Override
    public void run() {
        try {
            problem.startReading();
            Thread.sleep(1000); // Simulate reading time
            problem.stopReading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer implements Runnable {
    private ReaderWriterProblem problem;

    public Writer(ReaderWriterProblem problem) {
        this.problem = problem;
    }

    @Override
    public void run() {
        try {
            problem.startWriting();
            Thread.sleep(2000); // Simulate writing time
            problem.stopWriting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReaderWriterMutexDemo {
    public static void main(String[] args) {
        ReaderWriterProblem problem = new ReaderWriterProblem();

        // Create threads for readers and writers
        Thread reader1 = new Thread(new Reader(problem));
        Thread reader2 = new Thread(new Reader(problem));
        Thread writer1 = new Thread(new Writer(problem));

        reader1.start();
        reader2.start();
        writer1.start();
    }
}
