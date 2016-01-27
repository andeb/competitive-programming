import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		 Reader r = new Reader();
//		 Reader r = new Reader("test/DOBRA");
		Writer w = new Writer();
		
		int[] v = new int[] {
		4,
		9,
		25,
		81,
		289,
		1089,
		4225,
		16641,
		66049,
		263169,
		1050625,
		4198401,
		16785409,
		67125249,
		268468225,
		1073807361
		};

		int n, i = 0;
		while ((n = r.readInt()) != -1) {
			w.writef("Teste ");
			w.write (++i);
			w.write ('\n');
			w.write (v[n]);
			w.write ('\n');
			w.write ('\n');
		}
		
		w.f();
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

	static class Writer {

		private final BufferedWriter w = newWriter();

		private BufferedWriter newWriter() {
			return new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void write(char i) {
			iwrite(Character.toString(i));
		}

		public void write(byte i) {
			iwrite(Byte.toString(i));
		}

		public void write(short i) {
			iwrite(Short.toString(i));
		}

		public void write(int i) {
			iwrite(Integer.toString(i));
		}

		public void write(long i) {
			iwrite(Long.toString(i));
		}

		public void write(float i) {
			iwrite(Float.toString(i));
		}

		public void write(double i) {
			iwrite(Double.toString(i));
		}

		public void write(int i, int radix) {
			iwrite(Integer.toString(i, radix));
		}

		public void writef(String str) {
			iwrite(str);
		}

		public void writef(String str, Object... args) {
			iwrite(String.format(str, args));
		}

		public void f() {
			try {
				w.flush();
			} catch (IOException e) {
				throw new Error();
			}
		}

		private void iwrite(String str) throws Error {
			try {
				w.append(str);
			} catch (IOException e) {
				throw new Error();
			}
		}

	}

}

final class Interval<T> implements Comparable<Interval> {

	private double low; // the left endpoint
	private double high; // the right endpoint
	private boolean lopen; // indicates if the left endpoint is excluded
	private boolean ropen; // indicates if the right endpoint is excluded
	private T value; // the value stored in this interval

	/**
	 * Constructs a new interval instance
	 * <p>
	 * Note that {@code value} cannot be null if you want use this {@code interval} as a value storage. If it is null some estimators could not work and generate exceptions.
	 * 
	 * @param interval
	 *            the interval to copy the values from
	 * @param value
	 *            the value stored in this interval
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public Interval(Interval interval, T value) {
		this.low = interval.low;
		this.high = interval.high;
		this.lopen = interval.lopen;
		this.ropen = interval.ropen;
		this.value = value;
	}

	/**
	 * Constructs a new interval instance.
	 * <p>
	 * Note that {@code value} cannot be null if you want use this {@code interval} as a value storage. If it is null some estimators could not work and generate exceptions.
	 * 
	 * @param low
	 *            the left endpoint
	 * @param high
	 *            the right endpoint
	 * @param lopen
	 *            indicates if the left endpoint is excluded (true in this case)
	 * @param ropen
	 *            indicates if the right endpoint is excluded (true in this case)
	 * @param value
	 *            the value stored in this interval
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public Interval(double low, double high, boolean lopen, boolean ropen, T value) {
		if (low > high) {
			throw new IllegalArgumentException("The left endpoint of the interval must be less than " + "the right endpoint.");
		}

		this.low = low;
		this.high = high;
		this.lopen = lopen;
		this.ropen = ropen;
		this.value = value;
	}

	/**
	 * Constructs a new interval instance with no value.
	 * 
	 * @param low
	 *            the left endpoint
	 * @param high
	 *            the right endpoint
	 * @param lopen
	 *            indicates if the left endpoint is excluded (true in this case)
	 * @param ropen
	 *            indicates if the right endpoint is excluded (true in this case)
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public Interval(double low, double high, boolean lopen, boolean ropen) {
		this(low, high, lopen, ropen, null);
	}

	/**
	 * Constructs a new interval instance with left and right endpoints included by default.
	 * <p>
	 * Note that {@code value} cannot be null if you want use this {@code interval} as a value storage. If it is null some estimators could not work and generate exceptions.
	 * 
	 * @param low
	 *            the left endpoint
	 * @param high
	 *            the right endpoint
	 * @param value
	 *            the value stored in this interval
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public Interval(double low, double high, T value) {
		this(low, high, false, false, value);
	}

	/**
	 * Constructs a new interval instance with no value and left and right endpoints included by default.
	 * 
	 * @param low
	 *            the left endpoint
	 * @param high
	 *            the right endpoint
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public Interval(double low, double high) {
		this(low, high, false, false, null);
	}

	/**
	 * Compares this interval with the specified interval for order.
	 * <p>
	 * Any two intervals <i>i</i> and <i>i'</i> satisfy the {@code interval
	 * trichotomy}; that is, exactly one of the following three properties holds:
	 * <ol>
	 * <li><i>i</i> and <i>i'</i> overlap;
	 * <li><i>i</i> is to the left of <i>i'</i> (<i>i.high < i'.low</i>);
	 * <li><i>i</i> is to the right of <i>i'</i> (<i>i'.high < i.low</i>).
	 * </ol>
	 * <p>
	 * Note that if two intervals are equal ({@code i.low = i'.low} and {@code i.high = i'.high}), they overlap as well. But if they simply overlap (for instance {@code i.low < i'.low} and {@code i.high >
	 * i'.high}) they aren't equal. Remember that if two intervals are equal, they have got the same bounds excluded or included.
	 * 
	 * @param interval
	 *            the interval to be compared
	 * @return a negative integer, zero, or a positive integer as this interval is to the left of, overlaps with, or is
	 *         to the right of the specified interval.
	 * @throws NullPointerException
	 *             if {@code interval} is null.
	 */
	public int compareTo(Interval interval) {
		if (interval == null) {
			throw new NullPointerException("Interval cannot be null.");
		}

		if (high < interval.low || high <= interval.low && (ropen || interval.lopen)) {
			return -1;
		}
		if (interval.high < low || interval.high <= low && (interval.ropen || lopen)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns the left endpoint.
	 * 
	 * @return the left endpoint.
	 */
	public double getLow() {
		return low;
	}

	/**
	 * Returns the right endpoint.
	 * 
	 * @return the right endpoint.
	 */
	public double getHigh() {
		return high;
	}

	/**
	 * Indicates if the left endpoint is excluded.
	 * 
	 * @return {@code true} if the left endpoint is excluded, {@code false} otherwise.
	 */
	public boolean isLowExcluded() {
		return lopen;
	}

	/**
	 * Indicates if the right endpoint is excluded.
	 * 
	 * @return {@code true} if the right endpoint is excluded, {@code false} otherwise.
	 */
	public boolean isHighExcluded() {
		return ropen;
	}

	/**
	 * Returns the value stored in this interval.
	 * 
	 * @return the value stored in this interval.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Compares this interval with the specified object for equality.
	 * <p>
	 * Note that two intervals are equal if {@code i.low = i'.low} and {@code i.high = i'.high} and they have got the bounds excluded/included.
	 * 
	 * @param obj
	 *            object to which this interval is to be compared
	 * @return {@code true} if and only if the specified {@code Object} is a {@code Interval} whose low and high are
	 *         equal to this {@code Interval's}.
	 * @see #compareTo(org.gephi.data.attributes.type.Interval)
	 * @see #hashCode
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(this.getClass())) {
			Interval<T> interval = (Interval<T>) obj;
			if (low == interval.low && high == interval.high && lopen == interval.lopen && ropen == interval.ropen) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + (int) (Double.doubleToLongBits(this.low) ^ (Double.doubleToLongBits(this.low) >>> 32));
		hash = 97 * hash + (int) (Double.doubleToLongBits(this.high) ^ (Double.doubleToLongBits(this.high) >>> 32));
		hash = 97 * hash + (this.lopen ? 1 : 0);
		hash = 97 * hash + (this.ropen ? 1 : 0);
		return hash;
	}

	/**
	 * Creates a string representation of the interval with its value.
	 * 
	 * @param timesAsDoubles
	 *            indicates if times should be shown as doubles or dates
	 * @return a string representation with times as doubles or dates.
	 */
	public String toString(boolean timesAsDoubles) {
		return (lopen ? "(" : "[") + low + ", " + high + /* ", " + value + */(ropen ? ")" : "]");
	}

	/**
	 * Returns a string representation of this interval in one of the formats:
	 * <ol>
	 * <li>
     * {@code [low, high, value]}
	 * <li>
     * {@code (low, high, value]}
	 * <li>
     * {@code [low, high, value)}
	 * <li>
     * {@code (low, high, value)}
	 * </ol>
	 * <p>
	 * Times are always shown as doubles
	 * </p>
	 * 
	 * @return a string representation of this interval.
	 */
	@Override
	public String toString() {
		return toString(true);
	}
}

final class IntervalTree<T> {

	private final Node nil; // the sentinel node
	private final Node root; // the root of this interval tree

	/**
	 * Constructs an empty {@code IntervalTree}.
	 */
	public IntervalTree() {
		nil = new Node();
		nil.left = nil.right = nil.p = nil;
		root = nil;
	}

	private void copy(Node x, Node nil) {
		if (x != nil) {
			copy(x.left, nil);
			insert(x.i);
			copy(x.right, nil);
		}
	}

	private boolean compareLow(Interval a, Interval b) {
		if (a.getLow() < b.getLow() || a.getLow() == b.getLow() && (!a.isLowExcluded() || b.isHighExcluded()))
			return true;
		return false;
	}

	/**
	 * Inserts the {@code interval} into this {@code IntervalTree}.
	 * 
	 * @param interval
	 *            an interval to be inserted
	 * @throws NullPointerException
	 *             if {@code interval} is null.
	 */
	public void insert(Interval<T> interval) {
		if (interval == null)
			throw new NullPointerException("Interval cannot be null.");

		insert(new Node(interval));
	}

	private void insert(Node z) {
		z.left = z.right = nil;

		Node y = root;
		Node x = root.left;
		while (x != nil) {
			y = x;
			if (compareLow(z.i, y.i))
				x = x.left;
			else
				x = x.right;
			y.max = Math.max(z.max, y.max);
			if (y.p == root)
				root.max = y.max;
		}
		z.p = y;
		if (y == root)
			root.max = z.max;
		if (y == root || compareLow(z.i, y.i))
			y.left = z;
		else
			y.right = z;
		insertFixup(z);
	}

	private void insertFixup(Node z) {
		Node y = nil;

		z.color = RED;
		while (z.p.color == RED)
			if (z.p == z.p.p.left) {
				y = z.p.p.right;
				if (y.color == RED) {
					z.p.color = BLACK;
					y.color = BLACK;
					z.p.p.color = RED;
					z = z.p.p;
				} else {
					if (z == z.p.right) {
						z = z.p;
						leftRotate(z);
					}
					z.p.color = BLACK;
					z.p.p.color = RED;
					rightRotate(z.p.p);
				}
			} else {
				y = z.p.p.left;
				if (y.color == RED) {
					z.p.color = BLACK;
					y.color = BLACK;
					z.p.p.color = RED;
					z = z.p.p;
				} else {
					if (z == z.p.left) {
						z = z.p;
						rightRotate(z);
					}
					z.p.color = BLACK;
					z.p.p.color = RED;
					leftRotate(z.p.p);
				}
			}
		root.left.color = BLACK;
	}

	/**
	 * Removes all intervals from this {@code IntervalTree} that overlap with the given {@code interval}.
	 * 
	 * @param interval
	 *            determines which intervals should be removed
	 * @throws NullPointerException
	 *             if {@code interval} is null.
	 */
	public void delete(Interval interval) {
		if (interval == null)
			throw new NullPointerException("Interval cannot be null.");

		for (Node n : searchNodes(interval))
			delete(n);
	}

	private void delete(Node z) {
		z.max = Double.NEGATIVE_INFINITY;
		for (Node i = z.p; i != root; i = i.p) {
			i.max = Math.max(i.left.max, i.right.max);
			if (i.p == root)
				root.max = i.max;
		}

		Node y;
		Node x;

		if (z.left == nil || z.right == nil)
			y = z;
		else
			y = succesor(z);
		if (y.left == nil)
			x = y.right;
		else
			x = y.left;
		x.p = y.p;
		if (root == x.p)
			root.left = x;
		else if (y == y.p.left)
			y.p.left = x;
		else
			y.p.right = x;
		if (y != z) {
			if (y.color == BLACK)
				deleteFixup(x);

			y.left = z.left;
			y.right = z.right;
			y.p = z.p;
			y.color = z.color;
			z.left.p = z.right.p = y;
			if (z == z.p.left)
				z.p.left = y;
			else
				z.p.right = y;
		} else if (y.color == BLACK)
			deleteFixup(x);
	}

	private void deleteFixup(Node x) {
		while (x != root.left && x.color == BLACK)
			if (x == x.p.left) {
				Node w = x.p.right;
				if (w.color == RED) {
					w.color = BLACK;
					x.p.color = RED;
					leftRotate(x.p);
					w = x.p.right;
				}
				if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.p;
				} else {
					if (w.right.color == BLACK) {
						w.left.color = BLACK;
						w.color = RED;
						rightRotate(w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = BLACK;
					w.right.color = BLACK;
					leftRotate(x.p);
					x = root.left;
				}
			} else {
				Node w = x.p.left;
				if (w.color == RED) {
					w.color = BLACK;
					x.p.color = RED;
					rightRotate(x.p);
					w = x.p.left;
				}
				if (w.right.color == BLACK && w.left.color == BLACK) {
					w.color = RED;
					x = x.p;
				} else {
					if (w.left.color == BLACK) {
						w.right.color = BLACK;
						w.color = RED;
						leftRotate(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = BLACK;
					w.left.color = BLACK;
					rightRotate(x.p);
					x = root.left;
				}
			}
		x.color = BLACK;
	}

	private void leftRotate(Node x) {
		Node y = x.right;

		x.right = y.left;
		if (y.left != nil)
			y.left.p = x;
		y.p = x.p;
		if (x == x.p.left)
			x.p.left = y;
		else
			x.p.right = y;
		y.left = x;
		x.p = y;

		if (y.p == root)
			root.max = x.max;
		y.max = x.max;
		x.max = Math.max(x.i.getHigh(), Math.max(x.left.max, x.right.max));
	}

	private void rightRotate(Node x) {
		Node y = x.left;

		x.left = y.right;
		if (y.right != nil)
			y.right.p = x;
		y.p = x.p;
		if (x == x.p.left)
			x.p.left = y;
		else
			x.p.right = y;
		y.right = x;
		x.p = y;

		if (y.p == root)
			root.max = x.max;
		y.max = x.max;
		x.max = Math.max(x.i.getHigh(), Math.max(x.left.max, x.right.max));
	}

	private Node succesor(Node x) {
		Node y = x.right;
		if (y != nil) {
			while (y.left != nil)
				y = y.left;
			return y;
		}
		y = x.p;
		while (x == y.right) {
			x = y;
			y = y.p;
		}
		if (y == root)
			return nil;
		return y;
	}

	/**
	 * Returns the interval with the lowest left endpoint.
	 * 
	 * @return the interval with the lowest left endpoint or null if the tree is empty.
	 */
	public Interval<T> minimum() {
		if (root.left == nil)
			return null;
		return treeMinimum(root.left).i;
	}

	private Node treeMinimum(Node x) {
		while (x.left != nil)
			x = x.left;
		return x;
	}

	/**
	 * Returns the interval with the highest left endpoint.
	 * 
	 * @return the interval with the highest left endpoint or null if the tree is empty.
	 */
	public Interval<T> maximum() {
		if (root.left == nil)
			return null;
		return treeMaximum(root.left).i;
	}

	private Node treeMaximum(Node x) {
		while (x.right != nil)
			x = x.right;
		return x;
	}

	/**
	 * Returns the leftmost point or {@code Double.NEGATIVE_INFINITY} in case of no intervals.
	 * 
	 * @return the leftmost point.
	 */
	public double getLow() {
		if (isEmpty())
			return Double.NEGATIVE_INFINITY;
		return minimum().getLow();
	}

	/**
	 * Returns the rightmost point or {@code Double.POSITIVE_INFINITY} in case of no intervals.
	 * 
	 * @return the rightmost point.
	 */
	public double getHigh() {
		if (isEmpty())
			return Double.POSITIVE_INFINITY;
		return root.left.max;
	}

	/**
	 * Indicates if the leftmost point is excluded.
	 * 
	 * @return {@code true} if the leftmost point is excluded, {@code false} otherwise.
	 */
	public boolean isLowExcluded() {
		if (isEmpty())
			return true;
		return minimum().isLowExcluded();
	}

	/**
	 * Indicates if the rightmost point is excluded.
	 * 
	 * @return {@code true} if the rightmost point is excluded, {@code false} otherwise.
	 */
	public boolean isHighExcluded() {
		if (isEmpty())
			return true;
		return maximum().isHighExcluded();
	}

	/**
	 * Indicates if this {@code IntervalTree} contains 0 intervals.
	 * 
	 * @return {@code true} if this {@code IntervalTree} is empty, {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return root.left == nil;
	}

	/**
	 * Returns all intervals.
	 * 
	 * @return all intervals
	 */
	public List<Interval<T>> getIntervals() {
		List<Interval<T>> list = new ArrayList<Interval<T>>();
		inorderTreeWalk(root.left, list);
		return list;
	}

	/**
	 * Returns all intervals overlapping with a given {@code Interval}.
	 * 
	 * @param interval
	 *            an {#code Interval} to be searched for overlaps
	 * @return all intervals overlapping with a given {@code Interval}.
	 * @throws NullPointerException
	 *             if {@code interval} is null.
	 */
	public List<Interval<T>> search(Interval interval) {
		if (interval == null)
			throw new NullPointerException("Interval cannot be null.");

		List<Interval<T>> overlaps = new ArrayList<Interval<T>>();
		for (Node n : searchNodes(interval))
			overlaps.add(n.i);
		return overlaps;
	}

	/**
	 * Returns all intervals overlapping with an interval given by {@code low} and {@code high}. They are considered as
	 * included by default.
	 * 
	 * @param low
	 *            the left endpoint of an interval to be searched for overlaps
	 * @param high
	 *            the right endpoint an interval to be searched for overlaps
	 * @return all intervals overlapping with an interval given by {@code low} and {@code high}.
	 * @throws IllegalArgumentException
	 *             if {@code low} > {@code high}.
	 */
	public List<Interval<T>> search(double low, double high) {
		if (low > high)
			throw new IllegalArgumentException("The left endpoint of the interval must be less than " + "the right endpoint.");

		List<Interval<T>> overlaps = new ArrayList<Interval<T>>();
		for (Node n : searchNodes(new Interval(low, high)))
			overlaps.add(n.i);
		return overlaps;
	}

	private List<Node> searchNodes(Interval interval) {
		List<Node> result = new ArrayList<Node>();
		searchNodes(root.left, interval, result);
		return result;
	}

	private void searchNodes(Node n, Interval interval, List<Node> result) {
		// Don't search nodes that don't exist.
		if (n == nil)
			return;

		// Skip all nodes that have got their max value below the start of
		// the given interval.
		if (interval.getLow() > n.max)
			return;

		// Search left children.
		if (n.left != nil)
			searchNodes(n.left, interval, result);

		// Check this node.
		if (n.i.compareTo(interval) == 0)
			result.add(n);

		// Skip all nodes to the right of nodes whose low value is past the end
		// of the given interval.
		if (interval.compareTo(n.i) < 0)
			return;

		// Otherwise, search right children.
		if (n.right != nil)
			searchNodes(n.right, interval, result);
	}

	/**
	 * Indicates if this {@code IntervalTree} overlaps with the given time interval.
	 * 
	 * @param interval
	 *            a given time interval
	 * @return {@code true} if this {@code IntervalTree} overlaps with {@code interval}, {@code false} otherwise.
	 */
	public boolean overlapsWith(Interval interval) {
		return overlapsWith(root.left, interval);
	}

	private boolean overlapsWith(Node n, Interval interval) {
		// Don't search nodes that don't exist.
		if (n == nil)
			return false;

		// Skip all nodes that have got their max value below the start of
		// the given interval.
		if (interval.getLow() > n.max)
			return false;

		// Search left children.
		if (n.left != nil)
			if (overlapsWith(n.left, interval))
				return true;

		// Check this node.
		if (n.i.compareTo(interval) == 0)
			return true;

		// Skip all nodes to the right of nodes whose low value is past the end
		// of the given interval.
		if (interval.compareTo(n.i) < 0)
			return false;

		// Otherwise, search right children.
		if (n.right != nil)
			if (overlapsWith(n.right, interval))
				return true;

		// No overlaps, return false.
		return false;
	}

	private void inorderTreeWalk(Node x, List<Interval<T>> list) {
		if (x != nil) {
			inorderTreeWalk(x.left, list);
			list.add(x.i);
			inorderTreeWalk(x.right, list);
		}
	}

	/**
	 * Compares this interval tree with the specified object for equality.
	 * <p>
	 * Note that two interval trees are equal if they contain the same intervals.
	 * 
	 * @param obj
	 *            object to which this interval tree is to be compared
	 * @return {@code true} if and only if the specified {@code Object} is a {@code IntervalTree} which contain the same
	 *         intervals as this {@code IntervalTree's}.
	 * @see #hashCode
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(this.getClass())) {
			List<Interval<T>> thisIntervals = new ArrayList<Interval<T>>();
			List<Interval<T>> objIntervals = new ArrayList<Interval<T>>();
			inorderTreeWalk(root.left, thisIntervals);
			((IntervalTree<T>) obj).inorderTreeWalk(((IntervalTree<T>) obj).root.left, objIntervals);
			if (thisIntervals.size() == objIntervals.size()) {
				for (int i = 0; i < thisIntervals.size(); ++i)
					if (!thisIntervals.get(i).equals(objIntervals.get(i)))
						return false;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a hashcode of this interval tree.
	 * 
	 * @return a hashcode of this interval tree.
	 */
	@Override
	public int hashCode() {
		List<Interval<T>> list = new ArrayList<Interval<T>>();
		inorderTreeWalk(root.left, list);
		return Arrays.deepHashCode(list.toArray());
	}

	/**
	 * Creates a string representation of all the intervals with their values.
	 * 
	 * @param timesAsDoubles
	 *            indicates if times should be shown as doubles or dates
	 * @return a string representation with times as doubles or dates.
	 */
	public String toString(boolean timesAsDoubles) {
		List<Interval<T>> list = new ArrayList<Interval<T>>();
		inorderTreeWalk(root.left, list);
		if (!list.isEmpty()) {
			StringBuilder sb = new StringBuilder("<");
			sb.append(list.get(0).toString(timesAsDoubles));
			for (int i = 1; i < list.size(); ++i)
				sb.append("; ").append(list.get(i).toString(timesAsDoubles));
			sb.append(">");
			return sb.toString();
		}
		return "<empty>";
	}

	/**
	 * Returns a string representation of this interval tree in a format {@code <[low, high, value], ..., [low, high, value]>}. Nodes are visited in {@code inorder}.
	 * <p>
	 * Times are always shown as doubles.
	 * </p>
	 * 
	 * @return a string representation of this interval tree.
	 */
	@Override
	public String toString() {
		return toString(true);
	}

	private class Node {

		public Interval<T> i; // i.low is the key of this node
		public double max; // the maximum value of any interval endpoint
							// stored in the subtree rooted at this node

		public Color color; // the color of this node
		public Node left; // the left subtree of this node
		public Node right; // the right subtree of this node
		public Node p; // the parent node

		/*
		 * Constructs a sentinel node by default.
		 */
		public Node() {
			color = BLACK;
		}

		/*
		 * Constructs a new {@code Node} instance.
		 */
		public Node(Interval<T> i) {
			this();
			this.i = i;
			this.max = i.getHigh();
		}
	}

	private enum Color {
		RED, BLACK
	}

	private static final Color RED = Color.RED;
	private static final Color BLACK = Color.BLACK;
}