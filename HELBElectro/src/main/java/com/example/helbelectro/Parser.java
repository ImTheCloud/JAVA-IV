package com.example.helbelectro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private static Parser instance;
    private int timeDelay = 0;

    // constructeur prive pour empecher l'instanciation directe
    Parser() {
    }

    // methode statique pour obtenir l'instance unique du singleton
    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    // methode qui va s'occuper  de parser les lignes des composant dans le helbelectro.data
    public void parseSimulationFile() throws FileNotFoundException {
        String fileName = "helbelectro.data"; // chemin absolu !
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            // lire chaque ligne, et pour chaque virgule la ligne est divisée
            String line = scanner.nextLine();
            // diviser la ligne en tableau de valeurs ,
            String[] values = line.split(",");
            int timeInSeconds = Integer.parseInt(values[0]);
            String componentName = values[1];

            // attendre autant de temps qui est indiqué dans le fichier de simulation pour chaque composant
            Duration delay = Duration.seconds(timeDelay);
            Duration duration = Duration.seconds(timeInSeconds);
            Timeline timelineParser = new Timeline(new KeyFrame(delay.add(duration), e -> {
                HELBElectroController.getInstance().createComponent(componentName, values);
            }));
            timelineParser.play();
            timeDelay += timeInSeconds;
        }
        scanner.close();
    }
}