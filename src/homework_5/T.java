package homework_5;

public class T {
    public static void main(String[] args) {
        AQueue queue = new AQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        System.out.println(queue.dequeue());
    }
}
