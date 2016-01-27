import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		//Reader r = new Reader("test/SUBPRIME");
		Reader r = new Reader();
		// BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

		int c = r.readInt();
		
		if (c == 0) {
			System.out.println("E");
		} else if (c < 36) {
			System.out.println("D");
		} else if (c < 61) {
			System.out.println("C");
		} else if (c < 86) {
			System.out.println("B");
		} else
			System.out.println("A");
		
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
