package genetic__algorithm;

import javax.swing.DefaultListModel;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
////////////////////////
class test extends Genetic_algorithm
{
    static ArrayList<Integer> list=new ArrayList<>();
  
     String bitstring(int len)
     {
         String x="";
         for(int i=0;i<len;i++)
         {

          if(Math.random()>.5)
              x+="1";
          else
              x+="0";
          
         }
         return x;
     }
     int onemax(String bitstring)
     {
         int sum=0;
         for(int i=0;i<bitstring.length();i++)
             if(bitstring.charAt(i)=='1')
                 sum+=1;
         return sum;
     }
     int sort(int arr[])
     {
         int max=arr[0];
         int temp=0;
         for(int i=0;i<arr.length;i++)
         {
             if(arr[i]>max)
             { 
                 max=arr[i];
                 temp=i;
             }
             
                 
         }
         return temp;
     }
     
     DefaultListModel[] search(int Max,int len,int length,double p_crossover,double p_mutation)
     {
         DefaultListModel st[]=new DefaultListModel[2];
         String population[]=new String[length];
         int fitness[]=new int[length];
         
         String selected[]=new String[length];
         String children[]=new String[length];
         String best;
           DefaultListModel d=new DefaultListModel();
           DefaultListModel s=new DefaultListModel();
         list.removeAll(list);
         
         int temp,test,check,gen=0,b_;
         for(int i=0;i<length;i++)
         {
             population[i]=bitstring(len);
             fitness[i]=onemax(population[i]);
            
         }
          temp=sort(fitness);
          best=population[temp]; 
          b_=fitness[temp];
          check=fitness[temp];
          
         for(int i=0;i<length;i++)
         {
           
             d.addElement(population[i]+"--->"+fitness[i]);
             
             System.out.println(">"+population[i]+"-->"+fitness[i]);
         }
          System.out.println("the best:"+best);
          System.out.println("-----------after--------------");
         while(gen<=Max)
         {
             if(b_==len)
             break;
            for(int i=0;i<length;i++)
            {
            selected[i]=binary_tournament(population, fitness);
            System.out.println(">"+selected[i]+"-->"+fitness[i]);
            }

            //---------------------------------------------
            children=reproduce(selected, length, p_crossover, p_mutation);
            for(int i=0;i<length;i++)
            fitness[i]=onemax(children[i]);

           test=sort(fitness);
          

            System.out.println("======================after crossover and  mutation=========================");
            for(int i=0;i<length;i++)
            {
                
                d.addElement(children[i]+"--->"+fitness[i]);
                System.out.println(">"+children[i]+"-->"+fitness[i]);
            }
            d.addElement("-------------------");
            
            System.out.println("-----------------------------------------------");
             if(fitness[test]>check)
             {
                best=children[test];
                b_=fitness[test];
                check=fitness[test];
             }
             population=children;
            System.out.println("the best:"+best);
             System.out.println("======================Result=========================");
             System.out.println("gen:"+gen+" best :"+b_+" ,"+best);
             s.addElement("------------best---------");
             s.addElement("gen: "+gen+" fitness: "+b_+" best "+best);
             list.add(b_);
       
            gen++;
         }
         st[0]=d;
         st[1]=s;
     
         return st;
     }
     
      void save()
      {
         
        DefaultCategoryDataset  s=new DefaultCategoryDataset();
     
        for(int i=0;i<list.size();i++)
        {
        s.setValue(list.get(i), "test", " gen:"+i);
        }
     
        
        JFreeChart ch=ChartFactory.createBarChart("gens Iterations", "gens", "best : fitness", s,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot p=ch.getCategoryPlot();
        p.setRangeGridlinePaint(Color.blue);
        ChartFrame f=new ChartFrame("Bar chart for Gens", ch);
        f.setVisible(true);
        f.setSize(1000,700);
 
      }
     DefaultListModel[] search(int Max,int len,int length,double p_crossover)
     {
         return search(Max,len,length,p_crossover,1.0/len);
     }
    String binary_tournament(String pop[],int fitness[])
     {
         
         int  i=(int)(Math.random()*pop.length);
         int  j=(int)(Math.random()*pop.length);
         while(i==j)
             j=(int)(Math.random()*pop.length);
       
         if(fitness[i]>fitness[j])
             return pop[i];
         else
             return pop[j];
               
     }
    
    String point_mutation(String bitstring,double rate)
    {
        String child="";
        for(int i=0;i<bitstring.length();i++)
        {
           char ch=bitstring.charAt(i);
           
           if(Math.random()<rate)
           {
               if(ch=='1')
                   child+="0";
               else
                   child+="1";
               
           }
           else
               child+=ch;
            
        }
        return child;
 
    }
    String point_mutation(String bit)
    {
        
        return point_mutation(bit,  (1.0/bit.length())  );
    }
    String crossover(String parent1,String parent2,double rate)
    {
        if(Math.random()>=rate)
            return parent1;
        else
        {
        int mid=(int) (1+Math.random()*parent1.length()-2);
        String g=parent1.substring(0,mid)+parent2.substring(mid);
        return g;
            
        }
    }
 
    
    String[] reproduce(String selected[], int pop_size, double p_cross,double p_mutation)
    {
        String children[]=new String [pop_size];
      
            for(int i=0;i<pop_size;i++)
            {
                String p1,p2;
                p1=selected[i];
                if(i==pop_size-1)
                    p2=selected[0];
                else if(i%2==0)
                    p2=selected[i+1];
                else
                    p2=selected[i-1];
                String child_=crossover(p1, p2, p_cross);
                String child=point_mutation(child_,p_mutation);
                children[i]=child;
            }
          return children;
    }
    
}

public class Genetic_algorithm {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       
   
//       test t=new test();
//         t.search(100, 64,100,.98,1.0/64);
        
       new genetic().setVisible(true);
        
       
        

    }
    
}