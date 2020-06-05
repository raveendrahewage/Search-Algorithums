import java.util.*;
import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;


public class SearchAlgos
{
	public static void main(String []args)
	{
		int lineNo=0;
		Vector<Character> list = new Vector<Character>();
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

		try
		{    
           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
           results.write("----------------------------------------------");
           results.write("\n\t String Matching Algorithums");
           results.write("\n----------------------------------------------\n");
           results.close();    
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }    

		int ch=0;
		while(ch!=5)
		{
			Scanner cho = new Scanner(System.in);
			System.out.println("----------------------------------------------");
			System.out.println("\t String Matching Algorithums");
			System.out.println("----------------------------------------------\n");
			System.out.println("\n1. Naive Search");
			System.out.println("2. KMP Search");
			System.out.println("3. Rabin Kraph Search");
			System.out.println("4. Booyer Moor Search");
			System.out.println("5. Exit");
			System.out.print("\nSelect a searching algorithum : ");
			ch = cho.nextInt();

			switch(ch)
			{
	   			case 1:naiveSearch(list);break;
	   			case 2:kmpSearch(list);break;
	   			case 3:rabinKarpSearch(list);break;
	   			case 4:boyerMoorSearch(list);break;
	   			case 5:	System.out.println("----------------------------------------------");
	   					System.out.println("\t\t     Exit");
	   					System.out.println("----------------------------------------------\n");
	   					break;
	   			default:System.out.println("----------------------------------------------");
	   					System.out.println("\nEnter a valid choice\n");
	   					break;
			}
		}
	}
	
	static void naiveSearch(Vector<Character> list)
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

	static void kmpSearch(Vector<Character> list)
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

	static void rabinKarpSearch(Vector<Character> list)
	{
		System.out.println("\n----------------------------------------------");
		System.out.println("\t    Rabin Karp Algorithum");
		System.out.println("----------------------------------------------");
		Scanner ob = new Scanner(System.in);
		System.out.print("\nEnter the birthday string : ");
		String str2 = ob.nextLine();

		String fileName="results.txt";

		try
		{    
           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
           results.write("----------------------------------------------");
           results.write("\n\t    Rabin Karp Algorithum");
           results.write("\n----------------------------------------------");
           results.write("\nSearch results of : "+str2+"\n");
           results.close();    
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }

		int i,j=0,k,l,x,count1=0,count2,count3,count4=0;

		for(i=0;i<str2.length();i++)
		{
			count1=count1+(int)str2.charAt(i);
		}

		while(j<=list.size()-str2.length())
		{
			count2=0;
			for(k=j;k<j+str2.length();k++)
			{
				count2=count2+(int)list.get(k);
			}

			if(count1==count2)
			{
				count3=0;x=0;
				for(l=j;l<j+str2.length();l++)
				{
					if(list.get(l)==str2.charAt(x))
					{
						count3++;
						x++;
					}
				}
				if(count3==str2.length())
				{
					count4++;
					try
		        	{ 
   
		            	BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		            	out.write("\n"+count4+". Matching substring found, starting at : "+j+"\n"); 
		            	out.close(); 
		        	} 
		        	catch(IOException e)
		        	{	 
		            	System.out.println("exception occoured" + e); 
		        	}
				}
			}

			j++;
		}
		
		System.out.println("----------------------------------------------");
		System.out.println("\nNumber of found substrings : "+count4+"\n");
	}

	static void boyerMoorSearch(Vector<Character> list)
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