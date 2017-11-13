package project1;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordGraphTest {

	@Test
	public void testQueryBridgeWords1() {
		String expresult="The bridge words from \"explore\" to \"new\" is: strange";
		WordGraph text=new WordGraph("data.txt");
		String result=text.queryBridgeWords("explore","new");
		assertEquals(expresult,result);
	}
	@Test
	public void testQueryBridgeWords2() {
	String expresult1="No bridge words from \"explore\" to \"strange\"!";
	WordGraph text1=new WordGraph("data.txt");
	String result1=text1.queryBridgeWords("explore","strange");
	assertEquals(expresult1,result1);
	}
	
	@Test
	public void testQueryBridgeWords3() {
	String expresult1="NO \"explo\" in the graph!";
	WordGraph text1=new WordGraph("data.txt");
	String result1=text1.queryBridgeWords("explo","strange");
	assertEquals(expresult1,result1);
	}
	
	@Test
	public void testQueryBridgeWords4() {
	String expresult1="NO \"stra\" in the graph!";
	WordGraph text1=new WordGraph("data.txt");
	String result1=text1.queryBridgeWords("explore","stra");
	assertEquals(expresult1,result1);
	}
	
	@Test
	public void testQueryBridgeWords5() {
	String expresult1="NO \"expre\" and \"strge\" in the graph!";
	WordGraph text1=new WordGraph("data.txt");
	String result1=text1.queryBridgeWords("expre","strge");
	assertEquals(expresult1,result1);
	}
}
