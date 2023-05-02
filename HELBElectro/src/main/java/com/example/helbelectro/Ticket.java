package com.example.helbelectro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private static Ticket instance;
    private String productType;
    private int charge, power, ecoScore, price;
    private static FileWriter writer;
    private static String fileName;
    static SimpleDateFormat sdfFileName, sdfFileContent;

    private Ticket() {

    }

    public static Ticket getInstance() {
        if (instance == null) {
            instance = new Ticket();
        }
        return instance;
    }

    public void registerSale(String emplacement) {
        try {
            // objets SimpleDateFormat pour les dates
            sdfFileName = new SimpleDateFormat("HHmmss");
            sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            fileName = "ticket/" + "t_" + sdfFileName.format(new Date()) + ".txt";

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
