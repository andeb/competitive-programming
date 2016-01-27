import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/QUADRAD2")));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        //while (reader.ready()) {
        String line = reader.readLine();
        int i = Integer.parseInt(line);
        System.out.println((long) Math.pow(i, 2));
        //}

        //writer.flush();
    }

}
