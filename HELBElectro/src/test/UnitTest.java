//import static org.junit.jupiter.api.Assertions.*;
//import com.example.helbelectro.Parser;
//import com.example.helbelectro.conponent.ComponentBattery;
//import com.example.helbelectro.conponent.ComponentElectricMotor;
//import com.example.helbelectro.conponent.ComponentMotionSensor;
//import com.example.helbelectro.product.Product;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileNotFoundException;
//
// class UnitTest {
//    private Product produit;
//    private Parser parser;
//
//    @BeforeEach
//    public void setUp() {
//        parser = Parser.getInstance();
//    }
//
//    @Test
//    public void testParseSimulationFile() throws FileNotFoundException, FileNotFoundException {
//        parser.parseSimulationFile();
//        // Vérifier que les composants sont correctement créés
//        assertNotNull(ComponentBattery.getLoad());
//        assertNotNull(ComponentMotionSensor.getRange());
//        assertNotNull(ComponentMotionSensor.getColorSensor());
//        assertNotNull(ComponentElectricMotor.getPower());
//        // Vérifier que les valeurs aberrantes ne passent pas le test
//        assertNull(ComponentBattery.getLoad());
//        assertNull(ComponentMotionSensor.getRange());
//        assertNull(ComponentMotionSensor.getColorSensor());
//        assertNull(ComponentElectricMotor.getPower());
//    }
//}
