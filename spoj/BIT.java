import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int i = 0;
		while (true) {
			String line = reader.readLine();
			int bits = Integer.parseInt(line);

			if (bits == 0) {
				break;
			}

			int cinquenta = bits / 50;
			bits %= 50;

			int dez = bits / 10;
			bits %= 10;

			int cinco = bits / 5;
			bits %= 5;

			int um = bits;

			System.out.printf("Teste %d\n%d %d %d %d\n\n", ++i, cinquenta, dez,
					cinco, um);
		}
	}

}
