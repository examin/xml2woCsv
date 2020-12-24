package micro.examin.xml2woCsv.Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}};
        CourseSchedule obj = new CourseSchedule();
        System.out.println(obj.canFinish(numCourses, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        HashMap<Integer, Integer> degree = new HashMap<Integer, Integer>();
        for (int i = 0; i < numCourses; i++) {
            degree.put(i, 0);
        }
        for (int curr[] : prerequisites) {
            degree.put(curr[0], degree.getOrDefault(curr[0], 0) + 1);
        }
        Stack<Integer> stack = new Stack<Integer>();

        for (Map.Entry<Integer, Integer> entry : degree.entrySet()) {
            if (entry.getValue() == 0) {
                stack.push(entry.getKey());
            }
        }
        int[] tor = new int[numCourses];
        int count = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            tor[curr] = curr;
            count++;
            for (int currReq[] : prerequisites) {
                if (currReq[1] == curr) {
                    int currDegree = degree.get(currReq[0]);
                    if (currDegree > 1) {
                        degree.put(currReq[0], currDegree - 1);
                    } else {
                        degree.remove(currReq[0]);
                        stack.push(currReq[0]);
                    }
                }
            }
        }
        if (count == numCourses) {
            return true;
        } else return false;

    }

}
