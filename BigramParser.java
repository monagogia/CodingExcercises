import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * @author Mona Gogia
 *
 */


public class BigramParser {
	
	private String _result;
	
	public void BigramParse(String text)
	{
		Hashtable<String, Integer> result = new Hashtable<String, Integer>();
		String[] stringArray = text.split(" ");
		String[] outputArray = new String[stringArray.length - 1];
		for (int i = 0; i < stringArray.length - 1; i++) {
		    outputArray[i] = stringArray[i].replaceAll("([^A-Za-z0-9 ])", "").toLowerCase() 
		    		+ " " 
		    		+ stringArray[i+1].replaceAll("([^A-Za-z0-9 ])", "").toLowerCase();
		}
		
		for(int i = 0; i < outputArray.length; i++)
		{
			if(result.containsKey(outputArray[i]))
			{
				int count = result.get(outputArray[i]).intValue();
				count++;
				result.put(outputArray[i], new Integer(count));
			}
			else
			{
				result.put(outputArray[i], new Integer("1"));
			}
		}
		
		//Print results of Bigram parsing
		System.out.println(result);
		_result = result.toString();
	}
	
	public BigramParser()
	{
		
	}
	
	public String GetParseResult()
	{
		return _result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigramParser parser = new BigramParser();
		Scanner reader = new Scanner(System.in).useDelimiter("\\n");
    	System.out.println("Please enter a line of text or text file absolute path: ");
    	System.out.println("If you want to end the game enter 'Quit'. ");
    	String resp;
    	Path path;
    	Boolean isFile;
    	do
    	{
    		resp = reader.next().replace("\r", "");
    		System.out.println("Text entered - " + resp);
    		try
    		{
    			path = FileSystems.getDefault().getPath(resp);
    			isFile = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
    		}
    		catch(InvalidPathException e)
    		{
    			isFile = false;
    		}
    		if(isFile)
    		{
    			
    			String str;
    			File file = new File(resp);
    			FileInputStream fis;
				try 
				{
					fis = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
	    			fis.read(data);
	    			fis.close();

	    			str = new String(data, "UTF-8");
	    			parser.BigramParse(str);
				} 
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
    		}
    		else
    		{
    			parser.BigramParse(resp.replaceAll("([^A-Za-z0-9 ])", ""));
    		}
    	}
    	while(!resp.replaceAll("([^A-Za-z0-9 ])", "").equalsIgnoreCase("Quit"));
    	System.out.println("Thanks for Playing !");
	}

}
