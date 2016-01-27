import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/DAMA")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (reader.ready()) {
            String l = reader.readLine();
            if ("0 0 0 0".equals(l)) {
                break;
            }
            String[] s = l.split(" ");

            int x1 = Integer.parseInt(s[0]);
            int y1 = Integer.parseInt(s[1]);
            int x2 = Integer.parseInt(s[2]);
            int y2 = Integer.parseInt(s[3]);

            if (x1 == x2 && y1 == y2) {
                writer.append("0\n");
                continue;
            }

            if ((x1 != x2 && y1 == y2) || (x1 == x2 && y1 != y2)) {
                writer.append("1\n");
                continue;
            }

            if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
                writer.append("1\n");
                continue;
            }

            writer.append("2\n");
        }

        writer.flush();
    }

}
