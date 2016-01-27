import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("test/COFRE")));


        int c = 0;
        while (true) {
            int n = Integer.parseInt(r.readLine());
            if (n==0) break;

            System.out.println("Teste " + (++c));
            int s = 0;
            for (int i = 0; i < n; ++i) {
                String[] l = r.readLine().split(" ");
                int a = Integer.parseInt(l[0]);
                int b = Integer.parseInt(l[1]);

                s += ( a - b );
                System.out.println(s);
            }
            System.out.println();
        }


    }

}
