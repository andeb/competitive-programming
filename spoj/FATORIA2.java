import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null) {

            long result = 1;

            int calc = Integer.parseInt(line);
            for (int i = 1; i <= calc; i++) {
                result *= i;
            }

            System.out.println(result);
        }

    }

}
