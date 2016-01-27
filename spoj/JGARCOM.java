import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        int N = number(), a, b, t = 0;
        for (int i = 0; i < N; ++i) {
            a = number();
            b = number();

            if (a > b) {
                t += b;
            }
        }

        System.out.print(t);
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
