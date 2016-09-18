import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input1"))));
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(r.readLine());
		for (int __ = 0; __ < n; __++) {
			String[] split = r.readLine().split(" ");
			int v = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);

			List<Integer>[] vert = new LinkedList[v];
			for (int i = 0; i < v; i++) {
				vert[i] = new LinkedList<>();
			}

			for (int i = 0; i < e; i++) {
				split = r.readLine().split(" ");
				int a = split[0].charAt(0);
				int b = split[1].charAt(0);

				vert[a - 'a'].add(b - 'a');
				vert[b - 'a'].add(a - 'a');
			}

			boolean[] m = new boolean[v]; // marked
			List<List<Integer>> all_components = new ArrayList<>(v);

			// dfs
			for (int i = 0; i < v; i++) {
				if (!m[i]) {
					List<Integer> components = dfs(vert, m, i, new ArrayList<Integer>());
					all_components.add(components);
				}
			}

//			Collections.sort(all_components, new Comparator<List<Integer>>() {
//
//				@Override
//				public int compare(List<Integer> o1, List<Integer> o2) {
//					Collections.sort(o1);
//					Collections.sort(o2);
//
//					Integer i1 = o1.get(0);
//					Integer i2 = o2.get(0);
//					return i1 > i2 ? 1 : (i1 < i2 ? -1 : 0);
//				}
//
//			});

			System.out.println("Case #" + (__ + 1) + ":");
			for (List<Integer> list : all_components) {
				Collections.sort(list);
				
				for (Integer integer : list) {
					System.out.print(((char)(integer + 'a')) + ",");
				}
				System.out.println();
			}
			System.out.println(all_components.size() + " connected components");
			System.out.println();
		}
	}

	private static List<Integer> dfs(List<Integer>[] vert, boolean[] m, int i, List<Integer> list) {
		if (m[i])
			return list;
		m[i] = true;

		list.add(i);
		for (Integer adj : vert[i]) {
			dfs(vert, m, adj, list);
		}
		return list;
	}

}
