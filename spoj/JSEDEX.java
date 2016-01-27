import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        //        //Reader reader = new Reader("TEST/MARCIAN1");
        //        Reader reader = new Reader();
        //        int L = reader.readInt(), A = reader.readInt(), P = reader.readInt(), R = reader.readInt() * 2;
        //
        //        boolean cabe;
        //        if (L > R || A > R || P > R) {
        //            cabe = false;
        //        } else {
        //            int temp = R - L;
        //
        //            cabe = temp >= A && temp >= P;
        //        }
        //
        //        System.out.println(cabe ? "S" : "N");

        //Reader reader = new Reader("TEST/JSEDEX");
        Reader reader = new Reader();
        int R = reader.readInt(), L = reader.readInt(), A = reader.readInt(), P = reader.readInt();

        boolean cabe = R <= L && R <= A && R <= P;
        System.out.println(cabe ? "S" : "N");

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
