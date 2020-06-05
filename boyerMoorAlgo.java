import java.util.*;
import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;


public class boyerMoorAlgo
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

		boyerMoorSearch(list);
	}

	static void boyerMoorSearch(ArrayList<Character> list)
	{
		System.out.println("\n----------------------------------------------");
		System.out.println("\t   Boyer Moor Algorithum");
		System.out.println("----------------------------------------------");
		Scanner ob = new Scanner(System.in);
		System.out.print("\nEnter the birthday string : ");
		String str2 = ob.nextLine();

		String fileName="results.txt";

		try
		{    
           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
           results.write("----------------------------------------------");
           results.write("\n\t   Boyer Moor Algorithum");
           results.write("\n----------------------------------------------");
           results.write("\nSearch results of : "+str2+"\n");
           results.close();    
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }

		Map<Character,Integer> vec = new HashMap<Character,Integer>();
		vec.put(str2.charAt(0),str2.length()-1);
		
		for(int i=1;i<str2.length();i++)
		{
			if(vec.containsKey(str2.charAt(i)))
			{
				vec.replace(str2.charAt(i),i);
			}
			else
			{
				vec.put(str2.charAt(i),i);
			}
		}

		int count1=0,skips=0;

		while(skips<=list.size()-str2.length())
		{
			int i=str2.length()-1;
			while(i>=0 && str2.charAt(i)==list.get(skips+i))
			{
				i--;
			}

			if(i<0)
			{
				count1++;
				try
		        { 
   
		           	BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		         	out.write("\n"+count1+". Matching substring found, starting at : "+skips+"\n"); 
		           	out.close(); 
		       	} 
		       	catch(IOException e)
		       	{	 
		           	System.out.println("exception occoured" + e); 
		       	}
		     
		       	if(skips+str2.length()<list.size())
		       	{	
		       		if(vec.containsKey(list.get(skips+str2.length())))
		       		{
		       			skips=skips+str2.length()-vec.get(list.get(skips+str2.length()));
		       		}
		       		else
		       		{
		       			skips=skips+str2.length()+1;
		       		}
		       	}
		       	else
		       	{
		       		skips++;
		       	}
			}
			else
			{
				if(vec.containsKey(list.get(skips+i)))
		       	{
		       		skips=skips+Math.max(1,i-vec.get(list.get(skips+i)));
		       	}
		       	else
		       	{
		       		skips=skips+Math.max(1,i+1);
		       	}
			}
		}

		System.out.println("----------------------------------------------");
		System.out.println("\nNumber of found substrings : "+count1+"\n");
	}

}