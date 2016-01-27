import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    private final static Pattern debugPattern = Pattern.compile("-Xdebubg|jdwp");

    public static void main(String[] args) throws Exception {

        // Reader r = new Reader("t/TESTE");
        //        Reader r = new Reader();
                BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("t/TESTE")));
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        int qq = Integer.parseInt(r.readLine());
        for (; qq > 0; qq--) {

            String[] split = r.readLine().split(" ");
            int e = Integer.parseInt(split[1]);
            String[] especiais = r.readLine().toLowerCase().split(" |-|:");

            for (int i = 0; i < e; i++) {
                StringBuilder builder = new StringBuilder(100);
                String[] line = r.readLine().toLowerCase().split(" |-|:");

                for (int j = 0; j < line.length; j++) {
                    if (line[j].isEmpty()) {
                        continue;
                    }
                    if (spec(line[j], especiais)) {
                        if (j == 0) {
                            continue;
                        }
                        builder.append(line[j].toLowerCase().charAt(0));
                    } else {
                        builder.append(line[j].toUpperCase().charAt(0));
                    }
                }
                System.out.println(builder);
            }

            System.out.println();
        }
    }

    static boolean spec(String a, String[] b) {
        for (String string : b) {
            if (string.equals(a)) {
                return true;
            }
        }
        return false;
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
