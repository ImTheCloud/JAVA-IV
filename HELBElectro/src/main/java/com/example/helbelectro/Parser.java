package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private static Parser instance = null;
    private int timeDelay = 0;




    private Boolean isProductionPaused = false;
    private Parser() {
        // empêcher la création d'instance
    }

    // design pattern singleton
// appeler la classe grâce à un getInstance, similaire à en mobile
    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    // plus besoin de mettre les méthodes en static puisqu'il y a le singleton
    public void parseSimulationFile() throws FileNotFoundException {
        String fileName = "helbelectro.data";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine() && isProductionPaused == false) {
            //System.out.println(isProductionPaused);
            // lire chaque ligne, et pour chaque virgule la ligne est divisée
            String line = scanner.nextLine();
            String[] values = line.split(",");
            int timeInSeconds = Integer.parseInt(values[0]);
            String componentName = values[1];

            // Attendre autant de temps qui est indiqué dans le fichier de simulation pour chaque composant
            Duration delay = Duration.seconds(timeDelay);
            Duration duration = Duration.seconds(timeInSeconds);
            Timeline timelineParser = new Timeline(new KeyFrame(delay.add(duration), e -> {
                Factory.getInstance().createComponent(componentName, values);
            }));
            timelineParser.play();
            timeDelay += timeInSeconds;
        }
        scanner.close();
    }

    public Boolean getProductionPaused() {
        return isProductionPaused;
    }
    public void setProductionPaused(Boolean productionPaused) {
        isProductionPaused = productionPaused;
    }

}