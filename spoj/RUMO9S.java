import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/RUMO9S")));

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = reader.readLine();
            if ("0".equals(s)) {
                break;
            }

            int r = c(s, 1);
            if (r == -1) {
                writer.append(s);
                writer.append(" is not a multiple of 9.\n");
            } else {
                writer.append(s);
                writer.append(" is a multiple of 9 and has 9-degree ");
                writer.append(Integer.toString(r));
                writer.append(".\n");
            }
        }

        writer.flush();
    }

    private static int c(String s, int degree) {
        int r = 0;
        for (int i = 0; i < s.length(); ++i) {
            r += s.charAt(i) - '0';
        }
        if (r < 9) {
            return -1;
        }
        if (r > 9) {
            return c(Integer.toString(r), degree + 1);
        }
        return degree;
    }

}
