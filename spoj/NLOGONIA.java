import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        // Reader r = new Reader("NLOGONIA");
        Reader r = new Reader();

        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        for (;;) {
            int N = r.nextInt();
            if (N == 0) {
                break;
            }

            int x1 = r.nextInt(), y1 = r.nextInt();
            for (int i = 0; i < N; ++i) {
                int x2 = r.nextInt(), y2 = r.nextInt();
                if (x1 == x2 || y1 == y2) {
                    w.write("divisa\n");
                } else {

                    if (x1 > x2) {
                        if (y1 > y2) {
                            w.write("SO\n");
                        } else {
                            w.write("NO\n");
                        }
                    } else {
                        if (y1 > y2) {
                            w.write("SE\n");
                        } else {
                            w.write("NE\n");
                        }
                    }

                }
            }
        }

        w.flush();
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
