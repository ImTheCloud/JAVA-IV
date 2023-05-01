package com.example.helbelectro.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static String fileName,power,line,range,color,load,componentName;
    public static List<String> componentNames = new ArrayList<>();

    public static void parseSimulationFile() throws FileNotFoundException, InterruptedException {
        fileName = "simulation.txt";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        System.out.println("Creation of components in progress");

        while (scanner.hasNextLine()) {
            // lire chaque ligne, et pour chaque virgule la ligne est divisé
            line = scanner.nextLine();
            String[] values = line.split(",");

            int timeInSeconds = Integer.parseInt(values[0]); // juste pour avoir le temps  en secondes
            componentName = values[1];

            // Thread Sleep c'est pour attendre autant de temps qui est indique dans le fichier
            // de simulation pour chaque composant
            Thread.sleep(timeInSeconds * 1000);

            if (componentName.equals("Batterie")) {
                load = values[2];
                ComponentBattery battery = new ComponentBattery(load);
               //System.out.println("Battery created in " + timeInSeconds + " seconds");
                componentNames.add("C-Type-1");
                } else if (componentName.equals("Capteur")) {
                range = values[2];
                color = values[3];
                ComponentSensor sensor = new ComponentSensor(range, color);
                //System.out.println("Sensor created in " + timeInSeconds + " seconds");
                componentNames.add("C-Type-2");

            } else if (componentName.equals("Moteur")) {
                power = values[2];
                ComponentMotor motor = new ComponentMotor(power);
                //System.out.println("Motor created in " + timeInSeconds + " seconds");
                componentNames.add("C-Type-3");
            }
            //System.out.println("list of components :"+ componentNames);

        }
        scanner.close();
    }

}
