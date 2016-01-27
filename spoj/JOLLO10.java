import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        // Reader r = new Reader("test/JOLLO");
        Reader r = new Reader();

        while (true) {
            int a = r.readInt(), b = r.readInt(), c = r.readInt(), x = r.readInt(), y = r.readInt();

            if (a == 0 && b == 0 && c == 0 && x == 0 && y == 0) break;

            int[] q = new int[] { a, b, c };
            int[] w = new int[] { a, b, c, x, y };
            Arrays.sort(q);

            int ix = f(q, x);
            int iy = f(q, y);

            if (ix == 3 && iy == 3) {
                System.out.println(next(w, 0));
            } else {
                if (ix == 3 || iy == 3)
                    cx(q, w, Math.min(ix, iy));
                else if (ix == 2 || iy == 2)
                    cy(q, w, Math.min(ix, iy));
                else
                    System.out.println("-1");
            }

        }
    }

    private static void cx(int[] q, int[] w, int iy) {
        switch (iy) {
            case 0:
                System.out.println(next(w, q[2]));
                break;
            case 1:
                System.out.println(next(w, q[2]));
                break;
            case 2:
                System.out.println(next(w, q[1]));
                break;
        }
    }

    private static void cy(int[] q, int[] w, int ix) {
        switch (ix) {
            case 0:
                System.out.println("-1");
                break;
            case 1:
                System.out.println("-1");
                break;
            case 2:
                System.out.println(next(w, q[1]));
                break;
        }
    }

    private static int f(int[] q, int x) {
        return (-Arrays.binarySearch(q, x)) - 1;
    }

    private static int next(int[] q, int w) {
        e: for (;;) {
            if (++w == 53) return -1;
            for (int i = 0; i < q.length; i++) {
                if (q[i] == w) continue e;
            }
            break;
        }
        return w;
    }

    private static int max(int... f) {
        int e = f[0];
        for (int i = 1; i < f.length; ++i)
            if (f[i] > e) e = f[i];
        return e;
    }

    private static int min(int[] a, int... e) {
        int b = Integer.MAX_VALUE;
        w: for (int i = 0; i < a.length; ++i) {
            int t = a[i];
            if (t < b) {
                for (int j = 0; j < e.length; ++j)
                    if (e[j] == t) continue w;
                b = a[i];
            }
        }
        return b;
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
