import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
//     BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("test/ESTAGIO")));


        int c = 0;
        while (true) {
            int n = Integer.parseInt(r.readLine());
            if (n==0) break;

            System.out.println("Turma " + (++c));
            int[][] a = new int[n][2];
            for (int i = 0; i < n; ++i) {
                String[] sp = r.readLine().split(" ");
                a[i][0] = Integer.parseInt(sp[0]);
                a[i][1] = Integer.parseInt(sp[1]);
            }

            int maior = -1;
            for (int[] is : a) {
                if (is[1] > maior) maior = is[1];
            }

            String sep = "";
            for (int[] is : a) {
                if (is[1] == maior) {
                    System.out.print(sep);
                    System.out.print(is[0]);
                    sep = " ";
                }
            }
            System.out.println();
            System.out.println();
        }


    }

}
