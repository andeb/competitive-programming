import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        //        Reader reader = new Reader("TEST/ODDOREVE");
        Reader reader = new Reader();

        while (true) {
            int N = reader.readInt();
            if (N == 0) {
                break;
            }

            int[] mary = toInt(N, reader);
            int[] john = toInt(N, reader);

            for (int i = 0; i < mary.length; ++i) {
                int m = mary[i];
                boolean isPar = m % 2 == 0;
                for (int k = 0; k < john.length; ++k) {
                    int j = john[k];
                    if (j == -1) {
                        continue;
                    }
                    if (isPar && j % 2 == 1) {
                        john[k] = -1;
                        --N;
                        break;
                    }
                    if (!isPar && j % 2 == 0) {
                        john[k] = -1;
                        --N;
                        break;
                    }
                }
            }

            System.out.println(N);
        }
    }

    private static int[] toInt(int n, Reader reader) {
        int[] s = new int[n];
        for (int i = 0; i < n; ++i) {
            s[i] = reader.readInt();
        }
        return s;
    }

    static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        Reader() {
            this(System.in);
        }

        Reader(String filePath) throws FileNotFoundException {
            this(new FileInputStream(filePath));
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
