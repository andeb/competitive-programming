import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("TEST/CFATORES")));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] primes = sieve(1000000 / 2);

        int r;
        while ((r = Integer.parseInt(reader.readLine())) != 0) {
            System.out.printf("%d : %d\n", r, c(r, primes));
        }
    }

    public static int[] sieve(int n) {
        int max = (int) Math.sqrt(n);

        int[] tmp = new int[n - 2];
        for (int i = 2; i < n; ++i) {
            tmp[i - 2] = i;
        }

        for (int i = 2; i <= max; ++i) {
            if (tmp[i - 2] != 0) {
                for (int j = i + i; j < n; j += i) {
                    tmp[j - 2] = 0;
                }
            }
        }

        int[] primes = new int[78498];
        for (int i = 0, k = 0; i < n - 2; ++i) {
            if (tmp[i] != 0) primes[k++] = tmp[i];
        }

        return primes;
    }

    private static int c(int v, int[] primes) {
        if (v == 1) return 1;

        int r = 0;
        try {
            for (int i = 0; v != 1; ++i) {
                boolean foi = false;
                while (v % primes[i] == 0) {
                    foi = true;
                    v /= primes[i];
                }
                if (foi) ++r;
            }
        } catch (Exception e) {
            // oi, to vergonha disso, rs
            return 1;
        }
        return r;
    }

    static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        Reader() {
            this(System.in);
        }

        Reader(String filePath) {
            this(getInputStream(filePath));
        }

        private static FileInputStream getInputStream(String filePath) {
            try {
                return new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        Reader(InputStream stream) {
            try {
                int available;
                while ((available = stream.available()) != 0) {
                    byte[] bytes = new byte[available];
                    stream.read(bytes);

                    int oldCount = count;
                    count += available;
                    buf = Arrays.copyOf(buf, count);
                    System.arraycopy(bytes, 0, buf, oldCount, available);
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        private int readInt() {
            int count = this.count;

            // assert notIsInEof()
            int offset = pos++;
            while (pos < count && isDigit()) {
                ++pos;
            }
            // TODO
            if (pos >= count) {
                return 0;
            }
            int v = parseInt(offset);
            ignoreSpaceAndCarriageAndNewLine();
            return v;
        }

        private void ignoreSpaceAndCarriageAndNewLine() {
            // assert notIsInEof()
            int count = this.count;
            byte[] buf = this.buf;

            if (pos < count) {
                byte b = buf[pos];
                while (b == '\n' || b == '\r' || b == ' ') {
                    ++pos;
                    if (pos >= count) {
                        break;
                    }
                    b = buf[pos];
                }
            }
        }

        private boolean isDigit() {
            byte b = buf[pos];
            return b >= 48 && b <= 57;
        }

        private int parseInt(int offset) {
            byte[] buf = this.buf;
            int pos = this.pos;

            int v = 0;
            boolean isNegative = buf[offset] == '-';
            for (int i = isNegative ? offset + 1 : offset; i < pos; ++i) {
                int digit = buf[i] - 48;
                v *= 10;
                v -= digit;
            }
            return isNegative ? v : -v;
        }

    }

}
