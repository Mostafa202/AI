
package simulated_annealing;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

class test
{
    
    int[]random_permutation(int cities[][])
    {
        int arr[]=new int[cities.length];
        
      
        for(int i=0;i<cities.length;i++)
            arr[i]=i;

        for(int i=0;i<cities.length;i++)
        {
           
            int r = (int) (Math.random()*cities.length-i)+i;
           
            int temp=arr[r];
            arr[r]=arr[i];
            arr[i]=temp;
            
        }
        
        return arr;
    }
    double euc_2d(int c1[],int c2[])
    {
        return (Math.round(Math.sqrt(((c1[0] - c2[0])*(c1[0] - c2[0])) + ((c1[1] - c2[1])*(c1[1] - c2[1])))));
    }
    
double cost(int permutation[], int cities[][])
{
    double distance =0;
    for(int i=0;i<cities.length;i++)
    {
         int c1=permutation[i];
         int c2 = (i==permutation.length-1) ? permutation[0] : permutation[i+1];
         distance+=euc_2d(cities[c1],cities[c2]);
       
    }
    return distance;
}
boolean search(ArrayList<Integer>arr,int target)
{
    for(int i=0;i<arr.size();i++)
    {
        if(arr.get(i)==target)
            return true;
    }
    return false;
    
}
int []stochastic_two_opt(int perm[])
{
    ArrayList <Integer>exclude=new ArrayList();
    int c1=(int) (Math.random()*perm.length);
    int c2=(int) (Math.random()*perm.length);
    exclude.add(c1);
    if(c1==0)
        exclude.add(perm.length-1);
    else
        exclude.add(c1-1);
    if(c1==perm.length-1)
        exclude.add(0);
    else
        exclude.add(c1+1);

    while(search(exclude, c2))
        c2=(int) (Math.random()*perm.length);
   if(c2<c1)
   {
       int temp=c1;
       c1=c2;
       c2=temp;
       
   }
   
    ArrayList<Integer>l=new ArrayList<>();
   for(int i=c2-1;i>=c1;i--)
   {
       l.add(perm[i]);
   }
   for(int i=c1,j=0;i<c2;i++,j++)
       perm[i]=l.get(j);
    
  return perm;  
    
    
}

int[] create_neighbour(int current[],int cities[][])
{
    int candidate[]=new int[current.length];
       for(int i=0;i<candidate.length;i++)
      candidate[i]=current[i];

     candidate=stochastic_two_opt(candidate);
   
     return candidate;

}

boolean should_accept(int candidate[],int current[],double temp,int cities[][])
{
    if(cost(candidate, cities) <= cost(current,cities))
    return  true;
 
    else return Math.exp(((cost(current,cities) - cost(candidate,cities) / temp)))>Math.random();
 
    
}
    public double best_cost;
    DefaultListModel d=new DefaultListModel();
    
   DefaultListModel search(int cities[][],int max_itr,double max_temp,double temp_change)
   {
     int current_vector[]=new int[cities.length];
     int best[]=new int[cities.length];
     int candidate[]=new int[cities.length];
     current_vector=random_permutation(cities);
     double current_cost=cost(current_vector, cities);
     double temp=max_temp;
     best=current_vector;
    for(int i=0;i<max_itr;i++)
    {
        candidate =create_neighbour(current_vector, cities);
        temp*=temp_change;
        if(should_accept(candidate, current_vector, temp, cities))
            current_vector=candidate;
        if(cost(candidate, cities)<cost(best, cities))
            best=candidate;
        
        if((i+1)%10==0)
        {
            d.addElement(">iteration: "+ (i+1)+" temp: "+temp+ " best: "+cost(best, cities));
            best_cost=cost(best,cities);
            
        }
     
    }
         
    best_=best;
    return d;
}
   
   int size;
   void set(int s)
   {
       size=s;
   }
   int best_[]=new int[size];
   int[]best()
   {
       return best_;
   }

   
   
  
  


    
}
public class Simulated_annealing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       

       new simulated().setVisible(true);
        
        
       
        

        
    }
    
}
