import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
//		 BufferedReader reader = new BufferedReader(new InputStreamReader(new
//		 FileInputStream(new File("D"))));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] e = new int[n];
		int[] d = new int[n];

		String[] split = reader.readLine().split(" ");
		int[] v = new int[n];
		for (int i = 0; i < v.length; i++) {
			v[i] = Integer.parseInt(split[i]);
		}

		e[0] = 1;
		d[n - 1] = 1;

		for (int i = 1; i < n; i++) {
			e[i] = Math.min(e[i - 1] + 1, v[i]);
		}
		for (int i = n - 2; i >= 0; i--) {
			d[i] = Math.min(d[i + 1] + 1, v[i]);
		}

		int r = 0;
		for (int i = 0; i < n; i++) {
			r = Math.max(r, Math.min(e[i], d[i]));
		}

		System.out.println(r);
	}

}
