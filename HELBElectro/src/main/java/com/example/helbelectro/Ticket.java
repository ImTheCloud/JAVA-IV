package com.example.helbelectro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    public static void registerSale(Product typeProduct, int price, String ecoScore) {
        try {
            // Objects SimpleDateFormat for dates
            SimpleDateFormat sdfFileName = new SimpleDateFormat("HHmmss");
            SimpleDateFormat sdfFileContent = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String fileName = "t_" + sdfFileName.format(new Date()) + ".txt";

            // Create a File object from the name
            File fileInTicket = new File(fileName);
            // Write to the file

            FileWriter writer = new FileWriter(fileInTicket, true);
            writer.write("------------------------------------------------\n");
            writer.write("Date: " + sdfFileContent.format(new Date()) + "\n");
            writer.write("Type  produit: " + typeProduct.getClass().getSimpleName() + "\n");
            writer.write("Prix:" + price + "\n");
            writer.write("Eco-Score :" + ecoScore + "\n");

            // Retrieve product attributes and write them to the file
            getProductAttributes(typeProduct, writer);

            writer.write("------------------------------------------------\n");

            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void getProductAttributes(Product product, FileWriter writer) throws IOException {
        if (product instanceof ProductSensor) {
            addSensorAttributes(writer);
        } else if (product instanceof ProductBattery) {
            addBatteryAttributes(writer);
        } else if (product instanceof ProductMotor) {
            addMotorAttributes(writer);
        } else if (product instanceof ProductDrone) {
            addSensorAttributes(writer);
            addBatteryAttributes(writer);
            addMotorAttributes(writer);
        } else if (product instanceof ProductCar) {
            addBatteryAttributes(writer);
            addMotorAttributes(writer);
        } else if (product instanceof ProductAlarm) {
            addBatteryAttributes(writer);
            addSensorAttributes(writer);
        } else if (product instanceof ProductRobot) {
            addMotorAttributes(writer);
            addSensorAttributes(writer);
        }
    }

    private static void addSensorAttributes(FileWriter writer) throws IOException {
        String range = ComponentSensor.getRange();
        String colorSensor = ComponentSensor.getColorSensor();
        writer.write("Sensor Range: " + range + "\n");
        writer.write("Color Sensor: " + colorSensor + "\n");
    }

    private static void addBatteryAttributes(FileWriter writer) throws IOException {
        String load = ComponentBattery.getLoad();
        writer.write("Battery Load: " + load + "\n");
    }

    private static void addMotorAttributes(FileWriter writer) throws IOException {
        String power = ComponentMotor.getPower();
        writer.write("Motor Power: " + power + "\n");
    }
}
