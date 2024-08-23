import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<List<Integer>> networks = new ArrayList<>();
    private static boolean[] v;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            networks.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            networks.get(b).add(a);
        }

        result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v = new boolean[n + 1];
            bfs(i);
        }

        int max = Integer.MIN_VALUE;
        for (int value : result) {
            max = Math.max(max, value);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (result[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        v[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            List<Integer> network = networks.get(now);
            int size = network.size();
            for (int i = 0; i < size; i++) {
                int next = network.get(i);

                if (v[next]) continue;
                q.offer(next);
                v[next] = true;
                result[start]++;
            }
        }
    }
}