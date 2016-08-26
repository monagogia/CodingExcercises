import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import org.junit.Test;

/**
 * @author Mona Gogia
 *
 */
public class BigramParserTest {

	@Test
	public void TestTextInput() {
		String textInput = "The quick brown fox and the quick blue hare";
		//InputStream in = new ByteArrayInputStream(textInput.getBytes());
	    //System.setIn(in);
	    
	    BigramParser parser = new BigramParser();
	    parser.BigramParse(textInput);
	    assertEquals("{quick brown=1, brown fox=1, and the=1, blue hare=1, fox and=1, quick blue=1, the quick=2}", parser.GetParseResult());
	}
	
	@Test
	public void TestFileInput() {
		String textInput = "D:\\Interview\\VAECoding\\Test.txt";
		//InputStream in = new ByteArrayInputStream(textInput.getBytes());
	    //System.setIn(in);
		Path path;
    	Boolean isFile;
		try
		{
			path = FileSystems.getDefault().getPath(textInput);
			isFile = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
		}
		catch(InvalidPathException e)
		{
			e.printStackTrace();
		}
		
		String str;
		File file = new File(textInput);
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			str = new String(data, "UTF-8");
			
			BigramParser parser = new BigramParser();
			parser.BigramParse(str);
		    assertEquals("{quick brown=1, brown fox=1, and the=1, blue hare=1, fox and=1, quick blue=1, the quick=2}", parser.GetParseResult());
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

}
