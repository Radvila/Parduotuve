package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UI {

    public int ch;
    public int id;
    public String vardas;
    public String pavarde;
    public String pareigos;
    public String tipas;
    public String valstybinisNr;
    public String pavadinimas;
    public String tiekiaIki;
    public String adresas;
    public String sandelioAdr;
    public int parduotuvesNr;
    public int kaina;
    public int kodas;
    public int kvadratura;
    public int nr;

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
            id = Integer.parseInt(bufRead.readLine())
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
        System.out.println("Naujos parduotuves duomenys");

        try {
            System.out.println("Iveskite naujos parduotuves nr. ");
            nr = Integer.parseInt(bufRead.readLine());
            System.out.println("Iveskite naujos parduotuves adresa");
            adresas = bufRead.readLine();
            System.out.println("Iveskite naujos parduotuves kvadratura ");
            kvadratura = Integer.parseInt(bufRead.readLine());


            db.queryDb("INSERT INTO inma3638.Parduotuve VALUES('"+nr+"','"+ adresas +"', '"+ kvadratura +"');");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addSandelis(BufferedReader bufRead, SQL db){
        System.out.println("Naujo sandelio duomenys");

        try {
            System.out.println("Iveskite naujo sandelio tipa");
            kvadratura = Integer.parseInt(bufRead.readLine());
            System.out.println("Iveskite naujo sandelio adresa");
            sandelioAdr = bufRead.readLine();
            System.out.println("Kuriai parduotuvei tiekia");
            parduotuvesNr = Integer.parseInt(bufRead.readLine());

            db.queryDb("INSERT INTO inma3638.Sandelis VALUES('"+ tipas +"','"+ sandelioAdr +"');");
            db.queryDb("INSERT INTO inma3638.gauna VALUES('"+ sandelioAdr +"','"+ parduotuvesNr +"');");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTiekejas(BufferedReader bufRead, SQL db){
        System.out.println("Naujo tiekejo duomenys");

        try {
            System.out.println("Iveskite tiekejo pavadinima");
            pavadinimas = bufRead.readLine();
            System.out.println("Iveskite tiekejo adresa");
            adresas = bufRead.readLine();
            System.out.println("Iveskite tiekejo ID");
            id = Integer.parseInt(bufRead.readLine());
            System.out.println("Iveskite iki kada tiekia");
            tiekiaIki = bufRead.readLine();
            System.out.println("Iveskite sandelio adresa");
            sandelioAdr = bufRead.readLine();

            db.queryDb("INSERT INTO inma3638.Tiekejas VALUES('"+pavadinimas+"','"+adresas+"','"+id+"','"+tiekiaIki+"');");
            db.queryDb("INSERT INTO inma3638.tiekia VALUES('"+ sandelioAdr +"','"+id+"');");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updatePosition(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();
        try {
            result = db.queryDb("SELECT * FROM inma3638.darbuotojas;");
            System.out.println("Darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(1) + " "
                        + result.get(i).get(2) + " " + result.get(i).get(3)) + " " + result.get(i).get(4) +
                        " " + result.get(i).get(5));
            } System.out.println("Iveskite darbuotojo ID");
            int id = Integer.parseInt(bufRead.readLine());
            System.out.println("Iveskite naujas pareigas:");
            String pareigos = bufRead.readLine();
            result = db.queryDb("UPDATE inma3638.Darbuotojas SET Pareigos = " + pareigos
                    + " WHERE ID = '" + id + "';");
        } catch (Exception e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }

    private void findDarbuotojasByPard(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();

        try {
            result = db.queryDb("SELECT * FROM inma3638.Parduotuve;");

            System.out.println("Parduotuves");
            for (int i = 0; i < result.size(); i++) {
                System.out.println(
                        (String) result.get(i).get(0) + " " + result.get(i).get(1) + " " + result.get(i).get(2));
            }

            System.out.println("Iveskite parduotuves numeri");

            result = db.queryDb("SELECT * FROM inma3638.Darbuotojas, "
                    + "WHERE Darbuotojas.Pard_Nr = Parduotuve.Nr AND AK = '" + bufRead.readLine()
                    + "'");

            if (result.isEmpty()) {
                System.out.println("Tokios parduotuves nera arba ji neturi darbuotoju");
            } else {
                System.out.println("Parduotuves darbuotojai");
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(
                            (String) result.get(i).get(0) + " " + result.get(i).get(1) + " " + result.get(i).get(2)
                                    + " " + result.get(i).get(4));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printMenu(){
        System.out.println("Pasirinkite norima operacija:");
        System.out.println("1. Prideti nauja darbuotoja");
        System.out.println("2. Prideti nauja parduotuve");
        System.out.println("3. Prideti nauja sandeli");
        System.out.println("4. Prideti nauja tiekeja");
    }
}