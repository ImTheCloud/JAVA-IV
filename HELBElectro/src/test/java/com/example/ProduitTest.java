package java.com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    Scanner scanner;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        String fileName = "helbelectro.data";
        File file = new File(fileName);
        scanner = new Scanner(file);
    }

    @Test
    void testBattery() {
        testComponent("Batterie", 0, 9,0, 100);
    }

    @Test
    void testSensor() {
        testComponent("Capteur", 0, 9,0, 999);
        testColor("rouge", "jaune", "noir");
    }

    @Test
    void testMotor() {
        testComponent("Moteur", 0, 9,0, 999);
    }

    private void testComponent(String component, int minTime, int maxTime,
                               int minValue, int maxValue) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // diviser la ligne en tableau de valeurs ,
            String[] values = line.split(",");
            // recupérer le composant de la deuxieme colonne
            String currentComponent = values[1].trim();
            // si le composant correspond au composant des tests
            if (currentComponent.equals(component)) {
                String time = values[0].trim(); // trim pour enlever les espace av et apres
                int timeParse = Integer.parseInt(time); // convertir le temps en integer au cas ou
                assertTrue(timeParse >= minTime && timeParse <= maxTime); // verifier entre 0 et 9
                String attributFull = values[2].trim(); // recuperer l'attribut
                String attributNumber = attributFull.replaceAll("[^\\d]", ""); // pour extraire les chiffre
                int value = Integer.parseInt(attributNumber); // convertir la valeur de l'attribut en integer
                assertTrue(value >= minValue && value <= maxValue); // verifier que la valeur est entre les valeur specifier au dessus
            }
        }
    }

    private void testColor(String... validColors) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String component = values[1].trim();
            if (component.equals("Capteur")) { // si le composant est un capteur
                String color = values[3].trim(); // recup la couleur de la quatrième valeur
                assertTrue(isValidColor(color, validColors));
            }
        }
    }

    private boolean isValidColor(String color, String... validColors) {
        for (String validColor : validColors) {
            if (color.equals(validColor)) {
                return true;
            }
        }
        return false;
    }
}