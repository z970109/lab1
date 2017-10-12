
package project1;
public class text {
    public static void main(String[] args) {

    	String[] vertex = { "apple", "b", "c", "d", "end", "f", "g", "h", "i", "j", "k" };
    	double[][] matrix = {   
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },  
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },  
                { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },  
                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },  
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },  
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },  
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },  
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },  
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },  
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
    	
    	graph<String> graph = new graph<>(matrix, vertex);
    	System.out.println(graph.randNum);
        System.out.println(graph.startSearch(randNum));
    }
    
}