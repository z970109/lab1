package project1;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class project1 extends JFrame {
	ArrayList<String> words = new ArrayList<String>(); //= new String[100];// the graph elements
	static int [][] graph=new int [50][50]; //= new int[100][100];//初始化就全是0
	int[] gn=new int[100];//对应的原来的字符串未删去重复的字符串的对应位置
	ArrayList<String> word = new ArrayList<String>();
	int [][]graphs;
    //static int leng;
    int templen,n,num;
    String str;
	JFrame fr;
	JLabel lblName0 = new JLabel("input the sentences");// function0
	TextField txtName0 = new TextField(20);
	String fileloc = new String();

	StringBuffer strb=new StringBuffer("");//等待处理的文件里面的全部字符串
    String[] finastr;
    ImageIcon b1 = new ImageIcon("D:/java-neon/workplace/project1/show_the.jpg");
	JButton butn1 = new JButton(b1);// function1
	ImageIcon b2 = new ImageIcon("D:/java-neon/workplace/project1/save.jpg");
	JButton butn2 = new JButton(b2);


    
	JLabel lblName1 = new JLabel("input the bridge words");// function2
	TextField txtName1 = new TextField(20);

	ImageIcon b3 = new ImageIcon("D:/java-neon/workplace/project1/bridge.jpg");
	JButton butn3 = new JButton(b3);
	String bristrout = new String();//输出的字符存储地点
    TextArea ta=new TextArea();//输出的地方
    
	JLabel lblName2 = new JLabel("input the sentences");// function3

	TextField txtName2 = new TextField(20);
	ImageIcon b4 = new ImageIcon("D:/java-neon/workplace/project1/new_sentence.jpg");
	JButton butn4 = new JButton(b4);
	String senstrout = new String();
	TextArea ta1=new TextArea();//输出的地方

	JLabel lblName4 = new JLabel("input the two words");// function4
	TextField txtName4 = new TextField(20);
	ImageIcon b5 = new ImageIcon("D:/java-neon/workplace/project1/shortest.jpg");
	JButton butn5 = new JButton(b5);
	ImageIcon b6 = new ImageIcon("D:/java-neon/workplace/project1/random.jpg");
	JButton butn6 = new JButton(b6);// function5

	public project1() {

		fr = new JFrame();	
		Container a=getContentPane();
		a.setLayout(new FlowLayout());
		
		fr.setBounds(0, 0, 500, 800);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.white);

		fr.add(lblName0);
		fr.add(txtName0);
		fr.add(butn1);
		fr.add(butn2);
		fr.add(lblName1);
		fr.add(txtName1);
		fr.add(butn3);
		fr.add(ta);
		fr.add(lblName2);
		fr.add(txtName2);
		fr.add(butn4);
		fr.add(ta1);
		fr.add(lblName4);
		fr.add(txtName4);
		fr.add(butn5);
		fr.add(butn6);

		lblName0.setBounds(100,10, 200, 40);
		txtName0.setBounds(100,50, 200, 40);
		butn1.setBounds(100,90, 200, 40);
		butn2.setBounds(100,130, 200, 40);
		lblName1.setBounds(100,180, 200, 40);
		txtName1.setBounds(100,220, 200, 40);
		butn3.setBounds(100,260, 200, 40);
		ta.setBounds(300,260, 200, 40);
		lblName2.setBounds(100,300, 200, 40);
		txtName2.setBounds(100,340, 200, 40);
		butn4.setBounds(100,380,  200, 40);
		ta1.setBounds(300,380, 200, 40);
		lblName4.setBounds(100,420, 200, 40);
		txtName4.setBounds(100,460, 200, 40);
		butn5.setBounds(100,500,  200, 40);
		butn6.setBounds(100,540, 200, 40);
		butn1.setBorderPainted(false); 
		butn2.setBorderPainted(false);
		butn3.setBorderPainted(false);
		butn4.setBorderPainted(false);
		butn5.setBorderPainted(false);
		butn6.setBorderPainted(false);
		butn1.setBackground(Color.white);
		butn2.setBackground(Color.white);
		butn3.setBackground(Color.white);
		butn4.setBackground(Color.white);
		butn5.setBackground(Color.white);
		butn6.setBackground(Color.white);
		lblName0.setFont(new java.awt.Font("Dialog", 1, 15));//设置字体大小
		lblName0.setForeground(Color.BLUE);//设置字体颜色
		lblName1.setFont(new java.awt.Font("Dialog", 1, 15));
		lblName1.setForeground(Color.BLUE);
		lblName2.setFont(new java.awt.Font("Dialog", 1, 15));
		lblName2.setForeground(Color.BLUE);
		lblName4.setFont(new java.awt.Font("Dialog", 1, 15));
		lblName4.setForeground(Color.BLUE);
		
		fr.setTitle("project1");
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		butn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str = txtName0.getText();
				initData();
				run(str);
				clear();
				transtograph();
				showgraph();
			}
	    });
		//显示图片的按钮
		butn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
	    });
		
		//显示桥单词的按钮
		butn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String line = txtName1.getText();
				System.out.println(line);
		        String[] array = line.split(" ");
		        //to get two input words
		        while (array.length != 2) {
		            System.out.print("Invalid input,Please input two words:");
		            line = txtName1.getText();
		            array = line.split(" ");
		        }
		        System.out.println(array[0]+" "+array[1]);
		        String result = queryBridgeWords(array[0], array[1]);
		        ta.setText(result);
		        System.out.println(result);
				//此处还需要将要输出的字符改成bridge字符
			}
		});
		
		//显示新句子的按钮动作
		butn4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String str4 = txtName2.getText();
				String[] array = str4.split(" ");
				String [] finall=array;
				System.out.println("the first is "+finall[0]);
				int first=0,end=0,flag=0,start=0;
				for (int i=0;i<templen;i++)
				{
					System.out.print(" "+finastr[i]);
					if (finall[0]==finastr[i])
						{start=i;
					     break;}
				}
				System.out.println("the first words is"+finastr[start]);
				for (int i=0;i<templen;i++)
				{
					if (finall[i]!=finastr[i+start]&&flag==0)
						{first=i+start;flag++;}
					else if (finall[first]==finastr[i+start]&&flag==1)
					{end=i+start;flag++;}
					else break;
				}
				ArrayList<String> wordn = new ArrayList<String>();
				for (int i=start;i<=first;i++)
					wordn.add(finall[i]);
				wordn.add(finastr[first+2]);
				for (int i=first+1;i<finall.length;i++)
					wordn.add(finall[i]);
				String temp=wordn.toString();
				String [] finarray = temp.split("[^a-zA-Z]+");
				StringBuffer f=new StringBuffer();
				for (int i=0;i<finarray.length;i++)
				    {System.out.println(finarray[i]);f.append(finarray[i]+" ");}
				String ff=f.toString();
				ta1.setText(ff);
			}
		});
		//显示最短路径的按钮动作    ------[color="red"];
		butn5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//此处还需要将要输出的字符改成bridge字符
				String line = txtName4.getText();
		        String[] array = line.split(" ");
		        //to get two input words
		        while (array.length == 2)
				{String result = calcShortestPath(array[0], array[1]);
				add_vn();
				write("C:/Users/Administrator/Desktop/2.txt",result);
				write("C:/Users/Administrator/Desktop/2.txt"," [color=red]");
				write("C:/Users/Administrator/Desktop/2.txt","}");
				showgraphn();}
				
			}
		});
		
		//显示随机游走的按钮动作     ------[color="red"];
		butn6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//此处还需要将要输出的字符改成bridge字符
				String walkPath = randomWalk();
                write("C:/Users/Administrator/Desktop/randompath.txt","Randomly Walk Path :");
                write("C:/Users/Administrator/Desktop/randompath.txt",walkPath);
                write("C:/Users/Administrator/Desktop/randompath.txt", "\r\n");
			}
		});
		
    }
	
	
	
	
	
	public void showgraph()
	{
	    File file=new File("C:/Users/Administrator/Desktop/1.txt"); //指定文件名及路径
	    String filename=file.getAbsolutePath();
	    if(filename.indexOf(".")>=0)
	    {
	      filename = filename.substring(0, filename.lastIndexOf("."));
	    }
	    file.renameTo(new File(filename+".dot")); //改名
	    try{
	    	Runtime runtime = Runtime.getRuntime();
	    	runtime.exec("cmd /c start D:/java-neon/workplace/project1/1.vbs");
          }
        catch(Exception e)
        {
          System.out.println(e);       
        }
	    
	}
	public void showgraphn()
	{
	    File file=new File("C:/Users/Administrator/Desktop/2.txt"); //指定文件名及路径
	    String filename=file.getAbsolutePath();
	    if(filename.indexOf(".")>=0)
	    {
	      filename = filename.substring(0, filename.lastIndexOf("."));
	    }
	    file.renameTo(new File(filename+".dot")); //改名
	    try{
	    	Runtime runtime = Runtime.getRuntime();
	    	runtime.exec("cmd /c start D:/java-neon/workplace/project1/2.vbs");
          }
        catch(Exception e)
        {
          System.out.println(e);       
        }
	    
	}
	
	public void transtograph()
	{
		words.add(finastr[0]);		
		gn[0]=0;
        n=1;
		num=0;
		for (int i=1;i<templen;i++)
		{
				if (words.indexOf(finastr[i])==-1)
				{
					gn[i]=n;
					words.add(finastr[i]);
					graph[gn[i-1]][n]=1;//产生了二维边数组
					num++;
					n++;
				}
				else 
				{
					//此处还要画一条边
					gn[i]=words.indexOf(finastr[i]);
					graph[gn[i-1]][gn[i]]=1;//产生了二维边数组
					num++;
				}

		}
		System.out.println("边的数量是"+num);
		//leng=gv.size()-1;
		//System.out.println(leng);
		for (int i=0;i<templen;i++)
		    {System.out.print(finastr[i]+" ");System.out.print(gn[i]+" ");}
		
		System.out.print("\n");
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				//System.out.print(graphs[i][j]);System.out.print(' ');
				if (graph[i][j]!=1)
					graph[i][j]=-1;
			}
			//System.out.print("\n");
		}
		add_v();
		write("C:/Users/Administrator/Desktop/1.txt","}");
	}
	
	
	public void add_v()//在文件里写画图代码
	{
		write("C:/Users/Administrator/Desktop/1.txt","digraph  a {"+"\r\n");
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				if (graph[i][j]==1)
				{
                    int index_f=0;
                    int index_e=0;
					for (int m=0;m<templen;m++)
					{
						if (gn[m]==i)
							{index_f=m;break;}						
					}
					for (int m=0;m<templen;m++)
					{
						if (gn[m]==j)
						{index_e=m;break;}						
					}

					System.out.print(gn[index_f]);System.out.print(gn[index_e]);
                    System.out.print(index_f);System.out.print(index_e);
					write("C:/Users/Administrator/Desktop/1.txt",finastr[index_f]);
					write("C:/Users/Administrator/Desktop/1.txt"," -> ");
					write("C:/Users/Administrator/Desktop/1.txt",finastr[index_e]);
					write("C:/Users/Administrator/Desktop/1.txt"," [label=1]");
					write("C:/Users/Administrator/Desktop/1.txt",";"+"\r\n");
				}	
			}
		}
	}
	
	public void add_vn()//在文件里写画图代码
	{
		write("C:/Users/Administrator/Desktop/2.txt","digraph  a {"+"\r\n");
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				if (graph[i][j]==1)
				{
                    int index_f=0;
                    int index_e=0;
					for (int m=0;m<templen;m++)
					{
						if (gn[m]==i)
							{index_f=m;break;}						
					}
					for (int m=0;m<templen;m++)
					{
						if (gn[m]==j)
						{index_e=m;break;}						
					}

					System.out.print(gn[index_f]);System.out.print(gn[index_e]);
                    System.out.print(index_f);System.out.print(index_e);
					write("C:/Users/Administrator/Desktop/2.txt",finastr[index_f]);
					write("C:/Users/Administrator/Desktop/2.txt"," -> ");
					write("C:/Users/Administrator/Desktop/2.txt",finastr[index_e]);
					write("C:/Users/Administrator/Desktop/2.txt"," [label=1]");
					write("C:/Users/Administrator/Desktop/2.txt",";"+"\r\n");
				}	
			}
		}
	}
	public static void write(String file, String conent) {
		BufferedWriter out = null;
		try {
		out = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file, true)));
		out.write(conent);
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
		out.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		}
	
	public void clear()
	{
		String temp=strb.toString();
		finastr = temp.split("[^a-zA-Z]+");
		templen=finastr.length;
	}
	public String [] clearn(String [] a )
	{
		String temp=a.toString();
		String []finastrn = temp.split("[^a-zA-Z]+");
		return  finastrn;
	}
	
	
	public void run(String str) {
		int len = 0;
		File file = new File(str);
		System.out.println(str);//C:\Users\Administrator\Desktop\graph.txt
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null)
			{
				if (len != 0) // 处理换行符的问题
				{
					strb.append("\r\n" + line);
				}
				else
				{
					strb.append(line);
				}
				len++;
			}
			in.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Constructor to load data from filename
     *
     * @param filename the filename
     */
	void initData() {
        FileInputStream fileInputStream = null;
        Scanner in = null;
        List<String> allWords = new ArrayList<String>();
        Set<String> setWords = new HashSet<String>();
        // read from file
        try {
            fileInputStream = new FileInputStream(str);
            in = new Scanner(fileInputStream);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                StringBuilder temp = new StringBuilder();
                // filt the word
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ' ' == ch) {
                        temp.append(ch);
                    }
                }
                String[] array = temp.toString().split(" ");
                for (String str : array) {
                    if (!"".equals(str)) {
                        String tempStr = str.toLowerCase();
                        allWords.add(tempStr);
                        setWords.add(tempStr);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: ");
            System.exit(0);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.out.println("Close inputstream error");
                }
            }
            if (in != null) {
                in.close();
            }
        }
        // filter the duplicate words,and build the graph
        word = new ArrayList<String>(setWords);
        graphs = new int[word.size()][word.size()];
        for (int i = 0; i < allWords.size() - 1; i++) {
            String currentWord = allWords.get(i);
            String nextWord = allWords.get(i + 1);
            int row = word.indexOf(currentWord);
            int column = word.indexOf(nextWord);
            graphs[row][column]++;
        }
    }
    /**
     * Query bridge words
     *
     * @param word1 the first word
     * @param word2 the second word
     * @return the bridge word
     */
    public String queryBridgeWords(String word1, String word2) {
        String result = "";
        
        for (int i=0;i<templen;i++)
        	word.add(finastr[i]);
        for (int i=0;i<templen;i++)
        	System.out.print(word.get(i)+" ");
        boolean containsWord1 = word.contains(word1);
        boolean containsWord2 = word.contains(word2);
        if (containsWord1 && containsWord2) {
            int firstIndex = word.indexOf(word1);
            int secondIndex = word.indexOf(word2);
            boolean exists = false;
            int midIndex = 0;
            for (int i = 0; !exists && i < graphs[firstIndex].length; i++) {
                if (graphs[firstIndex][i] != 0) {
                    for (int j = 0; !exists && j < graphs[i].length; j++) {
                        if (graphs[i][j] != 0 && j == secondIndex) {
                            exists = true;
                            midIndex = i;
                        }
                    }
                }
            }
            if (exists) {
                result = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is: " + word.get(midIndex);
            } else {
                result = "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
            }
        } else if (!containsWord1 && containsWord2) {
            result = "NO \"" + word1 + "\" in the graph!";
        } else if (containsWord1 && !containsWord2) {
            result = "NO \"" + word2 + "\" in the graph!";
        } else {
            result = "NO \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
        }
        return result;
    }

    /**
     * find the shortest path
     *
     * @param word1 the first word
     * @param word2 the second word
     * @return the shortest path
     */
    public String calcShortestPath(String word1, String word2) {
        String result = "";
        boolean containsWord1 = word.contains(word1);
        boolean containsWord2 = word.contains(word2);
        if (containsWord1 && containsWord2) {
            int startIndex = word.indexOf(word1);
            int endIndex = word.indexOf(word2);
            //find all path
            Stack<Integer> visitedIndex = new Stack<Integer>();
            List<List<Integer>> allpaths = new ArrayList<List<Integer>>();
            visitedIndex.push(startIndex);
            depthFirstSearch(allpaths, visitedIndex, startIndex, endIndex);

            // find the shortest path
            if (allpaths.size() > 0) {
                int shortestIndex = 0;
                int shortestLength = Integer.MAX_VALUE;
                for (int i = 0; i < allpaths.size(); i++) {
                    List<Integer> tempPath = allpaths.get(i);
                    if (shortestLength > tempPath.size()) {
                        shortestLength = tempPath.size();
                        shortestIndex = i;
                    }
                }
                // convert to a string path
                List<Integer> shortestPath = allpaths.get(shortestIndex);
                for (int i = 0; i < shortestPath.size(); i++) {
                    result += word.get(shortestPath.get(i));
                    if (i != shortestPath.size() - 1) {
                        result += " -> ";
                    }
                }
            } else {
                result = "There are no path from \" " + word1 + "\" to \"" + word2 + "\"!";
            }

        } else if (!containsWord1 && containsWord2) {
            result = "NO \"" + word1 + "\" in the graph!";
        } else if (containsWord1 && !containsWord2) {
            result = "NO \"" + word2 + "\" in the graph!";
        } else {
            result = "NO \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
        }

        return result;
    }

    /**
     * Find all path with Depth First Search
     *
     * @param allpaths     the list of all paths
     * @param visitedIndex the visited index list
     * @param startIndex   start index
     * @param endIndex     end index
     */
    private void depthFirstSearch(List<List<Integer>> allpaths, Stack<Integer> visitedIndex, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            List<Integer> path = new ArrayList<>(visitedIndex);
            allpaths.add(path);
            visitedIndex.pop();
            return;
        }
        for (int i = 0; i < graphs[startIndex].length; i++) {
            if (graphs[startIndex][i] != 0 && !visitedIndex.contains(i)) {
                visitedIndex.push(i);
                depthFirstSearch(allpaths, visitedIndex, i, endIndex);
            }
        }
        visitedIndex.pop();
    }

    /**
     * @return the random walk path
     */
    public String randomWalk() {
        // to generate a random number
        String result = "";
        Random random = new Random();
        int startIndex = random.nextInt(word.size());
        List<List<Integer>> allpaths = new ArrayList<List<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        //path.add(startIndex);
        dfsRandomWalk(allpaths, path, startIndex);
        if (allpaths.size() > 0) {
            int randomPathIndex = random.nextInt(allpaths.size());
            List<Integer> shortestPath = allpaths.get(randomPathIndex);

            for (int i = 0; i < shortestPath.size(); i++) {
                result += word.get(shortestPath.get(i));
                result += " ";
            }
        }
        return result;
    }

    /**
     * Find all path
     *
     * @param allpaths     all path
     * @param visitedIndex the visited index list
     * @param startIndex   the index
     */
    private void dfsRandomWalk(List<List<Integer>> allpaths, Stack<Integer> visitedIndex, int startIndex) {
        // duplicate edge
        if (visitedIndex.contains(startIndex)) {
            int index = visitedIndex.indexOf(startIndex);
            if (index - 1 >= 0) {
                int preValue = visitedIndex.get(index - 1);
                if (preValue == visitedIndex.lastElement()) {
                    visitedIndex.push(startIndex);
                    List<Integer> path = new ArrayList<Integer>(visitedIndex);
                    allpaths.add(path);
                    visitedIndex.pop();
                    return;
                }
            }
        }

        // no edge
        boolean hasEdge = false;
        for (int i = 0; !hasEdge && i < graphs[startIndex].length; i++) {
            if (graphs[startIndex][i] != 0) {
                hasEdge = true;
            }
        }

        if (!hasEdge) {
            visitedIndex.push(startIndex);
            List<Integer> path = new ArrayList<Integer>(visitedIndex);
            allpaths.add(path);
            visitedIndex.pop();
            return;
        }

        //push
        visitedIndex.push(startIndex);

        for (int i = 0; i < graphs[startIndex].length; i++) {
            if (graphs[startIndex][i] != 0) {
                dfsRandomWalk(allpaths, visitedIndex, i);
            }
        }
        //pop
        visitedIndex.pop();
    }

    
	public static void main(String[] args) {
		
		project1 program =new project1();
		
	    //program.init();//执行初始化
	}
	

}
