import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("main")));

        String s = reader.readLine();
        String[] split = s.split(" ");

        int c = c(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        System.out.println(Integer.toString(c));
    }

    private static int c(int m, int a1, int a2) {
        int t = 0, r = 0, r1 = a1, r2 = a2;
        while (r != m) {
            int min = Math.min(r1, r2);

            if (min == r1) {
                r2 -= r1;
                r1 = a1;
                r = r2;
            } else {
                r1 -= r2;
                r2 = a2;
                r = r1;
            }

            t += min;
        }
        return t + r;
    }
}

