import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("TEST/ROBOCOL")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String l;
        while ((l = reader.readLine()) != null) {
            String[] s = l.split(" ");
            int N = Integer.parseInt(s[0]);
            int M = Integer.parseInt(s[1]);
            int S = Integer.parseInt(s[2]);

            if (N == 0 && M == 0 && S == 0) {
                break;
            }

            int i = -1;
            int j = -1;
            int direcao = -1;

            char[][] k = new char[N][];
            for (int ii = 0; ii < N; ++ii) {
                String readLine = reader.readLine();
                k[ii] = readLine.toCharArray();

                if (i > -1 && j > -1) { // should only one be happened??
                    continue;
                }

                // FUCK ! ¬¬

                if (readLine.indexOf('O') >= 0) {
                    i = ii;
                    j = readLine.indexOf('O');
                    direcao = 0;
                    continue;
                }
                if (readLine.indexOf('N') >= 0) {
                    i = ii;
                    j = readLine.indexOf('N');
                    direcao = 1;
                    continue;
                }
                if (readLine.indexOf('L') >= 0) {
                    i = ii;
                    j = readLine.indexOf('L');
                    direcao = 2;
                    continue;
                }
                if (readLine.indexOf('S') >= 0) {
                    i = ii;
                    j = readLine.indexOf('S');
                    direcao = 3;
                }
            }
            String comandos = reader.readLine();

            int figuras = 0;

            for (int pos = 0; pos < S; ++pos) {
                char cc = comandos.charAt(pos);

                switch (cc) {
                    case 'D':
                        direcao = (direcao + 1) % 4;
                        break;
                    case 'E':
                        direcao = ((direcao - 1) + 4) % 4;
                        break;
                    case 'F':
                        switch (direcao) {
                            case 0:
                                if ((j - 1) >= 0 && k[i][j - 1] != '#') {
                                    if (k[i][--j] == '*') {
                                        ++figuras;
                                        k[i][j] = '.';
                                    }
                                }
                                break;
                            case 1:
                                if ((i - 1) >= 0 && k[i - 1][j] != '#') {
                                    if (k[--i][j] == '*') {
                                        ++figuras;
                                        k[i][j] = '.';
                                    }
                                }
                                break;
                            case 2:
                                if ((j + 1) < k[i].length && k[i][j + 1] != '#') {
                                    if (k[i][++j] == '*') {
                                        ++figuras;
                                        k[i][j] = '.';
                                    }
                                }
                                break;
                            case 3:
                                if ((i + 1) < k.length && k[i + 1][j] != '#') {
                                    if (k[++i][j] == '*') {
                                        ++figuras;
                                        k[i][j] = '.';
                                    }
                                }
                                break;
                            default:
                                break;
                        }

                        break;
                }
            }

            System.out.println(figuras);
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
