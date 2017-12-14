package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UI {

    public int pasirinkimas = 1;
    public String id;
    public String vardas;
    public String pavarde;
    public String pareigos;
    public String tipas;
    public String valstybinisNr;
    public String pavadinimas;
    public String tiekiaIKI;
    public int kaina;
    public int kodas;
    public int kvadratura;
    public int nr;

    public void start() {
        SQL db = new SQL();
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));

        //atspausdinti pasirinkimus

        while (pasirinkimas != 0) {
            try {
                System.out.print(">");
                pasirinkimas = Integer.parseInt(bufRead.readLine());
                switch (pasirinkimas) {
                    case 0:
                        break;
                    case 1:
                        addDarbuotojas(bufRead, db);
                        break;
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
        System.out.println("Iveskite naujo darbuotojo asmens koda, varda,"
                + " pavarde, pareigas, atlyginima, darbo pradzios data");

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

            db.queryDb("INSERT INTO rasi3530.Darbuotojas VALUES('"+vardas+"','"+pavarde+"', '"+id+"', '"+pareigos+"', '"+nr+"');");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}