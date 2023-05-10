package com.example.helbelectro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private static int timeDelay = 0;

    // plus besoin de mettre les méthodes en static puisqu'il y a le singleton
    public static void parseSimulationFile() throws FileNotFoundException {
        String fileName = "helbelectro.data";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            // lire chaque ligne, et pour chaque virgule la ligne est divisée
            String line = scanner.nextLine();
            String[] values = line.split(",");
            int timeInSeconds = Integer.parseInt(values[0]);
            String componentName = values[1];

            // Attendre autant de temps qui est indiqué dans le fichier de simulation pour chaque composant
            Duration delay = Duration.seconds(timeDelay);
            Duration duration = Duration.seconds(timeInSeconds);
            Timeline timelineParser = new Timeline(new KeyFrame(delay.add(duration), e -> {
                HELBElectroController.createComponent(componentName, values);
            }));
            timelineParser.play();
            timeDelay += timeInSeconds;
        }
        scanner.close();
    }
}