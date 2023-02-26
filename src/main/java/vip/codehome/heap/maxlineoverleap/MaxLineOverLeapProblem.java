package vip.codehome.heap.maxlineoverleap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author dsys
 * @date 2023-02-15
 * 1、给定[1,3] [2,4] [4,7] [2,9]等线段求最大重合次数
 *
 */
public class MaxLineOverLeapProblem {
    /**
     * 最后重叠的线段的左节点一定是给定线段中其中一个线段的左节点
     * 因此求所有左节点，落在最多的线段中，就是最大重叠次数
     * O(N^2)
     * @param lines
     * @return
     */
    public static int maxLine1(int[][] lines){
        if(lines==null||lines.length==0) return 0;
        int max=1;
        for(int i=0;i<lines.length;i++){
            int left=lines[i][0];
            int t=0;
            for(int j=0;j<lines.length;j++){
                if(lines[j][0]<=left&&lines[j][1]>=left) t=t+1;
                max=Math.max(max,t);
            }
        }
        return max;
    }
    /**
     * 方案2：使用小根堆
     * 满足条件的左节点一定是某个线段的左端点，那么通过该节点的线段一定左端点小于该结果leftx，右节点大于leftx。
     * 通过小根堆的特性，留在堆的一定是跟当前节点比较右边的节点
     * 1、将所有线段按照左端点排序，减少次数
     * 2、遍历每一个线段，对于每一个线段
     * 3、把右端点放入堆中，
     * 4、左节点与堆顶比较，如果堆顶的元素小于左端点全部弹出
     * 5、每次Max找最大重合次数作为答案
     */
    public static int maxLine2(int[][] lines){
        if(lines==null||lines.length==0) return 0;
        Arrays.sort(lines,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        int max=1;
        for(int i=0;i<lines.length;i++){
            while (!heap.isEmpty()&&heap.peek()<=lines[i][0]) heap.poll();
            heap.add(lines[i][1]);
            max=Math.max(heap.size(),max);
        }
        return max;
    }
    public static void main(String args[]){
        int testArr[][]={{1,3},{2,4},{4,7},{2,9}};
        System.out.println(maxLine2(testArr));
        System.out.println(maxLine1(testArr)==maxLine2(testArr));
    }
}


