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
            case "Batterie" -> {
                String load = values[2];
                return new ComponentBattery(load);
            }
            case "Capteur" -> {
                String range = values[2];
                String color = values[3];
                return new ComponentSensor(range, color);
            }
            case "Moteur" -> {
                String power = values[2];
                return new ComponentMotor(power);
            }
            default -> System.out.println("Composant inexistant : " + componentName);
        }
        return null;
    }

    static Product createNewProduct(Product product) {
        if (product instanceof ProductSensor) {
            return new ProductSensor(ComponentSensor.getRange(), ComponentSensor.getColorSensor());
        } else if (product instanceof ProductBattery) {
            return new ProductBattery(ComponentBattery.getLoad());
        } else if (product instanceof ProductMotor) {
            return new ProductMotor(ComponentMotor.getPower());
        } else if (product instanceof ProductDrone) {
            return new ProductDrone(ComponentMotor.getPower(), ComponentSensor.getColorSensor(), ComponentSensor.getRange(), ComponentBattery.getLoad());
        } else if (product instanceof ProductCar) {
            return new ProductCar(ComponentMotor.getPower(), ComponentBattery.getLoad());
        } else if (product instanceof ProductAlarm) {
            return new ProductAlarm(ComponentBattery.getLoad(), ComponentSensor.getColorSensor(), ComponentSensor.getRange());
        } else if (product instanceof ProductRobot) {
            return new ProductRobot(ComponentMotor.getPower(), ComponentSensor.getColorSensor(), ComponentSensor.getRange());
        } else {
            return null;
        }
    }
}
