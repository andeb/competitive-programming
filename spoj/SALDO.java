import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
//                Reader r = new Reader("test/SALDO");
        Writer w = new Writer();

        int n, j, b = 0, e = 0, i, seq, max, ini, cont = 0;
        while ((n = r.readInt()) != 0) {
            i = 1;

            seq = 0; max = -1; ini = 1;

            while (i <= n) {
                j = r.readInt() -  r.readInt();

                if ((j + seq) >= 0) {
                    seq += j;
                    if (seq > max) {
                        max = seq;
                        b = ini;
                        e = i;
                    }
                    if (seq == max && (e - ini) < (i - ini)) {
                        b = ini;
                        e = i;
                    }
                } else {
                    ini = i + 1;
                    seq = 0;
                }
                i++;
            }
            w.writef("Teste %d\n", ++cont);

            if (max > 0)
                w.writef("%d %d\n\n", b, e);
            else
                w.writef("nenhum\n\n");
        }

        w.f();
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
            if (pos > count) {
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

    static class Writer {

        private BufferedWriter w = newWriter();

        private BufferedWriter newWriter() {
            return new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void write(char i) {
            iwrite(Character.toString(i));
        }

        public void write(byte i) {
            iwrite(Byte.toString(i));
        }

        public void write(short i) {
            iwrite(Short.toString(i));
        }

        public void write(int i) {
            iwrite(Integer.toString(i));
        }

        public void write(long i) {
            iwrite(Long.toString(i));
        }

        public void write(float i) {
            iwrite(Float.toString(i));
        }

        public void write(double i) {
            iwrite(Double.toString(i));
        }

        public void write(int i, int radix) {
            iwrite(Integer.toString(i, radix));
        }

        public void writef(String str) {
            iwrite(str);
        }

        public void writef(String str, Object... args) {
            iwrite(String.format(str, args));
        }

        public void f() {
            try {
                w.flush();
            } catch (IOException e) {
                throw new Error();
            }
        }

        private void iwrite(String str) throws Error {
            try {
                w.append(str);
            } catch (IOException e) {
                throw new Error();
            }
        }

    }

}
