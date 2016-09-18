import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		// Scanner s = new Scanner(new File("current"));

		while (s.hasNext()) {
			int result = 0, k = s.nextInt();

			List<String> list = new ArrayList<String>(k);
			for (int i = 0; i < k; i++)
				list.add(s.next());
			Collections.sort(list);

			String last = null;
			for (String current : list) {
				if (last != null) {
					for (int i = 0; i < current.length() && last.charAt(i) == current.charAt(i); i++)
						result++;
				}
				last = current;
			}
			System.out.println(result);
		}

		s.close();
	}

}