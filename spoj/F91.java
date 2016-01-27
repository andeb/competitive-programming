import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/F91")));

        //Reader reader = new Reader("test/F91");
        Reader reader = new Reader();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int i = reader.readInt();
            if (i == 0) {
                break;
            }

            int r = f91(i);
            writer.append("f91(" + i + ") = " + r + "\n");
        }

        writer.flush();
    }

    private static int f91(int N) {
        // Se N <= 100, então f91 (N) = f91 (f91 (N + 11));
        // Se N >= 101, então f91 (N) = N - 10.
        if (N < 101) {
            N = f91(f91(N + 11));
        } else {
            N = N - 10;
        }
        return N;
    }

    private static class Reader {

        private List<Byte> buf = new ArrayList<Byte>(1024);

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
                List<Byte> buf = this.buf;

                byte readed;
                while ((readed = (byte) stream.read()) != -1) {
                    buf.add(readed);
                }
                count = buf.size();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //try {
            //    List<Byte> buf = this.buf;
            //
            //    int available;
            //    while ((available = stream.available()) != 0) {
            //        byte[] bufArray = new byte[available];
            //        stream.read(bufArray);
            //
            //        for (byte b : bufArray) {
            //            buf.add(b);
            //        }
            //    }
            //    count = buf.size();
            //} catch (IOException e) {
            //    throw new RuntimeException(e);
            //}
        }

        private int readInt() {
            // assert notIsInEof()
            int offset = pos++;
            while (pos < count && isDigit()) {
                ++pos;
            }
            int v = parseInt(offset);
            ignoreSpaceAndCarriageAndNewLine();
            return v;
        }

        private void ignoreSpaceAndCarriageAndNewLine() {
            // assert notIsInEof()
            if (pos < count) {
                byte b = buf.get(pos);
                while (b == '\n' || b == '\r' || b == ' ') {
                    ++pos;
                    if (pos >= count) {
                        break;
                    }
                    b = buf.get(pos);
                }
            }
        }

        private boolean isDigit() {
            byte b = buf.get(pos);
            return b >= 48 && b <= 57;
        }

        private int parseInt(int offset) {
            int v = 0;
            boolean isNegative = buf.get(offset) == '-';
            for (int i = isNegative ? offset + 1 : offset; i < pos; ++i) {
                int digit = buf.get(i) - 48;
                v *= 10;
                v -= digit;
            }
            return isNegative ? v : -v;
        }

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
