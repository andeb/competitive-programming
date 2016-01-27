import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.BitSet;

public class Main {

	public static void main(String[] args) throws IOException {

		// Reader r = new Reader("test/MAGIC11");
		Reader r = new Reader();
		// BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

		boolean s = true;
		int k = r.readInt();
		int[] column = new int[k];
		int e = -1, diagonal1 = 0, diagonal2 = 0;
		BitSet u = new BitSet(k * k);
		for (int i = 0; i < k; i++) {
			int line = 0;
			for (int j = 0; j < k; j++) {
				int l = r.readInt();
				if (u.get(l)) {
					s = false;
					break;
				}
				u.set(l);

				column[i] += l;
				line += l;

				if (i == j) {
					diagonal1 += l;
				}
				if (k - (i + 1) == j) {
					diagonal2 += l;
				}

				// fast check
				if (e != -1 && (diagonal1 > e || diagonal2 > e)) {
					s = false;
					break;
				}
			}
			if (e == -1) {
				e = line;
			} else {
				if (e != line) {
					s = false;
					break;
				}
			}

			for (int m : column) {
				if (m > e) {
					s = false;
					break;
				}
			}
		}
		for (int i : column) {
			if (i != e) {
				s = false;
				break;
			}
		}

		if (s) {
			w.append("" + e);
		} else {
			w.append('0');
		}
		w.flush();

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
