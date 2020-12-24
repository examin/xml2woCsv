package micro.examin.xml2woCsv.LeetcodeDP;

public class KPartSumEqual {
    public static void main(String[] args) {
        int[] nums = {605, 454, 322, 218, 8, 19, 651, 2220, 175, 710, 2666, 350, 252, 2264, 327, 1843};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int req = 0;
        if (sum != 0) {
            if (sum % k != 0) {
                return false;
            }
            req = sum / k;
        }
        boolean[] visited = new boolean[nums.length];
        long start = System.currentTimeMillis();
        System.out.println(dfs(nums, 0, nums.length - 1, visited, sum / k, k));
        long end = System.currentTimeMillis();
        System.out.println(canPartitionKSubsets(nums, visited, 0, req, k));
        long end2 = System.currentTimeMillis();
        return true;

    }

    private static boolean canPartitionKSubsets(int[] nums, boolean[] visited, int currSum, int req, int k) {
        if (currSum == req) {
            return canPartitionKSubsets(nums, visited, 0, req, k - 1);
        }
        if (currSum > req) {
            return false;
        }
        if (k == 0) {
            return true;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canPartitionKSubsets(nums, visited, currSum + nums[i], req, k)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static boolean dfs(int[] A, int sum, int st, boolean[] visited, int target, int round) {
        if (round == 0) return true;
        if (sum == target && dfs(A, 0, A.length - 1, visited, target, round - 1))
            return true;
        for (int i = st; i >= 0; --i) {
            if (!visited[i] && sum + A[i] <= target) {
                visited[i] = true;
                if (dfs(A, sum + A[i], i - 1, visited, target, round))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
}