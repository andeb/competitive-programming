import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;



public class Main {

    public static void main(String[] args) throws IOException {


//         BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/JASPION")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(reader.readLine());
        for (int tt = 0; tt < t; tt++) {
            String[] sp = reader.readLine().split(" ");
            int m = Integer.parseInt(sp[0]);
            int n = Integer.parseInt(sp[1]);

            Map<String, String> map = new HashMap<String, String>(m * 2);
            for (int i = 0; i < m; i++) {
               map.put(reader.readLine(), reader.readLine());
            }


            for (int i = 0; i < n; i++) {
                StringBuilder b = new StringBuilder(1024 * 20);


                sp = reader.readLine().split(" ");
                for (int j = 0; j < sp.length; j++) {
                    if (j > 0) b.append(' ');

                    String s = map.get(sp[j]);
                    if (s == null)
                        b.append(sp[j]);
                    else
                        b.append(s);
                }
                b.append('\n');
                writer.append(b);
            }
        }

        writer.flush();
    }

}


