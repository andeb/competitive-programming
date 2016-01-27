import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader reader = new BufferedReader(new FileReader(new
		// File("m")));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

		while (reader.ready()) {
			String s = reader.readLine();
			if ("0".equals(s)) {
				break;
			}

			int q = c(Integer.parseInt(s));
			writer.append(q + "\n");
		}

		writer.flush();
	}

	private static int c(int c) {
		int r = 0;
		for (int i = 1; i <= c; ++i) {
			r += i * i;
		}
		return r;
	}

}
