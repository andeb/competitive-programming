import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        //BufferedReader reader = new BufferedReader(new FileReader("main"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int R = 0;
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; ++i) {
            int V = Integer.parseInt(reader.readLine());
            R += V;
        }
        System.out.println(R);

    }

}
