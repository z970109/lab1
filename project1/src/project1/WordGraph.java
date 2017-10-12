package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * class WordGraph
 */
public class WordGraph {

    /**
     * filename
     */
    private String filename;

    /**
     * all the words
     */
    private List<String> words;

    /**
     * the graph
     */
    public static int[][] graph=new int [30][30];

    /**
     * Constructor to load data from filename
     *
     * @param filename the filename
     */
    public WordGraph(String filename) {
        this.filename = filename;
        initData();
    }

    /**
     * Query bridge words
     *
     * @param word1 the first word
     * @param word2 the second word
     * @return the bridge word
     */
    public String queryBridgeWords(String word1, String word2) {
    	for (int i=0;i<graph.length;i++)
    	{
    		for (int j=0;j<graph.length;j++)
    			System.out.print(graph[i][j]+" ");
    		System.out.print("\n");
    	}
    	for (int j=0;j<words.size();j++)	
    		System.out.print(words.get(j)+" ");
        String result = "";
        boolean containsWord1 = words.contains(word1);
        boolean containsWord2 = words.contains(word2);
        if (containsWord1 && containsWord2) {
            int firstIndex = words.indexOf(word1);
            int secondIndex = words.indexOf(word2);
            boolean exists = false;
            int midIndex = 0;
            for (int i = 0; !exists && i < graph[firstIndex].length; i++) {
                if (graph[firstIndex][i] != 0) {
                    for (int j = 0; !exists && j < graph[i].length; j++) {
                        if (graph[i][j] != 0 && j == secondIndex) {
                            exists = true;
                            midIndex = i;
                        }
                    }
                }
            }
            if (exists) {
                result = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is: " + words.get(midIndex);
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
        String result = "The path from \"" + word1 + "\" to \"" + word2 + "\" is:  ";
        boolean containsWord1 = words.contains(word1);
        boolean containsWord2 = words.contains(word2);
        if (containsWord1 && containsWord2) {
            int startIndex = words.indexOf(word1);
            int endIndex = words.indexOf(word2);
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
                    result += words.get(shortestPath.get(i));
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
        for (int i = 0; i < graph[startIndex].length; i++) {
            if (graph[startIndex][i] != 0 && !visitedIndex.contains(i)) {
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
        int startIndex = random.nextInt(words.size());
        List<List<Integer>> allpaths = new ArrayList<List<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        //path.add(startIndex);
        dfsRandomWalk(allpaths, path, startIndex);
        if (allpaths.size() > 0) {
            int randomPathIndex = random.nextInt(allpaths.size());
            List<Integer> shortestPath = allpaths.get(randomPathIndex);

            for (int i = 0; i < shortestPath.size(); i++) {
                result += words.get(shortestPath.get(i));
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
        for (int i = 0; !hasEdge && i < graph[startIndex].length; i++) {
            if (graph[startIndex][i] != 0) {
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

        for (int i = 0; i < graph[startIndex].length; i++) {
            if (graph[startIndex][i] != 0) {
                dfsRandomWalk(allpaths, visitedIndex, i);
            }
        }
        //pop
        visitedIndex.pop();
    }

    /**
     * initial data
     */
    private void initData() {
        FileInputStream fileInputStream = null;
        Scanner in = null;
        List<String> allWords = new ArrayList<String>();
        Set<String> setWords = new HashSet<String>();
        // read from file
        try {
            fileInputStream = new FileInputStream(filename);
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
            System.out.println("File not found: " + filename);
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
        words = new ArrayList<String>(setWords);
        graph = new int[words.size()][words.size()];
        for (int i = 0; i < allWords.size() - 1; i++) {
            String currentWord = allWords.get(i);
            String nextWord = allWords.get(i + 1);
            int row = words.indexOf(currentWord);
            int column = words.indexOf(nextWord);
            graph[row][column]++;
        }
    }

    /**
     * Application help inforation
     */
    private static void help() {
        System.out.println("********* Welcome to this application *********");
        System.out.println("1. Query Bridge Words");
        System.out.println("2. Caculate Shortest Path between two words");
        System.out.println("3. Randomly Walk");
        System.out.println("4. Quit");
        System.out.println("5. Show the graph");
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
    /**
     * To solve user input
     *
     * @param in the input stream
     * @return String[] two words from user's input
     */
    private static String[] sovleInputString(Scanner in) {
        String line;
        System.out.print("Please input two words: ");
        line = in.nextLine();
        String[] array = line.split(" ");
        //to get two input words
        while (array.length != 2) {
            System.out.print("Invalid input,Please input two words:");
            line = in.nextLine();
            array = line.split(" ");
        }
        return array;
    }

    //main method
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid Parameters, please run with java WordGraph <filename>");
            System.exit(0);
        }
        String filename = args[0];
        WordGraph wordGraph = new WordGraph(filename);
        Scanner in = new Scanner(System.in);
        help();
        System.out.print("Please input your selected: ");
        String line = in.nextLine();
        while (!"4".equals(line)) {
            if ("1".equals(line)) {
                String[] array = sovleInputString(in);
                String result = wordGraph.queryBridgeWords(array[0], array[1]);
                System.out.println(result);
            } else if ("2".equals(line)) {
                String[] array = sovleInputString(in);
                String result = wordGraph.calcShortestPath(array[0], array[1]);
                System.out.println(result);
            } else if ("3".equals(line)) {
                String walkPath = wordGraph.randomWalk();
                System.out.println("Randomly Walk Path :");
                System.out.println(walkPath);
            } else {
                System.out.println("Error Selected!");
            }
            System.out.println();
            help();
            System.out.print("Please Input your selected: ");
            line = in.nextLine();
        }
        System.out.println("Goodbye.");
    }


}
