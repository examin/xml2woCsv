package micro.examin.xml2woCsv.LeetcodeDP;

public class petrolStation {
    public static void main(String[] args) {
      int target = 1000, startFuel = 299;
        int[][] stations =  {{13,21},{26,115},{100,47},{225,99},{299,141},{444,198},{608,190},{636,157},{647,255},{841,123}};

        System.out.println(getMinPetrol(target, startFuel, stations));
    }

    private static int getMinPetrol(int target, int startFuel, int[][] stations) {
        int res = 0;
        int maxReach = startFuel;
        int sIndex = 0;
        int currMaxReach = startFuel;
        for (int i = 0; i < target; i++) {
            if (sIndex < stations.length && i == stations[sIndex][0]) {
                maxReach = Math.max(maxReach, currMaxReach + stations[sIndex][1]);
                ++sIndex;
            }
            if (i == currMaxReach) {
                if(currMaxReach==maxReach){
                    return -1;
                }else currMaxReach = maxReach;
                ++res;
            }

        }
        return res;
    }
}
