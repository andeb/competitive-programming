import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        int v = number();

        int t = 7;
        if (v > 10) {
            v -= 10;

            if (v > 20) {
                t += 20;
                v -= 20;

                if (v > 70) {
                    t += 70 * 2;
                    v -= 70;

                    t += v * 5;
                } else {
                    t += v * 2;
                }
            } else {
                t += v;
            }
        }

        System.out.println(t);
    }

    private static int number() throws IOException {
        int b = System.in.read();
        while (b == '\n' || b == '\r') {
            b = System.in.read();
        }

        int r = 0;
        int digit;
        while (b != '\n' && b != '\r' && b != ' ') {
            digit = b - 48;
            r *= 10;
            r -= digit;
            b = System.in.read();
        }
        return -r;
    }

}
