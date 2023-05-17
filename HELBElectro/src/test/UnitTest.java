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
        testComponent("Moteur", 0, 9,0, 1000);
    }

    private void testComponent(String component, int minTime, int maxTime,
                               int minValue, int maxValue) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String currentComponent = values[1].trim();
            if (currentComponent.equals(component)) {
                String time = values[0].trim();

                int timeParse = Integer.parseInt(time);
                assertTrue(timeParse >= minTime && timeParse <= maxTime);

                String attributFull = values[2].trim();
                String attributNumber = attributFull.replaceAll("[^\\d]", "");
                int value = Integer.parseInt(attributNumber);
                assertTrue(value >= minValue && value <= maxValue);

            }
        }
    }

    private void testColor(String... validColors) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String component = values[1].trim();
            if (component.equals("Capteur")) {
                String color = values[3].trim();
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
