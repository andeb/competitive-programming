import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("main")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        //Reader reader = new Reader("test/POPULAR");
        Reader reader = new Reader();

        while (true) {
            int s = reader.readInt();
            if (s == 0) {
                break;
            }

            int[] r = new int[s];
            for (int i = 0; i < s; ++i) {
                for (int j = 0; j < s; ++j) {
                    r[j] += reader.readInt();
                }
            }

            int m = 0;
            for (int i : r) {
                if (i > m) {
                    m = i;
                }
            }

            writer.append(m + "\n");
        }

        writer.flush();
    }

    private static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        private Reader() {
            this(System.in);
        }

        private Reader(String filePath) throws FileNotFoundException {
            this(new FileInputStream(filePath));
        }

        private Reader(InputStream stream) {
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
                throw new RuntimeException(e);
            }
        }

        private int readInt() {
            int count = this.count;

            // assert notIsInEof()
            int offset = pos++;
            while (pos < count && isDigit()) {
                ++pos;
            }
            // TODO wtf?!
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
