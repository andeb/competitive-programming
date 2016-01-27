import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        // Reader r = new Reader("TEST/POLEPOS");
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int N = r.readInt();
            if (N == 0) {
                break;
            }

            int i;
            int[] M = new int[N];
            for (i = 0; i < N; ++i) {
                int C = r.readInt(), P = r.readInt();
                int NP = i + P;

                if (NP < 0 || NP >= M.length || M[NP] > 0) {
                    break;
                }
                M[NP] = C;
            }

            if (i == M.length) {
                StringBuilder b = new StringBuilder(N * 6);
                for (int j = 0; j < M.length; ++j) {
                    if (j > 0) {
                        b.append(' ');
                    }
                    b.append(M[j]);
                }
                b.append('\n');
                w.append(b.toString());
            } else {
                for (++i; i < N; ++i) {
                    r.readInt();
                    r.readInt();
                }
                w.append("-1\n");
            }
        }

        w.flush();
    }

    private static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        private Reader() {
            this(System.in);
        }

        private Reader(String filePath) {
            this(getFileInputStream(filePath));
        }

        private static FileInputStream getFileInputStream(String filePath) {
            try {
                return new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }
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
