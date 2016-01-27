import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        int HORAS = 60 * 24;

        // Reader reader = new Reader("TEST/ALADES");
        Reader reader = new Reader();

        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int H1 = reader.readInt();
            int M1 = reader.readInt();
            int H2 = reader.readInt();
            int M2 = reader.readInt();

            if (H1 == 0 && M1 == 0 && H2 == 0 && M2 == 0) {
                break;
            }

            if (H1 < H2 || (H1 == H2 && M1 < M2)) {
                w.append(Integer.toString(((H2 * 60) + M2) - ((H1 * 60) + M1)));
                w.append('\n');
            } else if (H1 > H2) {
                w.append(Integer.toString(((HORAS - ((H1 * 60) + M1)) + ((H2 * 60) + M2))));
                w.append('\n');
            } else {
                w.append(Integer.toString((HORAS - (M1 - M2))));
                w.append('\n');
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
