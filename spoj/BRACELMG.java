import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static <T> void main(String... args) throws Exception {

        //Reader r = new Reader("t/ACTUAL");
        //Reader r = new Reader();
//        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("t/ACTUAL")));
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {

            String l = r.readLine();
            int j = l.indexOf(' ');
            String[] split = new String[] { l.substring(0, j), l.substring(j + 1) };
            
            StringBuilder b = new StringBuilder(split[1].length() * 3);
            b.append(split[1]).reverse().append(split[1]).append(split[1]);
            String s = b.toString();
            
            w.append(s.contains(split[0]) ? 'S' : 'N');
            w.append('\n');
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

    }

}
