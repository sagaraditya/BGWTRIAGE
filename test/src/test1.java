import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		try{
		Scanner s = new Scanner(new File("C:\\Users\\Administrator\\workspace\\test\\src\\positiveDump.txt"));
		ArrayList<String> list_pos = new ArrayList<String>();
		while (s.hasNext()){
		    list_pos.add(s.next());
		}
		s.close();	
		Scanner s1 = new Scanner(new File("C:\\Users\\Administrator\\workspace\\test\\src\\negativeDump.txt"));
		ArrayList<String> list_neg = new ArrayList<String>();
		while (s1.hasNext()){
		    list_neg.add(s1.next());
		}
		s1.close();
		Scanner s2 = new Scanner(new File("C:\\Users\\Administrator\\workspace\\test\\src\\changerDump.txt"));
		ArrayList<String> list_changers = new ArrayList<String>();
		while (s2.hasNext()){
			list_changers.add(s2.next());
		}
		s2.close();
		Scanner s3 = new Scanner(new File("C:\\Users\\Administrator\\workspace\\test\\src\\conjDump.txt"));
		ArrayList<String> list_conj = new ArrayList<String>();
		while (s3.hasNext()){
			list_conj.add(s3.next());
		}
		s3.close();
		File fout = new File("C:\\Users\\Administrator\\workspace\\test\\src\\BGWTRIAGE.txt");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		
			
	String filename = "C:\\Users\\Administrator\\workspace\\test\\src\\myfile.txt";
	
	BufferedReader reader = new BufferedReader(new FileReader(filename));
	String[] a;
	
	  String line;
	  int sss=0,pos_count=0,neg_count=0,pos,neg,x,changer=0,weight=0,conj,a1=0,b1=0,c1=0,finWeight=0,lastweight=0,lastbutOne=0;
	  
	  //as long as there are lines in the file, print them
	  while((line = reader.readLine()) != null){
		  sss++;
		  System.out.println(line+sss);
		  pos=0;
		  neg=0;
		  String[] sentences = line.split("\\.");
		  for (String sentence : sentences)
		  {
		  String[] words = sentence.split(" ");
		  pos_count=0;
		  neg_count=0;
		  x=1;
		  conj=0;
		  weight=0;
		  changer=0;
		  for (String word : words)
		  {
			  if(list_conj.contains(word.toLowerCase()))
			  {
				if(changer==0)
				{
					if(pos_count>neg_count)
						  weight++;
					  if(neg_count>pos_count)
						  weight--;
				}
				 changer=0; 
				conj=1;
				pos_count=0;
				neg_count=0;
			  }
			  if(list_changers.contains(word.toLowerCase()))
			  {
				changer++;  
				if (changer>1)
				{
					pos_count=0;
					neg_count=0;
				}
			  }
			  if(list_pos.contains(word.toLowerCase()))
			  { pos_count++;
			  //System.out.println("pos "+word);
			  }
			  if(list_neg.contains(word.toLowerCase()))
			  {
				  neg_count++;
				  
				  //System.out.println("neg "+word);
			  }
			  if((changer==1 && pos_count>0 && weight==0)||(changer==1 && pos_count>0 && conj==1))
			  {
				  weight--;
			  }
			  if((changer==1 && neg_count>0 && weight==0)||(changer==1 && pos_count>0 && conj==1))
			  {
				  weight++;
			  }
			  
			 //System.out.println("after word "+x+" changer "+changer+" pos_count "+pos_count+" neg_count "+neg_count+" weight "+weight);
			  x++;
		}
		  if(conj==1)
		  {
			  if(pos_count>neg_count)
				  weight++;
			  if(neg_count>pos_count)
				  weight--;
		  }
		  if(weight==0 && conj==0)
		  {
			  if(pos_count>neg_count)
				  weight++;
			  if(neg_count>pos_count)
				  weight--;
		  }
			 // System.out.println(word);
		 //System.out.println("no. of pos words="+pos_count+", no. of neg words="+neg_count+" weight "+weight);
		  lastweight=weight;
		  lastbutOne = finWeight ;
		  finWeight = finWeight+weight; 
		  
	  }
		///*******///
		  //System.out.println(lastweight+","+lastbutOne);
		  //lastweight=weight;
		  if(lastweight>0 && lastbutOne <0){
			  finWeight=1;
		  }	else if(lastweight<0 && lastbutOne >0)		  {
			  finWeight=-1;
		  }
		 ///*******///
		  if (finWeight>0){
			  bw.write("POSITIVE");
		  }else if(finWeight<0){
			  bw.write("NEGATIVE"); 
		  }else{
			  bw.write("NEUTRAL"); 
				
		  }
		  bw.newLine();
		  
		  finWeight=0;
		  }
	
		  
	  bw.close();
	  
	} catch (IOException e) {
	  e.printStackTrace();
	}
	}

}
