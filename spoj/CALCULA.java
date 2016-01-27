import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {

//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/CALCULA")));
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

         BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        int n  ;
        int counter = 1;
        while ( ( n = Integer.parseInt( reader.readLine() )) != 0) {
            String s = reader.readLine();

            int r = 0, v = 0, i = 0;
            for (; s.charAt(i) != '+' && s.charAt(i) != '-'; ++i) {
                int digit = s.charAt(i) - 48;
                r *= 10;
                r -= digit;
            }
            r = -r;

            char operator;
            for (; n > 1; --n) {
                v = 0; operator = s.charAt(i++);
                for (; i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-'; ++i) {
                    int digit = s.charAt(i) - 48;
                    v *= 10;
                    v -= digit;
                }

                if (operator == '+')
                    r -= v;
                else
                    r += v;
            }


            w.append("Teste ").append(Integer.toString(counter++));
            w.append('\n');
            w.append(Integer.toString(r));
            w.append('\n');
            w.append('\n');
        }

        w.flush();
    }

}
