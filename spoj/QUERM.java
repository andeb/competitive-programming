import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("test/QUERM")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int i = 0;
        while (reader.ready()) {
            int N = Integer.parseInt(reader.readLine());
            if (N == 0) {
                break;
            }

            String[] s = reader.readLine().split(" ");

            int[] b = new int[N];
            for (int j = 0; j < N; j++) {
                b[j] = Integer.parseInt(s[j]);
            }

            for (int j = 0; j < b.length; j++) {
                if (b[j] == j + 1) {
                    writer.write("Teste ");
                    writer.write(Integer.toString(++i));
                    writer.write("\n");
                    writer.write(Integer.toString(b[j]));
                    writer.write("\n");
                    writer.write("\n");
                }
            }

        }

        writer.flush();
    }

}
