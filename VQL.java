public class VQL {
    public static void main(String[] args) {
        String vqlBin = binToVQLBin("100000000000000");
        String splitted = splitEights(vqlBin);
        String result = parseBinsToHex(splitted);
        System.out.println(result);
    }

    static String binToVQLBin(String bin) {
        // Given a binary String, convert to VQL form
        // i.e. 110 0000 0000 0000 ->
        // [1]...1 [1]1000000 [0]0000000

        int pass = 0; // Resets to 0 when = 7 at the end of the loop.
        int flag = 0;
        String _new = "";
        // We need this variable to track where to put the "next" hex value.
        for (int i = bin.length() - 1; i >= 0; i--) {
            String sub = bin.substring(i, i + 1);
            if (!sub.equals("1") && !sub.equals("0"))
                return "";
            _new = sub + _new;
            pass++;
            if (pass == 7 && flag == 0) {
                _new = "0" + _new;
                flag++;
                pass = 0;
            } else if (pass == 7) {
                _new = "1" + _new;
                pass = 0;
            }
        }
        if (pass != 0) {
            int fillerZeros = 7 - pass;
            String fillers = "";
            while (fillerZeros > 0) {
                fillers = "0" + fillers;
                fillerZeros--;
            }
            _new = "1" + fillers + _new;
        }
        return _new;
    }

    static String splitEights(String bin) {
        String _new = "";
        if (bin.length() % 8 != 0) {
            return "";
        }
        for (int i = 0; i < bin.length(); i++) {
            if ((i + 1) % 8 == 0) {
                if (_new == "") {
                    _new = bin.substring(i - 7, i + 1);
                } else {
                    _new = _new + " " + bin.substring(i - 7, i + 1);
                }
            }
        }
        return _new;
    }

    static String parseBinsToHex(String bin) {
        String[] toParse = bin.split(" ");
        String result = "";
        for (String s : toParse) {
            int i = Integer.parseInt(s, 2);
            if (result == "") {
                result = Integer.toHexString(i) + " ";
            } else {
                result = result + Integer.toHexString(i) + " ";
            }
        }
        return result;
    }
}