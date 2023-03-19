public class Console {

    public void refreshScreen(Line line, int cursor) {
        
        System.out.print("\033[2K\033[2;1H");
        System.out.print(line.lin);
        System.out.print("\033[2;"+ cursor + "H");
    }

    
    
}
