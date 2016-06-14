package Project1;

import java.io.*;
import java.text.*;
import java.util.*;

public class Sort_Algorithm
{
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) 
	{
		System.out.println("Sorting Demonstration");
		System.out.println("========================================\n");

		try
		{
				System.out.println("\nEnter the number of random numbers to be generated\n");  	
				int n = in.nextInt();
				System.out.println("\nEnter the maximum value for the random number\n");  	
				int max = in.nextInt();
				int[]arr = new int[n]; 
				String str;

				/**Generating random numbers**/
				Random randomGenerator = new Random();	    	
				File f = new File("C:\\MyWork\\Algo\\Sort_Output.txt");
				if (!f.exists()) { f.createNewFile(); }
				FileWriter fstream = new FileWriter(f);
				BufferedWriter out = new BufferedWriter(fstream);


				/**Array initialization for the random numbers**/
				for (int idx = 0; idx < n; ++idx)
				{
					arr[idx]  = randomGenerator.nextInt(max);
					str = "Random generated number at index " + idx + " is: " + arr[idx];
					System.out.println(str);
					out.write(str);
					out.newLine();
				}
				str = "***********************************************************************";
				System.out.println(str);
				out.write(str);
				out.newLine();
				
				/** Calling Quick Sort**/
				int[]qsArray = new int[n]; 
				qsArray = arr;					
				long qsStart = System.nanoTime(); //Set timer
				Quick_Sort(qsArray,0,n-1); 
				long qsEnd =  System.nanoTime();  //End Timer
				
				/**Calling Count Sort**/
				int[]csArray = new int[n]; 
				csArray = arr;					
				long csStart = System.nanoTime();  // Set timer
				Count_Sort(csArray,n);
				long csEnd = System.nanoTime();    // End timer

				/**Calling Selection Sort**/
				int[]ssArray = new int[n]; 
				ssArray = arr;
				long ssStart = System.nanoTime(); //  Set timer
				Selection_Sort(ssArray,n);    
				long ssEnd = System.nanoTime();   // End timer


				/**After Quick sort - write array values**/
				for(int i=0;i<n;i++) 
				{
					str = "After Quick sort: Element at index " + i + " is: " + qsArray[i];
					System.out.println(str);
					out.write(str);
					out.newLine();
				}					
				str = "************************** End of Quick Sort **************************";
				System.out.println(str);
				out.write(str);
				out.newLine(); 					
				System.out.println("\n");

				/**After Count sort - write array values**/
				for(int i=0;i<n;i++) 
				{
					str = "After Count sort: Element at index " + i + " is: " + csArray[i];
					System.out.println(str);
					out.write(str);
					out.newLine();
				}
				str = "************************** End of Count Sort **************************";
				System.out.println(str);
				out.write(str);
				out.newLine();  
				System.out.println("\n");

				/**After Selection sort - write array values**/
				for(int i=0;i<n;i++) 
				{
					str = "After Selection sort: Element at index " + i + " is: " + ssArray[i];
					System.out.println(str);
					out.write(str);
					out.newLine();
				}			
				str = "********************** End of Selection Sort **************************";
				System.out.println(str);
				out.write(str);
				out.newLine();  
				System.out.println("\n");

				/**Run time calculation for all the sorts**/
				NumberFormat formatter = new DecimalFormat("#0.0000000000");

				str = "Execution time of Selection sort is " + formatter.format((ssEnd - ssStart) / 1000000d) + " milli seconds";
				System.out.println(str);
				out.write(str);
				out.newLine();  

				str = "Execution time of Quick sort is " +     formatter.format((qsEnd - qsStart) / 1000000d) + " milli seconds";
				System.out.println(str);
				out.write(str);
				out.newLine(); 

				str = "Execution time of Count sort is " +     formatter.format((csEnd - csStart) / 1000000d) + " milli seconds";
				System.out.println(str);
				out.write(str);
				out.newLine(); 

				System.out.println("\n");

				out.close();
				             
		}
		catch(Exception e)
		{
			System.out.println(e); 

		}

	}
	static void Selection_Sort(int[]ssArray,int n)  
	{
		try
		{
			for(int lastIndex = n-1; lastIndex >=1; lastIndex--)
			{
				/**Considering the first element in the array as the largest element**/
				int largestIndex=0;
				for(int p=1;p<=lastIndex;p++)
				{
					/**Comparing the other elements in the array with the first element**/
					if(ssArray[p] > ssArray[largestIndex])
					{
						/**The largest index is set to the index of the largest element **/
						largestIndex=p;
					}
					/**Performing swap between the largestIndex element & the lastIndiex element**/
					if(largestIndex != lastIndex)
					{
						int tmp=ssArray[largestIndex];
						ssArray[largestIndex]=ssArray[lastIndex];
						ssArray[lastIndex]=tmp;
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	static void Count_Sort(int[]csArray,int n)  
	{
		try
		{
			int max_val=csArray[0],min_val=csArray[0],k = 0;
			for ( int idx = 1; idx < n; idx++)
			{
				if(csArray[idx] > max_val)
					max_val = csArray[idx];
				if(csArray[idx] < min_val)
					min_val = csArray[idx]; 
			}
			k=max_val-min_val+1;
			int i,j=0;
			int[]carr = new int[k];
			/**Forming the count array that will hold the count of each element in the input array**/
			for(i =0; i < n; i++)
			{
				carr[csArray[i] - min_val]++;
			}
			/**  Modification of the count array so that positions of output array is obtained **/
			for (i = 1; i < k; i++)
			{
				carr[i] = carr[i] + carr[i - 1];
			}  
			/** Formation of the final array **/
			for (i = 0; i < k; i++)
				while (j < carr[i])
					csArray[j++] = i + min_val;
		}
		catch(Exception e)
		{
			System.out.println(e);

		}
	}
	static void Quick_Sort( int[]qsArray, int first, int last )
	{
		try
		{
			int cut;
			if ( first < last )
			{
				cut = Partition(qsArray, first, last);
				Quick_Sort( qsArray, first, cut-1 );
				Quick_Sort( qsArray, cut+1, last );
			}

		}
		catch(Exception e)
		{
			System.out.println(e);

		}
	}

	private static int Partition( int[]qsArray, int first, int last )
	{

		int pivot,i,j;
		//int temp,temp1;
		/**  Choosing the last element of the input array as the pivot element **/     	
		pivot=qsArray[last];
		/**Initialization of an variable i**/
		i=first-1;
		for(j=first; j<last; j++)
		{
			/**Checking if each element from the start of the array is lesser than the pivot element**/
			if(qsArray[j] <= pivot)
			{
				/** If it is lesser, then we increment i-th position and perform swap**/
				i=i+1;
				if (i != j)
				{
				swap(qsArray,i,j);
				}
			}
		}
		/**Swap the pi'vot element such that left of pivot element are lesser than pivot and right are greater than pivot**/
		swap(qsArray,i+1,last);  
		return i+1;

	}
	
	private static void swap(int[] qsArray, int i, int j) {
		int temp;
		temp=qsArray[i];
		qsArray[i]=qsArray[j];
		qsArray[j]=temp;
		
	}

}
 