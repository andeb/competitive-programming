import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("ELEVADOR")));

        for (;;) {
            String[] split = r.readLine().split(" ");

            int L = Integer.parseInt(split[0]), C = Integer.parseInt(split[1]),
            /**/R1 = Integer.parseInt(split[2]), R2 = Integer.parseInt(split[3]);

            if (L == 0 && C == 0 && R1 == 0 && R2 == 0) {
                break;
            }

            if ((R1 * 2 > L) || (R1 * 2 > C) || (R2 * 2 > L) || (R2 * 2 > C)) {
                System.out.println("N");
            } else {
                //double hypElevador = Math.hypot(L, C);
                //            double c1 = Math.hypot(R1, C + (R1 - C));
                //            double c2 = Math.hypot(R2, L + (R2 - L));
                //System.out.println(c1 + c2 + R1 + R2 <= hypElevador ? "S" : "N");

                double t1 = L - R1 - R2;
                double t2 = C - R1 - R2;

                double hyp3 = Math.hypot(t1, t2);
                System.out.println(hyp3 >= R1 + R2 ? "S" : "N");
            }
        }
    }

}
