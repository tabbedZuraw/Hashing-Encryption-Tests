import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class j3DES {

    public static void main(String[] args) throws Exception {

	System.out.println("_________________");  
	System.out.println("");    
	System.out.println("Encryption Benchmark");        
	System.out.println("3DES");        
	System.out.println("_________________");        
	Scanner s = new Scanner(new File("/home/tabbin/Downloads/Java_Applications/string1024.txt"));
	String input = s.nextLine ();
	String output = " ";


	double lowest = 0, average = 0, highest = 0, lowestD = 0, averageD = 0, highestD = 0;
	int n = 10;
	for (int i = 0; i < n; i ++) {

	
        long millisES = System.nanoTime();
        byte[] codedtext = new j3DES().encrypt(input);
        long millisEK = System.nanoTime();
        output = new j3DES().decrypt(codedtext);
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

    public byte[] encrypt(String message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);

        return cipherText;
    }

    public String decrypt(byte[] message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }
}
