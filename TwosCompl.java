public class TwosCompl {
    public static void main(String[] args) {
        System.out.println(4 & 1);
    }

    // Take a DECIMAL number and convert using two's complement to HEXADECIMAL
    static String negate(String s) {
        int i = Integer.parseInt(s);
        i = ~i + 1;
        return Integer.toHexString(i);
    }
}
