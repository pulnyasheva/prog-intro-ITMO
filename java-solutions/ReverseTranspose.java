import java.util.Scanner;

public class ReverseTranspose {
    public static int[][] add1(int[][] nums, int index1) {
        int[][] nums1 = new int[index1][];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = new int[nums[i].length];
            nums1[i] = nums[i];
        }
        nums1[index1 - 1] = new int[0];
        return nums1;
    }

    public static int[] add2(int[] nums, int index2, int b) {
        int[] nums1 = new int[index2];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        nums1[index2 - 1] = b;
        return nums1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] mass = new int[0][0];
        int index1 = 0;
        int maxString = 0;
        while (sc.hasNextLine()) {
            index1++;
            mass = add1(mass, index1);
            Scanner sc1 = new Scanner(sc.nextLine());
            int index2 = 0;
            while (sc1.hasNextInt()) {
                index2++;
                int b = sc1.nextInt();
                mass[index1 - 1] = add2(mass[index1 - 1], index2, b);
            }
            if (maxString < index2) {
                maxString = index2;
            }
            sc1.close();
        }
        sc.close();
        for (int i = 0; i < maxString; i++) {
            for (int j = 0; j < mass.length; j++) {
                if (mass[j].length > i) {
                    System.out.print(mass[j][i] + " ");
                }
            }
            System.out.println();
        }
    }
}