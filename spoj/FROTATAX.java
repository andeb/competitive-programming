import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String[] line = r.readLine().split(" ");
        double a = Double.parseDouble(line[0]);
        double b = Double.parseDouble(line[1]);
        double c = Double.parseDouble(line[2]);
        double d = Double.parseDouble(line[3]);

        double ret1 = b * 100 / a;
        double ret2 = d * 100 / c;

        System.out.println(ret1 > ret2 ? "A" : "G");
    }


}
