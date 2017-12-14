package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UI {

    public int ch;
    public String id;
    public String vardas;
    public String pavarde;
    public String pareigos;
    public String Tipas;
    public String valstybinisNr;
    public String pavadinimas;
    public String tiekiaIKI;
    public String Adresas;
    public String S_Adr;
    public int kaina;
    public int kodas;
    public int Kvadratura;
    public int Nr;

    public void start() {
        SQL db = new SQL();
        //BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));
        choice = Integer.parseInt(bufRead.readLine());

        printMenu();

        while (1) {
            try {
                ch = Integer.parseInt(bufRead.readLine());
                switch (ch) {
                    case 0:
                        System.Exit(0);
                        break;
                    case 1:
                        addDarbuotojas(bufRead, db);
                        break;
                    case 2:
                        addParduotuve(bufRead, db);
                    case 3:
                        addSandelis(bufRead, db);
                    case 4:
                        addTiekejas(bufRead, db);
                    default:
                        System.out.println("Blogas pasirinkimas");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Klaida skaitant ivesti");
            } catch (NumberFormatException e) {
                System.out.println("Netinkamas ivesties formatas");
            }
        }
    }

    public void addDarbuotojas(BufferedReader bufRead, SQL db) {
        System.out.println("Naujo darbuotojo duomenys");

        try {
            System.out.println("Iveskite naujo darbuotojo varda");
            vardas = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo pavarde");
            pavarde = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo ID");
            id = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo pareigas");
            pareigos = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo parduotuves numeri");
            nr = Integer.parseInt(bufRead.readLine());

            db.queryDb("INSERT INTO inma3638.Darbuotojas VALUES('"+vardas+"','"+pavarde+"', '"+id+"', '"+pareigos+"', '"+nr+"');");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addParduotuve(BufferedReader bufRead, SQL db){
        System.out.printline("Naujos parduotuves duomenys");

        try {
            System.out.println("Iveskite naujos parduotuves nr. ");
            Nr = Integer.parseInt(bufRead.readLine());
            System.out.println("Iveskite naujos parduotuves adresa");
            Adresas = bufRead.readLine();
            System.out.println("Iveskite naujos parduotuves kvadratura ");
            Kvadratura = Integer.parseInt(bufRead.readLine());


            db.queryDb("INSERT INTO inma3638.Parduotuve VALUES('"+Nr+"','"+Adresas+"', '"+Kvadratura+"');");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addSandelis(BufferedReader bufRead, SQL db){
        System.out.printline("Naujo sandelio duomenys");

        try {
            System.out.println("Iveskite naujo sandelio tipa");
            Kvadratura = bufRead.readLine();
            System.out.println("Iveskite naujo sandelio adresa");
            S_Adr = bufRead.readLine();
            System.out.println("Kuriai parduotuvei tiekia");
            Pard_Nr = Integer.parseInt(bufRead.readLine());

            db.queryDb("INSERT INTO inma3638.Sandelis VALUES('"+Tipas+"','"+S_Adr+"');");
            db.queryDb("INSERT INTO inma3638.gauna VALUES('"+S_Adr"','"Pard_Nr"'););

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printMenu(){
        System.out.printline("Pasirinkite norima operacija:");
        System.out.printline("1. Prideti nauja darbuotoja");
        System.out.printline("2. Prideti nauja parduotuve");
        System.out.printline("3. Prideti nauja sandeli");
        System.out.printline("4. Prideti nauja tiekeja");
    }
}