import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        //Reader r = new Reader();

        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("TEST/CONTRATO")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String l;
        while ((l = reader.readLine()) != null) {
            if ("0 0".equals(l)) {
                break;
            }

            String[] s = l.split(" ");

            String replace = s[1].replace(s[0], "");

            boolean temDiferenteZero = false;
            for (int i = 0; i < replace.length(); ++i) {
                if (replace.charAt(i) != '0') {
                    temDiferenteZero = true;
                    break;
                }
            }

            if (temDiferenteZero) {
                System.out.println(replace);
            } else {
                System.out.println("0");
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
