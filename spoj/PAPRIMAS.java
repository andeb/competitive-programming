import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("test/PAPRIMAS")));

        while (reader.ready()) {
            String s = reader.readLine();

            boolean b = proccess(s);
            System.out.printf("It is%s a prime word.\n", b ? "" : " not");
        }
    }

    private static boolean proccess(String s) {
        int r = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c >= 65 && c <= 90) {
                r += c - 38;
            } else {
                r += c - 96;
            }
        }
        return isPrime(r);
    }

    private static boolean isPrime(int r) {
        if (r <= 3) {
            return true;
        }
        if (r % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= r; i += 2) {
            if (r % i == 0) {
                return false;
            }
        }
        return true;
    }
}
