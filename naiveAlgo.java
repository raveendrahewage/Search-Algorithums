import java.util.*;
import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;


public class naiveAlgo
{
	public static void main(String []args)
	{
		int lineNo=0;
		ArrayList<Character> list = new ArrayList<Character>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("pi.txt"));
			String line = reader.readLine();
			while (line != null)
			{
				if(lineNo>=21)
				{
					lineNo++;
					if(lineNo>21)
					{
						if(!line.isEmpty())
						{
							for(int i=0;i<54;i++)
							{
								if(line.charAt(i)!=' ')
								{
									list.add(line.charAt(i));
								}
							}
						}
					}
				}
				line = reader.readLine();
				lineNo++;
			}
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		String fileName="results.txt";

		naiveSearch(list);
	}
	
	static void naiveSearch(ArrayList<Character> list)
	{
		System.out.println("\n----------------------------------------------");
		System.out.println("\t      Naive Algorithum");
		System.out.println("----------------------------------------------");
		Scanner ob = new Scanner(System.in);
		System.out.print("\nEnter the birthday string : ");
		String str2 = ob.nextLine();

		String fileName="results.txt";

		try
		{    
           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
           results.write("----------------------------------------------");
           results.write("\n\t      Naive Algorithum");
           results.write("\n----------------------------------------------");
           results.write("\nSearch results of : "+str2+"\n");
           results.close();    
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }    

		int i,j,count=0;
		for(i=0;i<=list.size()-str2.length();i++)
		{
			j=0;
			while(j<str2.length() && list.get(i+j)==str2.charAt(j))
			{
				j++;
			}
			if(j==str2.length())
			{
				count++;
				try
		        { 
   
		            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		            out.write("\n"+count+". Matching substring found, starting at : "+i+"\n"); 
		            out.close(); 
		        } 
		        catch (IOException e)
		        { 
		            System.out.println("exception occoured" + e); 
		        }
			}
		}
		System.out.println("\n----------------------------------------------");
		System.out.println("\nNumber of found substrings : "+count+"\n");
	}

}