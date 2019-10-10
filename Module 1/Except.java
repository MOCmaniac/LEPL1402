
import java.util.ArrayList;
import java.util.List;

public class Except{
    
    
    public static void outof(){
        int[] error = new int[5];
        error[-2] = 42;
    }
    
    public static void concurr(){
        List<Integer> error = new ArrayList<Integer>();
        error.add(1);
        error.add(2);
        error.add(3);
        error.add(4);
        error.add(42);
        for (Integer i : error) {
            if(i.equals(3)){
                error.remove(i);
            }
        }
    }
    
    public static void nullpointer(){
        Integer i = null;
        i.toString();
    }
    
    
    
}