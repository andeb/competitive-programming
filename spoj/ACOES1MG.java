import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		// BufferedReader r = new BufferedReader(new InputStreamReader(
		// new FileInputStream("input")));
		Reader r = new Reader();

		for (;;) {
			int a = r.readInt(), b = r.readInt();
			if (a == 0 && b == 0)
				break;

			int t = c(a, b, 1);
			System.out.println(t);
		}

	}

	private static int c(int a, int b, int i) {
		if (a <= b)
			return 1;
		if (a % 2 == 0) {
			return c(a / 2, b, i) * 2;
		} else {
			return c(a / 2, b, i) + c(a / 2 + 1, b, i);
		}
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

			// assert notIsInEof()
			int offset = pos++;
			while (pos < count && isDigit()) {
				++pos;
			}
			// TODO
			if (pos > count) {
				return 0;
			}
			int v = parseInt(offset);
			ignoreSpaceAndCarriageAndNewLine();
			return v;
		}

		private void ignoreSpaceAndCarriageAndNewLine() {
			// assert notIsInEof()
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

		public boolean eof() {
			// TODO Auto-generated method stub
			return false;
		}

		// TODO
		// private int readLong() {
		// while () {
		//
		// }
		// }
		//
		// private long parseLong(int offset) {
		// long v = 0;
		// boolean isNegative = buf[offset] == '-';
		// for (int i = isNegative ? offset + 1 : offset + 0; i < pos; ++i) {
		// long digit = buf[i] - 48;
		// v *= 10;
		// v -= digit;
		// }
		// return v;
		// }

	}

}
