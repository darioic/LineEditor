import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    public static final int ARROW_RIGHT = 1000;
    public static final int ARROW_LEFT = 1001;
    public static final int HOME = 1002;
    public static final int END = 1003;
    public static final int INS = 1004;
    public static final int DEL = 1005;

    public static final int ENTER =13;
    public static final int ESC = 27;
    public static final int BACKSPACE = 127;
    public  int tecla;
    private Line line;
    public Reader r;
    public Console console;




    public EditableBufferedReader(Reader in, int sz) {
        super(in, sz);
        line = new Line();
        r = in;
        console = new Console();
    }


    public void setRaw() {
        String[] rw = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        try {
            Runtime.getRuntime().exec(rw);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void unsetRaw() {
        String[] rw = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
        try {
            Runtime.getRuntime().exec(rw);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public int read() throws IOException { 

        setRaw();

        int key = super.read();

        if(key != '\033'){

            tecla = key;
           
        }else{
            
           int key2 = super.read();
           if(key2 != '['){

              tecla = ESC;
           
            }else{
               int key3 = super.read();

               switch(key3) {

                  case 'C':
                      tecla = ARROW_RIGHT;
                      break;
                      

                  case 'D':
                      tecla = ARROW_LEFT;
                      break; 

                  case 'F':
                      tecla = END;
                      break;                 
                  case 'H':
                      tecla = HOME;
                      break;

               }
               if(key3 =='3' || key3 =='4' || key3 == '7' || key3 == '8') {
                  int key4 = super.read();
                  if (key4 == '~') {

                     switch (key3) {

                        case '2':
                           tecla = INS;
                           break;

                        case '3':
                           tecla = DEL;
                           break;

                        case '4':
                           tecla = END;
                           break;

                        case '8':
                            tecla = END;
                            break;

                        case '7':
                            tecla = HOME;
                            break;


                        }

                    }
                }
            }
        }
        return tecla;

    }
    public String readLine() {     


        try {

            int cursor = 1;
            System.out.print("\033[20h");
            while(true){

              
                tecla = read();
                if(tecla == ENTER){

                    break;

                }else if(tecla == DEL){

                    
                    line.delete(cursor);
                    if(cursor>1) cursor--;

                
                }else if(tecla == BACKSPACE){

                   line.backspace(cursor);
                   if(cursor >1) cursor--;

                }else if(tecla == ARROW_LEFT){

                    if(cursor>1){

                        cursor--;

                    }
     
     
                }else if(tecla == ARROW_RIGHT){

                    if(cursor<line.lin.length()){

                        cursor++;

                    }
     
                
     
                }else if(tecla == HOME){

                    cursor = 1;
     
     
                }else if(tecla == END){
     
                    cursor = line.lin.length();
     
                }else if(tecla == INS){

                    if(contador%2!=0){

                       add = true; 

                    }else add=false;  

                }else{

                    if(add){

                        line.addCharacter(tecla, cursor);
                        
                    }else{

                        line.modifyCharacter(tecla, cursor);
                    }

                    cursor++;                

                }
                console.refreshScreen(line, cursor);
     
     
             }

            
             unsetRaw();
             return line.lin;
     
        } catch (IOException IO) {

            System.err.println("IOException");

        }
        
        return "";
    } 
    

    public static void main(String[] args) throws IOException {

        Reader r = new InputStreamReader(System.in);
        EditableBufferedReader buf= new EditableBufferedReader(r, 1000);
        buf.readLine();
        
    }
    
}
