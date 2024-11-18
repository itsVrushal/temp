import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class producerconsumerprac {

    private static final int BUFFER_SIZE = 5;
    private static final int MAX_ITEMS = 10;
    private Queue<Integer> buffer = new LinkedList<>();
    private Semaphore empty = new Semaphore(BUFFER_SIZE);
    private Semaphore full = new Semaphore(0);

    private Lock mutex = new ReentrantLock();

    private int producedItems = 0;
    private int consumedItems = 0;

    class Producer extends Thread {
        public void run() {
            try {

                while (true) {
                    mutex.lock();
                    if (producedItems >= MAX_ITEMS) {
                        mutex.unlock();
                        break;
                    }
                    mutex.unlock();
                    int item = producedItem();
                    empty.acquire();
                    mutex.lock();
                    buffer.add(item);
                    producedItems++;
                    System.out.println("Producer produced: " + item + " (Total produced: " + producedItems + ")");
                    mutex.unlock();
                    full.release();
                    Thread.sleep((int) Math.random() * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private int producedItem() {
            return (int) (Math.random() * 100);
        }
    }

    class Consumer extends Thread {
        public void run() {
            try {
                while (true) {
                    mutex.lock();
                    if (consumedItems >= MAX_ITEMS) {
                        mutex.unlock();
                        break;
                    }
                    mutex.unlock();
                    full.acquire();
                    mutex.lock();

                    int items = buffer.remove();
                    consumedItems++;
                    System.out.println("Consumer consumed: " + items + " (Total consumed: " + consumedItems + ")");
                    mutex.unlock();
                    empty.release();
                    Thread.sleep((int) (Math.random() * 1500));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        producerconsumerprac pc = new producerconsumerprac();
        Producer producer = pc.new Producer();
        Consumer consumer = pc.new Consumer();

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}