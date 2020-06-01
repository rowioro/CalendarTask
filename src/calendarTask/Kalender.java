package calendarTask;

import java.util.Scanner;

public class Kalender {
    
    public static void main(String[] args) {
     
        Termin terminNew = new Termin();
        String zeichen;
        for(;;){
            System.out.print("Möchten Sie einen Termin antragen (y/n)? ");
            Scanner scanner = new Scanner(System.in);
            zeichen = scanner.nextLine();
            if (zeichen.equals("y")){
            terminNew.terminAntragen();
            }
            else
                break;
        }
        terminNew.zeigeKalender();
        terminNew.zeigeTagTermine();
    }
    
}
