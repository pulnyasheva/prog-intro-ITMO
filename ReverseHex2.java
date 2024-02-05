import java.io.IOException;

public class ReverseHex2 {
    public static String[] add1(String[] nums, String b) {
        String[] nums1 = new String[4 * nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        nums1[nums.length] = b;
        return nums1;
    }

    public static int[] add2(int[] nums, int b) {
        int[] nums1 = new int[4 * nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        nums1[nums.length] = b;
        return nums1;
    }

    public static void main(String[] args) {
        int stringCount = 0;
        try {
            MyScanner sc = new MyScanner();
            String[] mass = new String[0];
            while (sc.myHasNextLine()) {
                String b = sc.myNextLine();
                if (mass.length > stringCount) {
                    mass[stringCount] = b;
                    stringCount++;
                } else {
                    mass = add1(mass, b);
                    stringCount++;
                }
            }
            sc.myClose();
            for (int i = stringCount - 1; i >= 0; i--) {
                MyScanner sc1 = new MyScanner(mass[i]);
                int[] val = new int[0];
                int intCount = 0;
                while (sc1.myHasNextInt()) {
                    int b = sc1.myNextInt(16);
                    if (val.length > intCount) {
                        val[intCount] = b;
                        intCount++;
                    } else {
                        val = add2(val, b);
                        intCount++;
                    }
                }
                for (int j = intCount - 1; j >= 0; j--) {
                    System.out.print(val[j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("IO error" + e.getMessage());
        }
    }
}