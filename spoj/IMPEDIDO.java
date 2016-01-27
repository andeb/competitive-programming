import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/IMPEDIDO")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        m: while (reader.ready()) {
            String line = reader.readLine();
            String[] split = line.split(" ");

            int aS = Integer.parseInt(split[0]);
            int dS = Integer.parseInt(split[1]);

            if (aS == 0 && dS == 0) {
                break;
            }

            int[] a = new int[aS];
            line = reader.readLine();
            split = line.split(" ");
            for (int i = 0; i < aS; i++) {
                a[i] = Integer.parseInt(split[i]);
            }

            int[] d = new int[dS];
            line = reader.readLine();
            split = line.split(" ");
            for (int i = 0; i < dS; i++) {
                d[i] = Integer.parseInt(split[i]);
            }

            int i = Integer.MAX_VALUE;
            for (int j : a) {
                if (j < i) {
                    i = j;
                }
            }

            int m = 0;
            for (int j : d) {
                if (j <= i) {
                    ++m;
                    if (m == 2) {
                        System.out.println("N");
                        continue m;
                    }
                }
            }
            System.out.println("Y");
        }

        writer.flush();
    }

}
