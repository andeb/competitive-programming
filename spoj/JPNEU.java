import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

public static void main(String[] args) throws IOException {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(Integer.parseInt(r.readLine()) - Integer.parseInt(r.readLine()));
}

}
