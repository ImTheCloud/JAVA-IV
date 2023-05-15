//package com.example;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class UnitTest {
//    private Produit produit;
//    private Parser parser;
//
//    @BeforeEach
//    public void setUp() {
//        produit = new Produit();
//        parser = Parser.getInstance();
//    }
//
//    @Test
//    public void testGetVentes() {
//        assertEquals(0, produit.getVentes());
//    }
//
//    @Test
//    public void testIncrementerVentes() {
//        produit.incrementerVentes();
//        assertEquals(1, produit.getVentes());
//    }
//
//    @Test
//    public void testParseSimulationFile() throws FileNotFoundException {
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
