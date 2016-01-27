import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

    private static int SOMA = 285;

    public static void main(String[] args) throws FileNotFoundException {
        // Reader r = new Reader("a");
        Reader r = new Reader();

        int inst = r.readInt();
        for (int i = 1; i <= inst; i++) {
            System.out.print("Instancia " + i + "\n");
            System.out.print((Verifica(r) ? "SIM" : "NAO") + "\n\n");
        }
    }

    static boolean Verifica(Reader r) {
        int[] lin = new int[9];
        int[] col = new int[9];
        int[][] sub = new int[3][3];

        int i, j = 0;
        e: for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                int n = r.readInt();
                n *= n;
                lin[i] += n;
                col[j] += n;
                sub[i / 3][j / 3] += n;
                if (i == 8 && col[j] != SOMA) break e;
            }
            if (lin[i] != SOMA) break;
        }
        if (i != 9 || j != 9) {
            for (; i < 9; ++i) {
                for (++j; j < 9; j++)
                    r.readInt();
                j = 0;
            }
            return false;
        }

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (sub[i][j] != SOMA) return false;
            }
        }

        return true;
    }

    private static boolean v(int[][] qw) {
        int[] lin = new int[9];
        int[] col = new int[9];
        int[][] sub = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = qw[i][j];
                lin[i] += n;
                col[j] += n;
                sub[i / 3][j / 3] += n;

                if (i == 8 && col[j] != SOMA) return false;
            }
            if (lin[i] != SOMA) return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sub[i][j] != SOMA) return false;
            }
        }

        return true;

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

            int offset = pos++;
            while (pos < count && isDigit()) {
                ++pos;
            }
            if (pos > count) {
                return 0;
            }
            int v = parseInt(offset);
            ignoreSpaceAndCarriageAndNewLine();
            return v;
        }

        private void ignoreSpaceAndCarriageAndNewLine() {
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
