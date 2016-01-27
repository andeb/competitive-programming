import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // Reader s = new Reader("TEST/METEORO");
        Reader s = new Reader(System.in);

        int i = 0;
        while (true) {
            int X1 = s.readInt(), Y1 = s.readInt(), X2 = s.readInt(), Y2 = s.readInt();
            if (X1 == 0 && X2 == 0 && Y1 == 0 && Y2 == 0) {
                break;
            }
            if (X2 < X1) {
                X2 ^= X1;
                X1 ^= X2;
                X2 ^= X1;
            }
            if (Y2 < Y1) {
                Y2 ^= Y1;
                Y1 ^= Y2;
                Y2 ^= Y1;
            }

            int N = s.readInt();
            int R = 0;
            for (int j = 0; j < N; ++j) {
                int X = s.readInt(), Y = s.readInt();

                if (X >= X1 && X <= X2 && Y >= Y1 && Y <= Y2) {
                    ++R;
                }
            }
            System.out.printf("Teste %d\n%d\n\n", ++i, R);
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

        // TODO
        //private int readLong() {
        //    while () {
        //        
        //    }
        //}
        //
        //private long parseLong(int offset) {
        //    long v = 0;
        //    boolean isNegative = buf[offset] == '-';
        //    for (int i = isNegative ? offset + 1 : offset + 0; i < pos; ++i) {
        //        long digit = buf[i] - 48;
        //        v *= 10;
        //        v -= digit;
        //    }
        //    return v;
        //}

    }

}
