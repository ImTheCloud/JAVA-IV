package com.example.helbelectro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private String productType;
    private int charge, power, ecoScore, price;;

    public static void registerSale(String typeProduct, int price, String ecoScore) {
        try {
            // objets SimpleDateFormat pour les dates
            SimpleDateFormat sdfFileName = new SimpleDateFormat("HHmmss");
            SimpleDateFormat sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String fileName = "t_" + sdfFileName.format(new Date()) + ".txt";

            // crée un objet File à partir du nom
            File fileInTicket = new File(fileName);
            // ecrire dans le fichier

            FileWriter writer = new FileWriter(fileInTicket, true);
            writer.write("------------------------------------------------\n");
            writer.write("Date: " + sdfFileContent.format(new Date()) + "\n");
            writer.write("Type  produit:" + typeProduct + "\n");
            writer.write("Prix:" + price + "\n");
            writer.write("Eco-Score :" + ecoScore + "\n");

//            writer.write("Charge :" + emplacement + "\n");
//            writer.write("Portée :" + emplacement + "\n");
//            writer.write("Couleur :" + emplacement + "\n");
//            writer.write("Puissance :" + emplacement + "\n");

            writer.write("------------------------------------------------\n");

            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
