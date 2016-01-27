import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/RECUPERA")));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = 0;
        m: while (reader.ready()) {
            String line = reader.readLine();
            String[] split = reader.readLine().split(" ");

            int[] v = new int[Integer.parseInt(line)];
            for (int i = 0; i < v.length; i++) {
                v[i] = Integer.parseInt(split[i]);
            }

            int soma = 0;
            for (int i = 0; i < v.length; i++) {
                if (v[i] == soma) {
                    System.out.printf("Instancia %d\n%d\n\n", ++c, v[i]);
                    continue m;
                }
                soma += v[i];
            }
            System.out.printf("Instancia %d\nnao achei\n\n", ++c);
        }

        //writer.flush();
    }

}
