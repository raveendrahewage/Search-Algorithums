import java.util.*;
import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;


public class kmpAlgo
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

		kmpSearch(list);
	}

	static void kmpSearch(ArrayList<Character> list)
	{
		System.out.println("----------------------------------------------");
		System.out.println("\t       KMP Algorithum");
		System.out.println("----------------------------------------------");
		Scanner ob = new Scanner(System.in);
		System.out.print("\nEnter the birthday string : ");
		String str2 = ob.nextLine();

		String fileName="results.txt";

		try
		{    
           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
           results.write("----------------------------------------------");
           results.write("\n\t       KMP Algorithum");
           results.write("\n----------------------------------------------");
           results.write("\nSearch results of : "+str2+"\n");
           results.close();    
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }   

		int[] pi=new int[str2.length()];
		pi[0]=0;

		int i=1,j=0;

		while(i<str2.length())
		{
			if(str2.charAt(j)==str2.charAt(i))
			{	
				pi[i]=j+1;
				i++;
				j++;
			}
			else
			{	
				if(j>0)
				{
					j=pi[j-1];
				}
				else
				{
					pi[i]=0;
					i++;
				}
			}
		}
		
		int k=0,l=0,count=0;

		while(k<list.size())
		{
			if(list.get(k)==str2.charAt(l))
			{
				k++;
				l++;
			}

			if(l==str2.length())
			{
				count++;
				try
		        { 
   
		            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		            out.write("\n"+count+". Matching substring found, starting at : "+(k-str2.length())+"\n"); 
		            out.close(); 
		        } 
		        catch (IOException e)
		        { 
		            System.out.println("exception occoured" + e); 
		        }
				l=pi[l-1];
			}
			else if (k<list.size() && str2.charAt(l) != list.get(k))
			{
				if(l>0)
				{
					l=pi[l-1];
				}
				else
				{
					k++;
				}
			}
		}

		System.out.println("----------------------------------------------");
		System.out.println("\nNumber of found substrings : "+count+"\n");
	}
}