import javax.crypto.spec.*;
import java.security.*;
import javax.crypto.*;
import org.bouncycastle.util.encoders.Hex;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
  
public class jTwoFish
{
   public static void main(String []args) throws Exception {

	System.out.println("_________________");  
	System.out.println("");    
	System.out.println("Encryption Benchmark");        
	System.out.println("TwoFish");        
	System.out.println("_________________");        
	Scanner s = new Scanner(new File("/home/tabbin/Downloads/Java_Applications/string16.txt"));
	String toEncrypt = s.nextLine ();

	double lowest = 0, average = 0, highest = 0, lowestD = 0, averageD = 0, highestD = 0;
	int n = 10;
	for (int i = 0; i < n; i ++) {

	
        long millisES = System.nanoTime();
        byte[] encrypted = encrypt(toEncrypt, "password");
        long millisEK = System.nanoTime();
        String decrypted = decrypt(encrypted, "password");
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
  
   public static byte[] encrypt(String toEncrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("twofish");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance("twofish");
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, sk);
  
      // enctypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
  
      return encrypted;
   }
  
   public static String decrypt(byte[] toDecrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("twofish");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // do the decryption with that key
      Cipher cipher = Cipher.getInstance("twofish");
      cipher.init(Cipher.DECRYPT_MODE, sk);
      byte[] decrypted = cipher.doFinal(toDecrypt);
  
      return new String(decrypted);
   }
}
