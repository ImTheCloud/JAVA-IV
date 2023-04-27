package com.example.helbelectro;

import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
public class Ticket {

    private Date date;
    private String productType;
    private double price;
    private int ecoScore;
    private int charge;
    private int power;

        public static void enregistrerVente(String emplacement) {
            try {
                SimpleDateFormat sdfFileName = new SimpleDateFormat("HHmmss");
                SimpleDateFormat sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String fileName = "ticket/" + sdfFileName.format(new Date()) + ".txt";
                File fileInTicket = new File(fileName);

                // ecrit dans le fichier
                FileWriter writer = new FileWriter(fileInTicket, true);
                writer.write("Date: " + sdfFileContent.format(new Date()) + "\n");
                writer.write("Vente du produit de l'emplacement " + emplacement + "\n");
                writer.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
