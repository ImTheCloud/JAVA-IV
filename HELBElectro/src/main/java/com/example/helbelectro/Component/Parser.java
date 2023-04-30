package com.example.helbelectro.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void parseSimulationFile() throws FileNotFoundException, InterruptedException {
        String fileName = "simulation.txt";
        File file = new File(fileName);

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String componentName = values[1];
            int timeInSeconds = Integer.parseInt(values[0]);

            Thread.sleep(timeInSeconds * 1000);

            if (componentName.equals("Capteur")) {
                String resistance = values[2];
                String couleur = values[3];
                ComponentMotionSensor capteur = new ComponentMotionSensor(resistance, couleur);
                System.out.println("Capteur créé : " + capteur);
            } else if (componentName.equals("Batterie")) {
                String niveauDeCharge = values[2];
                ComponentBattery batterie = new ComponentBattery(niveauDeCharge);
                System.out.println("Batterie créée : " + batterie);
            } else if (componentName.equals("Moteur")) {
                String puissance = values[2];
                ComponentElectricMotor moteur = new ComponentElectricMotor(puissance);
                System.out.println("Moteur créé : " + moteur);
            }
        }

        scanner.close();
    }

}
