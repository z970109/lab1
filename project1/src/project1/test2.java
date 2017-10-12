package project1;
import java.util.ArrayList;  
public class test2 {
	 //有向带权图。-1表示无路可通。自己到自己也是-1。其它表示权值。

	static int[][] graph;
    static int length=graph.length;
    private static boolean[] hasFlag=new boolean[graph.length];
    //true-表示该结点已访问过。false-表示还没有访问过。
     
     static ArrayList<String> res=new ArrayList<String>();
    //最后的所有的路径的结果。每一条路径的格式是如：0->2->1->3:7
     
    //求在图graph上源点s到目标点d之间所有的简单路径，并求出路径的和。   
    public static void getPaths(int s,int d,String path,int sum)
    {
        hasFlag[s]=true;//源点已访问过. 
     for(int i=0;i<length;i++)
     {

        if (graph[s][i]==-1 || hasFlag[i]){continue;}
        //若无路可通或已访问过，则找下一个结点。
 
        if(i==d)//若已找到一条路径
        { 
            res.add(path+"->"+d+":"+(sum+graph[s][i]));//加入结果。
            continue;
        }
        getPaths(i, d, path+"->"+i, sum+graph[s][i]);//继续找
        hasFlag[i]=false;       
     }//for(i)
    }
      
    public static void main(String[] args) {

    	for (int i=0;i<length;i++)
		{
			for (int j=0;j<length;j++)
			{
				System.out.print(graph[i][j]);System.out.print(' ');
			}
			System.out.print("\n");
		}
      getPaths(0, 3, ""+0, 0);//从源点：0 到目点：3,初始路径："0" 初始和：0
      System.out.println(length);
      for(String e:res)//打印所有的结果
      {
          System.out.println(e);
      }
    }
}
