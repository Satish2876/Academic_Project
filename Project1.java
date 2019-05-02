import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;
public class Encryption
{	
	public static String encrypt(String text, char key[])
    {
		System.out.println("\n\t ENCRYPTION");
		System.out.println("\t--------------------------");
        String res = "";
		
		//First Level Encryption with the Vigenere Table
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key[j] - 2 * 'A') % 26 + 'A');
            j = ++j % key.length;
        }
        System.out.println("\n\t First Level Encrypted Text  : "+res);
		//Second Level Encryption with Railfence 2 	
		String pText = res;
        int len,n1,n2,a,b;
        
        len=pText.length();
        if(len%2==0)
            n1=n2=len/2;
        else
            n1=n2=(len+1)/2;
        
        char A1[]= new char[n1];
        char B2[]= new char[n2];
        
        a=b=0;
        for(int i=0;i<len;i++){
            char c=pText.charAt(i);
            
            if(i%2==0){
                
                A1[a]=c;
                a++;
               
                
            }
            else{
                B2[b]=c;
                b++;
                
            }
        }   
                      
            String encMsg=(new StringBuilder()).append(A1).append(B2).toString(); 
             
            return encMsg;
		
    }
	
	public static String decrypt(String text)
	{
			System.out.println("\n\t DECRYPTION");
			System.out.println("\t--------------------------");
			String res = "";
			
			Scanner sc = new Scanner(System.in);
			String dkey, fk;
			int klen = text.length();
			System.out.print("\n\tEnter Decryption Key : ");
					dkey = sc.nextLine();
					fk = dkey.toUpperCase();
					char fdkey[] = new char[klen];
					for(int i=0, j=0 ; i< klen; ++i, ++j)
					{
						if(j == fk.length())
						j=0;
			
						fdkey[i] = fk.charAt(j); 
					}
							
			String m = text;
			int len,n1,n2;
			int a,b;
			len=m.length();
        
			if(len%2==0)
			n1=n2=len/2;
			else
			n1=n2=(len+1)/2;
            
			char A1[] = new char[n1];
			char B2[] = new char[n2];
			a=b=0;
			for(int i=0;i<n1;i++)
			{
				char c=m.charAt(i);
				A1[a]=c;
				a++;
			}
			for(int i=n2;i<len;i++)
			{
				char c=m.charAt(i);
				B2[b]=c;
				b++;
			}
			char dmsg[] =new char[len];
			a=b=0;       
			for(int i=0;i<len;i++)
			{
				if(i%2==0)
				{
					dmsg[i]= A1[a];
					a++;
				}
				else
				{
					dmsg[i]= B2[b];
					b++;   
				}
            
			}
			String ptxt =(new StringBuilder()).append(dmsg).toString(); 
			String txt = ptxt.replaceAll("\\s","");
			System.out.println("\n\tFirst Level Decrypted Text  : "+ptxt);
			
			for (int i = 0, j = 0; i < txt.length(); i++)
			{
				char c = txt.charAt(i);
				if (c < 'A' || c > 'Z')
                continue;
				res += (char) ((c - fdkey[j] + 26) % 26 + 'A');
				j = ++j % fdkey.length;
			}
			return res;
					
	}	

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String plainText,key,pt,k;
		int len;
		
		System.out.print("\t\n\n       ~:DEVELOPMENT OF IMPROVED SYMMETRIC CRYPTOGRAPHY:~");
		System.out.print("\n-----------------------------------------------------------------\n\n");
		System.out.print("\tEnter the Plain Text Message  : ");
		plainText=sc.nextLine();
		pt = plainText.toUpperCase();
		String fplainText = pt.replaceAll("\\s","");
		len = fplainText.length();
		System.out.print("\n\tEnter the key of 5 characters : ");
		key=sc.nextLine();
		k = key.toUpperCase();
		if(key.length()<5)
		{
			System.out.println("\tInvalid Key !!! Key must be of 5 characters. ");
			
		}
		System.out.println("\n\tYour Message   : "+fplainText);
		System.out.println("\tYour Key       : "+k);
		
		System.out.println("\n\tMessage Length : "+len);
				
		//Creating Key of length equal to Plain Text by cyclic repeatition of Key
		char fkey[] = new char[len];
		for(int i=0, j=0 ; i< len; ++i, ++j)
		{
			if(j == k.length())
				j=0;
			
			fkey[i] = k.charAt(j); 
		}
		
		//System.out.print("\tFinal key      : ");
		//System.out.println(fkey);
		long t = System.currentTimeMillis();
		String EncText = encrypt(fplainText , fkey);
		System.out.println("\n\t Second Level Encrypted Text : "+EncText);
		
		System.out.print("\n\tPress 1 for Decryption or 2 to exit :");
		int c=sc.nextInt();
		
		switch(c)
		{
			case 1:		
					String DcrTxt = decrypt(EncText);
					System.out.println("\n\tFinal Decrypted Text        : "+DcrTxt);
					long tt = System.currentTimeMillis();
					long d=tt-t;
     System.out.println("Time Interval : "+d);
					break;
			case 2:
			       break;
				   
			default:
					System.out.println("\n\tInvalid Choice !!!!!!!");
					
		}		   
	}
}
	