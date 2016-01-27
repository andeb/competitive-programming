import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class FORMULA {

    public static void main(String[] args) {
        // Reader r = new Reader("test/FORMULA");
        Reader r = new Reader();

        while (true) {
            int g = r.readInt(), p = r.readInt();
            if (g == 0 && p == 0) break;

            int[][] t = new int[p][p];
            for (int i = 0; i < g; ++i)
                for (int j = 0; j < p; ++j)
                    ++t[j][r.readInt() - 1];

            int s = r.readInt();
            for (int i = 0; i < s; ++i) {
                int z = r.readInt();

                int[] o = new int[z];
                for (int j = 0; j < z; ++j) {
                    o[j] = r.readInt();
                }

                int[] v = new int[p];
                for (int j = 0; j < p; j++) {
                    int q = 0;
                    for (int k = 0; k < z; k++)
                        q += o[k] * t[j][k];
                    v[j] = q;
                }

                int m = 0;
                for (int j = 0; j < p; ++j) {
                    if (v[j] > m) m = v[j];
                }

                boolean w = true;
                for (int j = 0; j < p; ++j) {
                    if (v[j] == m) {
                        if (!w) System.out.print(' '); else w = false;
                        System.out.print(j + 1);
                    }
                }
                System.out.println();
            }
        }
    }

    static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        Reader() {
            this(System.in);
        }

        Reader(String filePath) {
            this(getStream(filePath));
        }

        private static FileInputStream getStream(String filePath) {
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

        public boolean eof() {
            // TODO Auto-generated method stub
            return false;
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
