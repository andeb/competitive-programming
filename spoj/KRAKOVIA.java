import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/KRAKOVIA")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int i = 0;
        while (reader.ready()) {
            String line = reader.readLine();
            String[] split = line.split(" ");

            int n = Integer.parseInt(split[0]);
            BigInteger p = new BigInteger(split[1]);

            if (n == 0 && p.intValue() == 0) {
                return;
            }

            BigInteger q = new BigInteger("0");
            for (int j = 0; j < n; j++) {
                line = reader.readLine();

                q = q.add(new BigInteger(line));
            }

            BigInteger r = q.divide(p);
            System.out.printf("Bill #%d costs %s: each friend should pay %s\n\n", ++i, q, r);
        }

        writer.flush();
    }

}
