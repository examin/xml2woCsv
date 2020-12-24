package micro.examin.xml2woCsv.LeetcodeDP;

public class FuelStations {
    public int minRefuelStops(int target, int startfuel, int[][] stations) {
        if(target<=startfuel) return 0;
        else if(stations.length==0){
            return -1;
        }
        int max = startfuel;
        int last = -1;
        int sum = 0;
        for(int i = 0;i<stations.length;i++){
            if(max<stations[i][0]&&last!=-1){
                max+=stations[last][1];
                sum+=1;
            }
            last = i;
        }
        if ((max>=target)) return sum;
        else if ( last>=0&&max>=stations[last][0]&&max+stations[last][1]>=target) return sum+1;
        else return -1;
    }

    public static void main(String[] args) {
        FuelStations obj = new FuelStations();
        System.out.println(obj.minRefuelStops(100,50, new int[][]{{25,50},{50,25}}) );
    }
}
