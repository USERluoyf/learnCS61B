public class rotate {

    public static int[] Rotate(int[] A, int i) {
        int rightShift = i;
        if (rightShift > A.length) {
            rightShift = i % A.length;
        }
        int[] newArr = new int[A.length];
        for (int j = 0; j < A.length; j++) {
            newArr[(rightShift + j) % A.length] = A[j];
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6};
        int[] a = Rotate(A, 3);
        System.out.println(a[0]);
    }
}
