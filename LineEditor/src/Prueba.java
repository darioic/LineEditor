import java.util.concurrent.TimeUnit;

public class Prueba {


    
   public static void main(String[] args) {
    
      
   
   String s = "abcde";
   System.out.println("Iniciio = "+s);

   int cursor = 2;
   char car = 'z';
   s =s.substring(0, cursor-1)+(char)car+ s.substring(cursor);
   String s1 = s.substring(0, cursor-1);
   System.out.println("1 PARTE= "+s1);
   String s2 = s.substring(cursor-1);
   System.out.println("2 PARTE = "+s2);
   System.out.println("FINAL = "+s);
   
              


   
    

   }
    
}
