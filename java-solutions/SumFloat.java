public class SumFloat {
    public static void main(String args[]) {
        float sum = 0;
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            int beginIndex = 0;
            int endIndex = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(str.charAt(j))) {
                    beginIndex = j;
                    j++;
                    while (j < args[i].length() && Character.isWhitespace(str.charAt(j)) == false) {
                        j++;
                    }
                    String x = args[i].substring(beginIndex, j);
                    sum += Float.parseFloat(x);
                }
            }
        }
        System.out.println(sum);
    }
}
