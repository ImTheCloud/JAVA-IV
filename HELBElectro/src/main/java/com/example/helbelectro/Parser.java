package com.example.helbelectro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Parser {
    private static Parser instance = null;
    private static String fileName, line, componentName;
    public static List<String> componentNames = new ArrayList<>();

    private Parser() {
        // constructeur private pour ne plus faire de new
        // obligatoire pour eviter l'utilisation des instances dans d'autre class
        // mais ca peux fonctionner sans le constructeur
    }

    //  design patern singleton
    // appeler la class grace a un getInstance, similaire que en mobile
    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    // plus besoin de mettre les methodes en static que il y a le singleton
    public void parseSimulationFile() throws FileNotFoundException, InterruptedException {
        fileName = "helbelectro.data";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        Factory factory = Factory.getInstance(new ComponentBattery("0"), new ComponentMotor("0"), new ComponentSensor("0", "0"));

        while (scanner.hasNextLine()) {
            // lire chaque ligne, et pour chaque virgule la ligne est divisé
            line = scanner.nextLine();
            String[] values = line.split(",");
            int timeInSeconds = Integer.parseInt(values[0]);
            componentName = values[1];
            // Thread Sleep c'est pour attendre autant de temps qui est indique dans le fichier
            // de simulation pour chaque composant
            Thread.sleep(timeInSeconds * 1000);

            Object obj = Factory.createComponent(componentName, values);

            if (obj != null) {
                if (componentName.equals("Batterie")) {
                    componentNames.add("C-Type-1");
                } else if (componentName.equals("Capteur")) {
                    componentNames.add("C-Type-2");
                } else if (componentName.equals("Moteur")) {
                    componentNames.add("C-Type-3");
                }
            }
        }
        scanner.close();
    }
}
