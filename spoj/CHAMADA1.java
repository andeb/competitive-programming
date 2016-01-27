import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("TEST/CHAMADA1")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] l = reader.readLine().split(" ");

        int n, k;
        n = Integer.parseInt(l[0]);
        k = Integer.parseInt(l[1]);

        String[] a = new String[n];
        for (int i = 0; i < n; ++i) {
            a[i] = reader.readLine();
        }
        Arrays.sort(a);

        System.out.println(a[k - 1]);
    }
}
