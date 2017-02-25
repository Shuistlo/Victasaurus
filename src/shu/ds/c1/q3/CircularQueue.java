package shu.ds.c1.q3;

import java.util.Arrays;

/**
 * Created by victo on 25/02/2017.
 */
public class CircularQueue {
    private int f=0;
    private int r=0;
    private int n;
    private int[] q;

    public void setQ(int[] q) {
        this.q = q;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString() {
        int i = r;
        while(i%n<f){
            q[i] = Integer.MIN_VALUE;
            i++;
        }
        return "CircularQueue{" +
                "f=" + f +
                ", r=" + r +
                ", n=" + n +
                ", q=" + Arrays.toString(q) +
                '}' + " The Min_integer values represent empty spots in array.";
    }

    public void setR(int r) {
        this.r = r;
    }

    public CircularQueue(int n) {
        this.n = n;
        q = new int[n];
    }

    public int size(){
        return (r-f+n) % n;
    }

    public boolean isEmpty(){
        return f==r;
    }

    public int front(){
        return q[f];
    }

    public void enqueue(int e){
        if (size()!=n-1){
            q[r] = e;
            r = (r+1)%n;
        }
    }

    public int dequeue(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        } else{
            int e = q[f];
            f = (f+1)%n;
            return e;
        }
    }
}
