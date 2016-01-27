import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("test/MARCIAN1")));

        String[] split = r.readLine().split(" ");

        int L = Integer.parseInt(split[0]), A = Integer.parseInt(split[1]),
        /**/P = Integer.parseInt(split[2]), R = Integer.parseInt(split[3]);

        double hyp1 = Math.sqrt(Math.pow(L / 2, 2) + Math.pow(P / 2, 2));
        double hyp2 = Math.sqrt(Math.pow(hyp1, 2) + Math.pow(A / 2, 2));

        System.out.println(hyp2 <= R ? "S" : "N");
    }

}
