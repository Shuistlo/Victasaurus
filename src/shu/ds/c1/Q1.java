package shu.ds.c1;

/**
 * Created by victo on 25/02/2017.
 */
public class Q1 {
        public static void main(String[] args) {
            System.out.println(fnc(3,5));
        }
        public static int fnc(int n, int k) {
            System.out.println(n + " " + k);
            if ( (n <= 1) || (k <= 1) ) {
                return n+k;
            }
            else {
                return fnc(n-1,k) + fnc(n+1,k-2);
            }
        }
}
