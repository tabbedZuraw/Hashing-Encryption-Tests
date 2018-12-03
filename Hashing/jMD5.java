import java.util.Scanner;
import java.io.File;
import java.security.*;
import java.math.*;

public class jMD5 {
    public static void main(String args[]) throws Exception{
	System.out.println("_________________");  
	System.out.println("");    
	System.out.println("Hashing Benchmark");        
	System.out.println("MD5");        
	System.out.println("_________________");        
	Scanner s = new Scanner(new File("/home/tabbin/Downloads/Java_Applications/string1024.txt"));
	String input = s.nextLine ();
	String output = " ";
	
	double lowest = 0;
	double average = 0;
	double highest = 0;
	int n = 1000;
	for (int i = 0; i < n; i ++) {
	long millisS = (System.nanoTime());

        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(input.getBytes(),0,input.length());   
     
        long millisE = (System.nanoTime());
	double timeT = (millisE - millisS) / 1e6;

	if(lowest == 0){
		output = new BigInteger(1,m.digest()).toString(16);
		lowest = timeT;}
	if(highest == 0){
		highest = timeT;}
	if(average == 0){
		average = timeT;}
	if(timeT < lowest){
		lowest = timeT;}
	if(timeT > highest){
		highest = timeT;}
	if(average != 0){
		average = (average + timeT) / 2;}
	
	
	}

	System.out.println("Input : " + input);    
	System.out.println("Output : " + output);    
	System.out.println("");    
	System.out.println("Lowers : "+lowest);    
	System.out.println("Average : "+average);     
	System.out.println("Highest : "+highest);              

    }
}
