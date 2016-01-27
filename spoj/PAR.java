import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("main")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = 0;
        while (reader.ready()) {
            String s = reader.readLine();
            if ("0".equals(s)) {
                break;
            }

            int numPartidas = Integer.parseInt(s);

            String jA = reader.readLine();
            String jB = reader.readLine();

            writer.append("Teste " + (++c) + "\n");
            for (int i = 0; i < numPartidas; i++) {
                s = reader.readLine();

                String[] split = s.split(" ");
                int vA = Integer.parseInt(split[0]);
                int vB = Integer.parseInt(split[1]);

                writer.append((vA + vB) % 2 == 0 ? jA : jB);
                writer.append("\n");
            }
            writer.append("\n");
        }

        writer.flush();
    }

}
