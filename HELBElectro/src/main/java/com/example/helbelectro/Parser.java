package com.example.helbelectro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/helb/Documents/GitHub/JAVA-IV/HELBElectro/simulation.txt";
        File file = new File(fileName);

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

        scanner.close();
    }
}
