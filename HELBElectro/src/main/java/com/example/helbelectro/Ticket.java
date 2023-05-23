package com.example.helbelectro;

import com.example.helbelectro.conponent.ComponentBattery;
import com.example.helbelectro.conponent.ComponentElectricMotor;
import com.example.helbelectro.conponent.ComponentMotionSensor;
import com.example.helbelectro.product.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private static Ticket instance;

    // constructeur prive pour empecher l'instanciation directe
    Ticket() {
    }

    // methode statique pour obtenir l'instance unique du singleton
    public static Ticket getInstance() {
        if (instance == null) {
            instance = new Ticket();
        }
        return instance;
    }

    // methode pour la generation de ticket
    public void registerSale(Product typeProduct, int price, String ecoScore) {
        try {
            SimpleDateFormat sdfFileName = new SimpleDateFormat("HHmmss");
            SimpleDateFormat sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String fileName = "t_" + sdfFileName.format(new Date()) + ".txt";

            File fileInTicket = new File(fileName);
            FileWriter writer = new FileWriter(fileInTicket, true);
            writer.write("------------------------------------------------\n");
            writer.write("Date: " + sdfFileContent.format(new Date()) + "\n");
            writer.write("Type  produit: " + typeProduct.getClass().getSimpleName() + "\n");
            writer.write("Prix:" + price + "\n");
            writer.write("Eco-Score :" + ecoScore + "\n");
            getProductAttributes(typeProduct, writer);
            writer.write("------------------------------------------------\n");
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // methode pour verifier quel type de produit va devoir etre ecrit dans le ticket pour donner les attribut exact
    // par exemple pour le Sensor on va ajouter la range et la couleur
    // c'est pour ca que je verifie quel type de produit c'est afin de generer le ticket correctement
    // le drone aura par exemple les 3 methodes d'attribut car il a les 3 composants
    // le tout est ajouter dans le ticket
    private  void getProductAttributes(Product product, FileWriter writer) throws IOException {
        if (product instanceof ProductMotionSensor) {
            addSensorAttributes(writer);
        } else if (product instanceof ProductBattery) {
            addBatteryAttributes(writer);
        } else if (product instanceof ProductElectricMotor) {
            addMotorAttributes(writer);
        } else if (product instanceof ProductMonitoringDrone) {
            addSensorAttributes(writer);
            addBatteryAttributes(writer);
            addMotorAttributes(writer);
        } else if (product instanceof ProductRemoteCar) {
            addBatteryAttributes(writer);
            addMotorAttributes(writer);
        } else if (product instanceof ProductSecurityAlarm) {
            addBatteryAttributes(writer);
            addSensorAttributes(writer);
        } else if (product instanceof ProductTrackingRobot) {
            addMotorAttributes(writer);
            addSensorAttributes(writer);
        }
    }

    private void addSensorAttributes(FileWriter writer) throws IOException {
        String range = ComponentMotionSensor.getRange();
        String colorSensor = ComponentMotionSensor.getColorSensor();
        writer.write("Sensor Range: " + range + "\n");
        writer.write("Color Sensor: " + colorSensor + "\n");
    }

    private void addBatteryAttributes(FileWriter writer) throws IOException {
        String load = ComponentBattery.getLoad();
        writer.write("Battery Load: " + load + "\n");
    }

    private void addMotorAttributes(FileWriter writer) throws IOException {
        String power = ComponentElectricMotor.getPower();
        writer.write("Motor Power: " + power + "\n");
    }
}
