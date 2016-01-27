import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        Reader s = new Reader();
        //Reader s = new Reader("test/LOTICA");

        while (true) {
            int N = s.readInt();
            if (N == 0) {
                break;
            }

            for (int i = 0; i < N; ++i) {
                int o = -1;

                boolean noSolution = false;
                for (int j = 0; j < 5; ++j) {
                    int v = s.readInt();

                    if (noSolution) {
                        continue;
                    }

                    if (v <= 127) {
                        if (o > -1) {
                            noSolution = true;
                            continue;
                        }
                        o = j;
                    }
                }
                if (noSolution || o == -1) {
                    System.out.println("*");
                } else {
                    System.out.println((char) ('A' + o));
                }
            }

        }
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
