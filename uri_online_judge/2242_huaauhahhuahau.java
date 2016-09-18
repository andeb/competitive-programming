import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D"))));
		 BufferedReader reader = new BufferedReader(new
		 InputStreamReader(System.in));

		String l = reader.readLine();

		for (int i = 0, j = l.length() - 1; i <= j; i++) {
			char v1 = l.charAt(i);
			if (vogal(v1)) {
				for (; j >= i; j--) {
					char v2 = l.charAt(j);
					if (vogal(v2)) {
						if (v1 != v2) {
							System.out.println("N");
							System.exit(0);
						}
						j--;
						break;
					}
				}
			}
		}
		System.out.println("S");
	}

	static boolean vogal(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

}
