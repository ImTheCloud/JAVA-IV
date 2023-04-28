package com.example.helbelectro.Factory;

import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
public class Ticket {

    private String productType;
    private int charge,power,ecoScore,price;
    private static FileWriter writer;
    private static String fileName;
    static SimpleDateFormat sdfFileName,sdfFileContent;

    public static void enregistrerVente(String emplacement) {
        try {
            // objets SimpleDateFormat pour les dates
            sdfFileName = new SimpleDateFormat("HHmmss");
            sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            fileName = "ticket/" + "t_"+sdfFileName.format(new Date()) + ".txt";

            // crée un objet File à partir du nom
            File fileInTicket = new File(fileName);
            // ecrire dans le fichier
            writer = new FileWriter(fileInTicket, true);
            writer.write("Date: " + sdfFileContent.format(new Date()) + "\n");
            writer.write("Vente du produit :" + emplacement + "\n");
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
