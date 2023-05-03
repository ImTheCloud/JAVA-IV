package com.example.helbelectro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Parser {
    private static Parser instance = null;


    private Parser() {
        // empecher la creation d'instance
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
        String fileName = "helbelectro.data";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            // lire chaque ligne, et pour chaque virgule la ligne est divisé
            String line = scanner.nextLine();
            String[] values = line.split(",");
            int timeInSeconds = Integer.parseInt(values[0]);
            String componentName = values[1];
            // Thread Sleep c'est pour attendre autant de temps qui est indique dans le fichier
            // de simulation pour chaque composant
            Thread.sleep(timeInSeconds * 1000);
            Factory.getInstance().createComponent(componentName, values);
        }
        scanner.close();
    }
}
