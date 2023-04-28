package com.example.helbelectro.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void parseSimulationFile() throws FileNotFoundException {
        String fileName = "/home/helb/Documents/GitHub/JAVA-IV/HELBElectro/simulation.txt";
        File file = new File(fileName);

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] values = line.split(",");
//            String componentName = values[1];
//            int timeInSeconds = Integer.parseInt(values[0]);
//
//            System.out.println(componentName + " - " + timeInSeconds + " seconds");
//        }

        scanner.close();
    }
}
