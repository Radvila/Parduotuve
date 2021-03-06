package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));

        printMenu();

        while (true) {
            try {
                ch = Integer.parseInt(bufRead.readLine());
                switch (ch) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        addDarbuotojas(bufRead, db);
                        break;
                    case 2:
                        addParduotuve(bufRead, db);
                        break;
                    case 3:
                        addSandelis(bufRead, db);
                        break;
                    case 4:
                        addTiekejas(bufRead, db);
                        break;
                    case 5:
                        updatePosition(bufRead, db);
                        break;
                    case 6:
                        updateTransportasAdr(bufRead, db);
                        break;
                    case 7:
                        findDarbuotojasByPard(bufRead, db);
                        break;
                    case 8:
                        findSandelioTiekejai(bufRead, db);
                        break;
                    case 9:
                        removePreke(bufRead, db);
                        break;
                    case 10:
                        removeDarbuotojas(bufRead, db);
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
        System.out.println("Naujo darbuotojo duomenys");

        try {
            System.out.println("Iveskite naujo darbuotojo varda");
            vardas = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo pavarde");
            pavarde = bufRead.readLine();
            System.out.println("Iveskite naujo darbuotojo ID");
            id = Integer.parseInt(bufRead.readLine());
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
                        + result.get(i).get(2) + " " + result.get(i).get(3) + " " + result.get(i).get(4) +
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

    private void updateTransportasAdr(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();
        List<List> extra = new LinkedList<List>();
        try {
            result = db.queryDb("SELECT * FROM inma3638.transportas;");
            System.out.println("Transportas:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " "
                        + result.get(i).get(2));
            }
            System.out.println("Iveskite valst. nr.:");
            String nr = bufRead.readLine();
            extra = db.queryDb("SELECT * FROM inma3638.sandelis;");
            System.out.println("Sandeliai:");
            for (int i = 0; i < extra.size(); i++){
                System.out.println((String) extra.get(i).get(1));
            }
            System.out.println("Iveskite nauja adresa:");
            String adr = bufRead.readLine();
            result = db.queryDb("UPDATE inma3638.transportas SET S_Adr = " + adr
                    + " WHERE Valst_nr = '" + nr + "';");
        } catch (Exception e) {
            System.out.println("Error updating address: " + e.getMessage());
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
                    + "WHERE Darbuotojas.Pard_Nr = Parduotuve.Nr AND Nr = '" + bufRead.readLine()
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

    private void findSandelioTiekejai(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();

        try {
            result = db.queryDb("SELECT * FROM inma3638.Sandelis;");

            System.out.println("Sandelis");
            for (int i = 0; i < result.size(); i++) {
                System.out.println(
                        (String) result.get(i).get(0) + " " + result.get(i).get(1));
            }

            System.out.println("Iveskite sandelio adresa");

            result = db.queryDb("SELECT * FROM inma3638.Tiekejas, "
                    + "WHERE Tiekejas.Tiek_ID = Tiekia.Tiek_ID AND Tiekia.S_Adr = '" + bufRead.readLine()
                    + "'");

            if (result.isEmpty()) {
                System.out.println("Tokio sandelio nera arba i sandelys neturi tiekeju");
            } else {
                System.out.println("Sandelio tiekejai");
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

    private void removePreke(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();


        try {
            result = db.queryDb("SELECT * FROM inma3638.preke;");

            System.out.println("Prekes:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(2));
            }

            System.out.println("Iveskite istrinamos prekes koda:");

            result = db.queryDb("DELETE FROM inma3638.Preke WHERE Kodas = '" + Integer.parseInt(bufRead.readLine() + "';"));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeDarbuotojas(BufferedReader bufRead, SQL db) {
        List<List> result = new LinkedList<List>();


        try {
            result = db.queryDb("SELECT * FROM inma3638.Darbuotojas;");

            System.out.println("Darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println(
                        (String) result.get(i).get(0) + " " + result.get(i).get(1) + " " + result.get(i).get(2)
                                + " " + result.get(i).get(3) + " " + result.get(i).get(4));
            }

            System.out.println("Iveskite atleidziamo darbuotojo ID:");

            result = db.queryDb("DELETE FROM inma3638.Darbuotojas WHERE ID = '" + Integer.parseInt(bufRead.readLine() + "';"));

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
        System.out.println("5. Atnaujinti darbuotojo pareigas");
        System.out.println("6. Atnaujinti transporto info");
        System.out.println("7. Darbuotojai pagal parduotuves");
        System.out.println("8. Sandelio tiekejai");
        System.out.println("9. Panaikinti preke");
        System.out.println("10. Panaikinti darbuotoja");
    }
}