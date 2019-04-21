
package hill.climbing;

import java.util.Arrays;
import javax.swing.*;

class test extends HillClimbing
{
    String bitstring(int num_bits)
    {
        String x="";
        for(int i=0;i<num_bits;i++)
        {
            if(Math.random()<.5)
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
        {
            if(bitstring.charAt(i)=='1')
            {
                sum+=1;
            }
        }
            return sum;
    }
    String neighbour(String bitstring)
    {
        char arr[]=new char[bitstring.length()];
        int pos=(int) (Math.random()*bitstring.length());
      
        arr=bitstring.toCharArray();
        if(arr[pos]=='1')
            arr[pos]='0';
        else
            arr[pos]='1';
         bitstring=new String(arr);
       return bitstring;
    }
    DefaultListModel d=new DefaultListModel();
    String pub;
    DefaultListModel search(int max_iterations,int num_bits)
    {
        String candidate=bitstring(num_bits);
        int cost=onemax(candidate);
        int i=0;
        while(i<max_iterations)
        {
            if(cost==num_bits)
                break;
            String neighbour=neighbour(candidate);
            int cost_nb=onemax(neighbour);
            if(cost_nb>=cost)
            {
                candidate=neighbour; 
                cost=cost_nb;
            }
            System.out.println("> Iteration:"+(i+1)+" , "+"cost: "+cost);
            d.addElement("> Iteration:"+(i+1)+" , "+"cost: "+cost);
            i++;
        }
        System.out.println("> Iteration:"+(i)+" , "+"cost: "+cost+ " , "+"best: "+candidate);
        pub="> Iteration:"+(i)+" , "+"cost: "+cost+ " , "+"best: "+candidate;
        return d;
    }
    
    public String save()
    {
        return pub;
    }
    
}
public class HillClimbing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       new hill().setVisible(true);
        
        
        
    }
    
}
