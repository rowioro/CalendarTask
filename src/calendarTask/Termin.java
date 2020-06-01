package calendarTask;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Termin implements Comparable<Termin>{
    
    public LocalDate date;
    public LocalTime time;
    public String titel;
    public String text;
    
    List <Termin> kalender = new ArrayList<>();
    
    public void terminAntragen(){
        
        Termin termin = new Termin();
        Scanner scanner = new Scanner(System.in);
        
        boolean korekteDatum = false; 
        while(korekteDatum != true){
            try{
                System.out.println("Tragen Sie bitte den Termin zum Kalender an.");
                System.out.print("Geben Sie bitte das Datum im richtigen Format (zB.: 31 12 1999) an: ");
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
                termin.date = LocalDate.parse(scanner.nextLine(), f);
                if (termin.date.minusDays(0).isBefore(LocalDate.now())) {
                    System.out.println("Falsche Datum!");
                    continue;
                }
                korekteDatum = true;
            }
            catch (DateTimeException ex) {
            System.out.println("Sie haben falschen Datum-Format angegeben!"); 
            System.out.println(ex);
            }
        }

        boolean korekteZeit = false;
        while(korekteZeit != true){
            try{
                System.out.print("Geben Sie bitte die Uhrzeit im richtigen Format (zB.: 12:00) an: ");
                termin.time = LocalTime.parse(scanner.nextLine());
                korekteZeit = true;
            }
            catch (DateTimeException ex) {
            System.out.println("Du hast falschen Zeit-Format angegeben!"); 
                System.out.println(ex);
            }
        }
        
        System.out.print("Geben Sie bitte einen Titel an: ");
        termin.titel = scanner.nextLine();
        System.out.print("Geben Sie bitte einen Text an: ");
        termin.text = scanner.nextLine();
        
        kalender.add(termin);
    }
    
    public void zeigeKalender(){
        
        Collections.sort(kalender);
        for (Termin termin : kalender) {
            System.out.println(termin.date + " - " + termin.time + " - " + termin.titel + " - " + termin.text);
        }
    }
    
    public void zeigeTagTermine(){
        
        Scanner scanner = new Scanner(System.in);
        boolean korekteDatum = false; 
        int zahlTermine = 0;
        while(korekteDatum != true){
            try{
                System.out.print("Geben Sie bitte das bevorzugte Datum im richtigen Format (zB.: 31 12 1999) an: ");
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate tag = LocalDate.parse(scanner.nextLine(), f);
                
                for (Termin termin : kalender) {
                    
                    if (termin.date.isAfter(tag.minusDays(1)) && termin.date.isBefore(tag.plusDays(1))){
                        System.out.println(termin.date + " - " + termin.time + " - " + termin.titel + " - " + termin.text);
                        zahlTermine++;
                    }
                }
                if (zahlTermine == 0){
                    System.out.println("Es gibt keine Termine an diesem Tag.");
                }
                
                
                System.out.println("Alle Termine fuer folgende Woche: ");
                for (Termin termin : kalender) {
                    
                    if (termin.date.isAfter(tag.minusDays(0)) && termin.date.isBefore(tag.plusDays(8))){
                        System.out.println(termin.date + " - " + termin.time + " - " + termin.titel + " - " + termin.text);
                        zahlTermine++;
                    }
                }
                if (zahlTermine == 0){
                    System.out.println("Es gibt keine Termine fuer die Woche, die nach dem von Ihnen gewaehlten Tag folgt.");
                }
                
                korekteDatum = true;
            }
            catch (DateTimeException ex) {
            System.out.println("Sie haben falschen Datum-Format angegeben!"); 
            System.out.println(ex);
            }
        }
    }
    
    

    @Override
    public int compareTo(Termin o) {
        
        if (this.date.compareTo(o.date) != 0){
            return this.date.compareTo(o.date);
        }
        return this.time.compareTo(o.time);
    }

}
    
    
