package com.example.helbelectro;

public class Factory {
    private static Factory instance = null;
    Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Component createComponent(String componentName, String[] values) {
        switch (componentName) {
            case "Batterie":
                String load = values[2];
                return new ComponentBattery(load);
            case "Capteur":
                String range = values[2];
                String color = values[3];
                return new ComponentSensor(range, color);
            case "Moteur":
                String power = values[2];
                return new ComponentMotor(power);
            default:
                System.out.println("Composant non reconnu : " + componentName);
        }
        return null;
    }

    public static Product createNewProduct(Product product) {
        return switch (product.getClass().getSimpleName()) {
            case "ProductSensor" -> new ProductSensor(ComponentSensor.getRange(), ComponentSensor.getColorSensor());
            case "ProductBattery" -> new ProductBattery(ComponentBattery.getLoad());
            case "ProductMotor" -> new ProductMotor(ComponentMotor.getPower());
            case "ProductDrone" ->
                    new ProductDrone(ComponentMotor.getPower(), ComponentSensor.getColorSensor(), ComponentSensor.getRange(), ComponentBattery.getLoad());
            case "ProductCar" -> new ProductCar(ComponentMotor.getPower(), ComponentBattery.getLoad());
            case "ProductAlarm" ->
                    new ProductAlarm(ComponentBattery.getLoad(), ComponentSensor.getColorSensor(), ComponentSensor.getRange());
            case "ProductRobot" ->
                    new ProductRobot(ComponentMotor.getPower(), ComponentSensor.getColorSensor(), ComponentSensor.getRange());
            default -> null;
        };
    }

}
