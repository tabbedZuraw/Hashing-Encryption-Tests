import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class jAES {

    public static void main(String[] args) throws Exception {
	
	System.out.println("_________________");  
	System.out.println("");    
	System.out.println("Encryption Benchmark");        
	System.out.println("AES");        
	System.out.println("_________________");       
	Scanner w = new Scanner(new File("/home/tabbin/Downloads/Java_Applications/string1024.txt"));
	String input = w.nextLine ();
	String output = " ";
	double lowest = 0, average = 0, highest = 0, lowestD = 0, averageD = 0, highestD = 0;
	int n = 10;
	for (int i = 0; i < n; i ++) {

	long millisES = System.nanoTime();
	String encrypted = encrypt(input);
        long millisEK = System.nanoTime();
	String decrypted = decrypt(encrypted); 
        long millisDK = System.nanoTime();
	double timeTE = (millisEK - millisES) / 1e6;
	double timeTD = (millisDK - millisEK) / 1e6;

	if(lowest == 0){
		lowest = timeTE;}
	if(highest == 0){
		highest = timeTE;}
	if(average == 0){
		average = timeTE;}
	if(timeTE < lowest){
		lowest = timeTE;}
	if(timeTE > highest){
		highest = timeTE;}
	if(average != 0){
		average = (average + timeTE) / 2;}

	if(lowestD == 0){
		lowestD = timeTD;}
	if(highestD == 0){
		highestD = timeTD;}
	if(averageD == 0){
		averageD = timeTD;}
	if(timeTD < lowestD){
		lowestD = timeTD;}
	if(timeTD > highestD){
		highestD = timeTD;}
	if(averageD != 0){
		averageD = (averageD + timeTD) / 2;}
	}

	System.out.println("Encrypt");    
	System.out.println("Lowers : "+lowest);    
	System.out.println("Average : "+average);     
	System.out.println("Highest : "+highest);
	System.out.println("Decrypt");    
	System.out.println("Lowers : "+lowestD);    
	System.out.println("Average : "+averageD);     
	System.out.println("Highest : "+highestD);  

   }
	private static byte[] key = {
        0x2d, 0x2a, 0x2d, 0x42, 0x55, 0x49, 0x4c, 0x44, 0x41, 0x43, 0x4f, 0x44, 0x45, 0x2d, 0x2a, 0x2d
    };
 
    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            String encryptedString = new String(Base64.getEncoder().encode(cipherText),"UTF-8");
            return encryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] cipherText = Base64.getDecoder().decode(encryptedText.getBytes("UTF8"));
            String decryptedString = new String(cipher.doFinal(cipherText),"UTF-8");
            return decryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
