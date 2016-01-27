import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new FileReader(new File("main")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = reader.readLine();
            if ("0".equals(s)) {
                break;
            }

            boolean b = proccess(s);
            append(writer, s, b);
        }

        writer.flush();
    }

    private static boolean proccess(String s) {
        int oi = 0;
        int op = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ((i + 1) % 2 == 0) {
                op += s.charAt(i) - 48;
            } else {
                oi += s.charAt(i) - 48;
            }
        }
        return (oi - op) % 11 == 0;
    }

    private static void append(BufferedWriter writer, String toPrint, boolean isMultiple) throws IOException {
        writer.append(toPrint);
        writer.append(" is");
        if (!isMultiple) {
            writer.append(" not");
        }
        writer.append(" a multiple of 11.\n");
    }
}
