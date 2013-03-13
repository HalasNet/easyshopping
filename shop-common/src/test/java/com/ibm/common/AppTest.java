package com.ibm.common;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    /**
     *   abcdeghhhhddd
     *   bcde
     * 
     * 
     * @param source
     * @param target
     */
    public static void str(String source,String target){
    	for(int i=0;i<source.length()-target.length();i++){
    		for(int j=0;j<target.length();j++){
    			if(source.charAt(i+j) != target.charAt(j))
    				break;
    			if(j == target.length()-1){
    				System.out.println("NO."+i);
    				return;
    			}
    		}
    	}
    	System.out.println("nothing");
    }
    
    public static void main(String[]args){
    	String source = "abcdegdfdgdgdg";
    	String target = "degdf0";
    	str(source,target);
    }
}
