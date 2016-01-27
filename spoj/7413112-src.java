import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        // Reader r = new Reader("test/PARPROX");
        Reader r = new Reader();

        int N = r.nextInt();

        int[][] M = new int[N][N];
        for (int i = 0; i < N; ++i) {
            M[i][0] = r.nextInt();
            M[i][1] = r.nextInt();
        }

        double menor = Double.MAX_VALUE;
        for (int i = 0; i < N - 1; ++i) {
            int x1 = M[i][0];
            int y1 = M[i][1];

            for (int j = i + 1; j < N; ++j) {
                int x2 = M[j][0];
                int y2 = M[j][1];

                long c1 = x2 - x1;
                long c2 = y2 - y1;

                double t = Math.sqrt((c1 * c1) + (c2 * c2));

                // System.out.println("i: " + i + " j: " + j + " d: " + t);

                if (t < menor) {
                    menor = t;
                }
            }
        }

        int b = (int) (menor * 10000);
        if (b % 10 >= 5) {
            b += 10;
        }
        System.out.print((double) (b / 10) / 1000);
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

        public int nextInt() {
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
            int v = nextInt(offset);
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

        private int nextInt(int offset) {
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


