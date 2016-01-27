import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws Exception {
		//Reader r = new Reader("test/t");
		Reader r = new Reader();
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

		int i = 0;
		for (;;) {
			int n = r.readInt(), m = r.readInt();
			if (n == 0 && m == 0) {
				break;
			}

			LinkedList<Integer>[] v = new LinkedList[n];
			for (int j = 0; j < v.length; j++) {
				v[j] = new LinkedList<Integer>();
			}
			for (int j = 0; j < m; j++) {
				int a = r.readInt() - 1;
				int b = r.readInt() - 1;
				v[a].add(b);
				if (r.readInt() == 2) {
					v[b].add(a);
				}
			}

			boolean f = false;
			if (dfs(v)) {
				transpose(v);
				f = dfs(v);
			}
			
			w.write(f ? '1' : '0');
			w.write('\n');
			w.flush();
		}

	}

	private static void transpose(LinkedList<Integer>[] v) {
		// for (int i = 0; i < v.length; i++) {
		// System.out.println(v[i]);
		// }

		LinkedList<Integer>[] t = new LinkedList[v.length];
		for (int j = 0; j < v.length; j++) {
			t[j] = new LinkedList<Integer>();
		}

		for (int i = 0; i < v.length; i++) {
			for (Integer j : v[i]) {
				t[j].add(i);
			}
		}
		for (int i = 0; i < t.length; i++) {
			v[i] = t[i];
		}

		// System.out.println("-------");
		// for (int i = 0; i < v.length; i++) {
		// System.out.println(v[i]);
		// }
	}

	private static boolean dfs(LinkedList<Integer>[] v) {
		// DFS(G)
		int[] color = new int[v.length];
		int[] predecessor = new int[v.length];
		int[] distancia = new int[v.length];
		int time = 0;

		int i = 0;
		//for (int i = 0; i < v.length; i++) {
			for (Integer j : v[i]) {
				if (color[j] == 0) {
					dfs_visit(v, color, j, distancia, time, predecessor);
				}
			}
		//}

		// System.out.println(Arrays.toString(color));
		for (int j = 0; j < color.length; j++) {
			if (color[j] != 2)
				return false;
		}
		return true;
		// for each vertex u E V[G]
		// color[u] <- white
		// pi[u] <- NIL
		// time <- 0
		// for each u E V[G]
		// if color[u] = white
		// DFS visit(u)
	}

	private static void dfs_visit(LinkedList<Integer>[] v, int[] color, int j, int[] distancia, int time, int[] predecessor) {
		// DFS visit(u)
		// color[u] <- gray
		// d[u] <- time <- time + 1
		// for each v E Adj[u]
		color[j] = 1;
		distancia[j] = (time = time + 1);

		for (Integer i : v[j]) {
			if (color[i] == 0) {
				predecessor[i] = j;
				dfs_visit(v, color, i, distancia, time, predecessor);
				color[j] = 2;
			}
		}
		color[j] = 2;
		// if color[u] = white
		// pi[v] <- u
		// DFS visit(v)
		// color[u] <- black
		// f [u] <- time <- time + 1
	}

	static class Reader {

		private byte[] buf = new byte[0];
		private int pos;
		private int count;

		Reader() {
			this(System.in);
		}

		Reader(String filePath) {
			this(getStream(filePath));
		}

		private static FileInputStream getStream(String filePath) {
			try {
				return new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		Reader(InputStream stream) {
			try {
				int available;
				while ((available = stream.available()) != 0) {
					byte[] bytes = new byte[available];
					stream.read(bytes);

					int oldCount = count;
					count += available;
					buf = Arrays.copyOf(buf, count);
					System.arraycopy(bytes, 0, buf, oldCount, available);
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}

		private int readInt() {
			int count = this.count;

			int offset = pos++;
			while (pos < count && isDigit()) {
				++pos;
			}
			if (pos > count) {
				return 0;
			}
			int v = parseInt(offset);
			ignoreSpaceAndCarriageAndNewLine();
			return v;
		}

		private void ignoreSpaceAndCarriageAndNewLine() {
			int count = this.count;
			byte[] buf = this.buf;

			if (pos < count) {
				byte b = buf[pos];
				while (b == '\n' || b == '\r' || b == ' ') {
					++pos;
					if (pos >= count) {
						break;
					}
					b = buf[pos];
				}
			}
		}

		private boolean isDigit() {
			byte b = buf[pos];
			return b >= 48 && b <= 57;
		}

		private int parseInt(int offset) {
			byte[] buf = this.buf;
			int pos = this.pos;

			int v = 0;
			boolean isNegative = buf[offset] == '-';
			for (int i = isNegative ? offset + 1 : offset; i < pos; ++i) {
				int digit = buf[i] - 48;
				v *= 10;
				v -= digit;
			}
			return isNegative ? v : -v;
		}

	}

}