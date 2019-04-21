
package adaptive_random_search;

import javax.swing.DefaultListModel;

class test{
    
    double objective_function(double arr[])
    {
        double sum=0.0;
        for(int i=0;i<arr.length;i++)
            sum+=(arr[i]*arr[i]);
        return sum;
    }
  double rand_in_bounds(double min, double max)
  {
       return min + ((max-min) * Math.random());
  }

 double[] random_vector(int minmax[][])
 {
     double arr[]=new double[minmax.length];
     for(int i=0;i<minmax.length;i++)
         arr[i]=rand_in_bounds(minmax[i][0],minmax[i][1]);
     return arr;
 }
double [] take_step(int minmax[][],double current[],double step_size)
{
    double position[]=new double[current.length];
    for(int i=0;i<position.length;i++)
    {
        double min=Math.max(minmax[i][0],(current[i]-step_size));
        double max=Math.min(minmax[i][1],(current[i]+step_size));
        position[i]=rand_in_bounds(min, max);
    }
    return position;
}

double large_step_size(int iter, double step_size, double s_factor, double l_factor, int iter_mult)
{
    if(iter>0&&(iter%iter_mult==0))
        return step_size * l_factor;
    else
        return step_size * s_factor;
    
}

 Object[] take_steps(int bounds[][],double current[],double step_size,double big_stepsize)
{
    
    double step[]=new double[current.length];
    double big_step[]=new double[current.length];
    step=take_step(bounds, current,step_size);
    big_step=take_step(bounds, current, big_stepsize);
    
   Object[]ar=new Object[2];
   ar[0]=step;
   ar[1]=big_step;
   
    
   return ar; 
    
}
    DefaultListModel d=new DefaultListModel();
Object[] search(int max_iter, int bounds[][], double init_factor, double s_factor, double l_factor, int iter_mult,
int max_no_impr)
{
    double step_size = (bounds[0][1]-bounds[0][0]) * init_factor;
    int count=0;
    double []current_vector=random_vector(bounds);
  
    for(int i=0;i<max_iter;i++)
    {
        double big_stepsize = large_step_size(i, step_size, s_factor, l_factor,
        iter_mult);
        
        Object[]o = take_steps(bounds, current_vector, step_size, big_stepsize);
        double []step=(double[])o[0];
        double big_step[]=(double[])o[1];
        
        if (objective_function(step)<= objective_function(current_vector) || objective_function(big_step) <= objective_function(current_vector))
        {
 
           if(objective_function(big_step)<=objective_function(step))
           {
               step_size=big_stepsize;
               current_vector=big_step;
                       
           }
           else
               current_vector=step;
           count=0;
        }
      
        else
        {
        count += 1;
        if(count >= max_no_impr)
        {
            count=0;
            double stepsize=(step_size/s_factor);
        }
    
       }
        d.addElement(">iterations > "+(i+1)+" best : "+objective_function(current_vector));
           System.out.println(">iterations > "+(i+1)+" best : "+objective_function(current_vector));
    }
    Object []o=new Object[3];
    o[0]=d;
    o[1]=current_vector;
    o[2]=objective_function(current_vector);
    return o;
    
    
}

    
}
public class Adaptive_random_search {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new adaptive().setVisible(true);
    }
    
}
