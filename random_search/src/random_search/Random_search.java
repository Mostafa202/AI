
package random_search;

import javax.swing.DefaultListModel;

class test
{
    
    int [][]search_space(int prob,int from,int to)
    {
        int ch[][]=new int [prob][2];
        for(int i=0;i<prob;i++)
        {
            ch[i][0]=from;
            ch[i][1]=to;
        }
       return ch; 
    }
    double []random_vector(int minmax[][])
    {
        double candidate[]=new double [minmax.length];

       for(int i=0;i<minmax.length;i++)
       {
         candidate[i]=minmax[i][0] + ((minmax[i][1] - minmax[i][0]) * Math.random());

       }
    return candidate;
    
    }
    double objective(double vector[])
    {
        double sum=0;
       for(int i=0;i<vector.length;i++)
            sum+=vector[i]*vector[i];
        return sum;
    }
    double cost;
    DefaultListModel d=new DefaultListModel();
    DefaultListModel p=new DefaultListModel();
    double[] search(int max,int prob,int from ,int to)
    {
        int [][] search_space;
        search_space=search_space(prob, from, to);
        double best[]=new double[prob];
        double best_cost=0;
        for(int i=0;i<max;i++)
        {
            double candidate[]=random_vector(search_space);
            double candidate_cost=objective(candidate);
            if((i==0)||(candidate_cost<best_cost))
            {
                best=candidate;

                best_cost=candidate_cost;
            }
            d.addElement("iteration "+(i+1)+" best=>cost :"+best_cost);
        }
        cost=best_cost;
        
        return best;
    }

    public DefaultListModel costs()
    {
        return d;
    }
    public DefaultListModel numbers(int max,int prob,int from ,int to)
    {
       double arr[]=search(max,prob,from,to);
       for(int i=0;i<arr.length;i++)
       {
           p.addElement(arr[i]);
       }
           p.addElement("----------------------------");
           p.addElement(" cost: "+cost);
        return p;
    }
   
    
    
}
public class Random_search {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
       new random().setVisible(true);
        
        
    }
    
}
