package micro.examin.xml2woCsv.DP_BABU.DP_04;
//todo later
public class MultiStageGraph {
    static int N = 8;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        int[][] graph = new int[][]{{INF, 1, 2, 5, INF, INF, INF, INF},
                {INF, INF, INF, INF, 4, 11, INF, INF},
                {INF, INF, INF, INF, 9, 5, 16, INF},
                {INF, INF, INF, INF, INF, INF, 2, INF},
                {INF, INF, INF, INF, INF, INF, INF, 18},
                {INF, INF, INF, INF, INF, INF, INF, 13},
                {INF, INF, INF, INF, INF, INF, INF, 2}};

//        System.out.println(shortestDist(graph));
    }

//    private static int shortestDist(int[][] graph) {
//    }
}
