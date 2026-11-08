import java.util.*;

public class Main {
    public static void main(String[] args) {
        var st = Main.class.getResourceAsStream("branches.txt");
        if (st == null) st = Main.class.getClassLoader().getResourceAsStream("lw04/prelab/branches.txt");

        Scanner sc = new Scanner(st);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int m = sc.nextInt();
        int[] t = new int[m];
        for (int i = 0; i < m; i++) t[i] = sc.nextInt();

        for (int i = 1, j, k; i <= 3; i++) {
            k = a[i];
            for (j = i - 1; j >= 0 && a[j] < k; j--) a[j + 1] = a[j];
            a[j + 1] = k;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(i > 0 ? " " : "").append(a[i]);
        sb.append("\n");

        for (int x : t) {
            int p = -1;
            for (int i = 0; i < n && p < 0; i++) if (a[i] == x) p = i;
            sb.append(x).append(p < 0 ? ": NOT FOUND" : ": FOUND at index " + p).append("\n");
        }

        System.out.print(sb);
        sc.close();
    }
}
