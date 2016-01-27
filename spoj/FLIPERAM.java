import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
      Reader reader = new Reader();
//        Reader reader = new Reader("test/FLIPERAMA");

       BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out), 8192 * 4);

       int N = reader.readInt();
       int M = reader.readInt();

       int[] b = new int[N];
       for (int i = 0; i < b.length; ++i)
           b[i] = reader.readInt();

       if (M > 5) {
           quickSort(b);
           for (int i = N - 1, j = 0; j < M; --i, ++j) {
               w.append(Integer.toString( b[i] ));
               w.append('\n');
           }
       } else {
           int g = -1;
           for (int i = 0; i < b.length; ++i) {
               if (b[i] > g) g = b[i];
           }

           int nt = -1;
           int t;
           for (int j = 0; j < M;) {
               for (int i = 0; i < b.length && j < M; ++i) {
                   t = b[i];
                   if (t == g) {
                       w.append(Integer.toString( g ));
                       w.append('\n');
                       ++j;
                   }
                   if (t < g && t > nt) nt = t;
               }
               g = nt;
               nt = -1;
           }
       }

       w.flush();
   }

  private static void quickSort(int v[]) {
      qsort(v, 0, v.length - 1);
  }

  private static void qsort(int[] array, int begin, int end) {
      if (end > begin) {
          int index = particiona(array, begin, end);
          qsort(array, begin, index - 1);
          qsort(array, index + 1, end);
      }
  }

  private static int particiona(int[] array, int begin, int end) {
      int index = begin;
      int pivot = array[index];
      troca(array, index, end);
      for (int i = index = begin; i < end; ++i) {
          if (array[i] <= pivot) {
              troca(array, index++, i);
          }
      }
      troca(array, index, end);
      return (index);
  }

  private static void troca(int[] v, int a, int b) {
      int tmp = v[a];
      v[a] = v[b];
      v[b] = tmp;
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

}
