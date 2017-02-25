package shu.ds.c1.q3;

/**
 * Created by victo on 25/02/2017.
 */
public class Q3 {
    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(8);
        q.setQ(new int[] {5,6,2,3,4,0,0,0});
        q.setF(0);
        q.setR(5);
        while (q.front() < 10) {
            q.enqueue(q.dequeue() + q.front());
        }
        System.out.println(q);
    }

}
