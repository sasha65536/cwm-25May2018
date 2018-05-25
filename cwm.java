

// Hi!

// This is a code-with-me thing for Sunday's presentation on 24-May-2018
// by Sasha

// It is a Java solution to the hash finding problem
//  written in haste during the event while successfully taking python notes as well!!!

// There is a whole bunch of cut-paste stuff that came directly from the interwebs.
// these links were helpful (read: plagiarized)
// https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
// https://stackoverflow.com/questions/923863/converting-a-string-to-hexadecimal-in-java

// Also, I'm cheating in an additional way by using my pre-written "utilmain.jar" library
// But the utilmain is just for file handling. The calls used "to_mem_c" and "to_disk_c"
//  load a text file, separated by newlines, into a string array and write a string
//  array to a file, separating each string by a newline. Whoever is nice enough to
//  add the source for this is super cool but it isn't going to me at the moment as I'm
//  feeling too lazy to re-write it right now and my original source for it is on a 
//  different machine. I'll dig it up at some point.

// P.S. Look! I've documented my code!!!

// P.P.S. Here's the output! I hope it is correct because I didn't check it.
/*
rainbow table created!!!
found matching hash for: password
found matching hash for: america
found matching hash for: 12121212
found matching hash for: megadeth
found matching hash for: 362436
found matching hash for: melissa1
found matching hash for: smith1
found matching hash for: 123asd
found matching hash for: abc1234
finito!
*/




import java.security.*;

public class cwm
{
	public static String toHashNext(String s)
	{
		try
		{
			byte[] bytesOfMessage = s.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			return asHex(thedigest);
		}
		catch(Exception e){e.printStackTrace();}
		return "";
	}
	public static String toHashAll(String mem[])
	{
		StringBuilder sb=new StringBuilder();

		for (int i=0;i<mem.length;i++)
			sb.append(toHashNext(mem[i])+"\n");

		return sb.toString();
	}
	public static void main(String argv[])
	{
		String namesList[]=new file("plaintext_passwords.txt").to_mem_c();

		String s=toHashAll(namesList);
	
		new file("rainbow_table.txt").to_disk_c(new String[]{s});


		System.out.println("rainbow table created!!!");


		printAllMatches(namesList,
				new file("recovered_password_hashes.txt").to_mem_c(),
				new file("rainbow_table.txt").to_mem_c());

		
	}

	public static void printAllMatches(String namesList[],String recovered[],String rainbow[])
	{
		for (int a=0;a<recovered.length;a++)
			for (int b=0;b<rainbow.length;b++)
				if (recovered[a].equalsIgnoreCase(rainbow[b]))
				{
					System.out.println("found matching hash for: "+namesList[b]);
				}
	}


	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	public static String asHex(byte[] buf)
	{
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i)
		{
		    chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
		    chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}
}
