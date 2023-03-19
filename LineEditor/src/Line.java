public class Line {

   public String lin;

   public Line(){

      lin = "";

   }

   public String backspace(int cursor){

    if(cursor>1){

        lin = lin.substring(0, cursor-2) + lin.substring(cursor-1);

    }

   return lin;

   }

   public String delete(int cursor){

      if(cursor>1){
  
          lin = lin.substring(0, cursor-1) + lin.substring(cursor);
  
      }
  
     return lin;
  
     }

   public String addCharacter(int car, int cursor){

    lin = lin.substring(0, cursor-1)+(char)car+ lin.substring(cursor-1);

    return lin;

   }
   public String modifyCharacter(int car, int cursor){

      lin = lin.substring(0, cursor-1)+(char)car+ lin.substring(cursor); 

      return lin;

   }


}
