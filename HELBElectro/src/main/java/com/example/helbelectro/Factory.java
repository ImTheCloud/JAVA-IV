package com.example.helbelectro;

public class Factory {
    private static Factory instance = null;
    private Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    // methode pour simplement créer les composants
     Component createComponent(String componentName, String[] values) {
        switch (componentName) {
            case "Batterie" :
                String load = values[2];
                return new ComponentBattery(load);
            case "Capteur" :
                String range = values[2];
                String color = values[3];
                return new ComponentMotionSensor(range, color);
            case "Moteur" :
                String power = values[2];
                return new ComponentElectricMotor(power);
        }
        return null;
    }

    // methode pour simplement créer les produits la facoty s'occupe de ca
    // je verifie chaque produits avant de le crée afin d'ajouter les bon attribut a chaque produit
     Product createNewProduct(Product product) {
        if (product instanceof ProductMotionSensor) {
            return new ProductMotionSensor(ComponentMotionSensor.getRange(), ComponentMotionSensor.getColorSensor());
        } else if (product instanceof ProductBattery) {
            return new ProductBattery(ComponentBattery.getLoad());
        } else if (product instanceof ProductElectricMotor) {
            return new ProductElectricMotor(ComponentElectricMotor.getPower());
        } else if (product instanceof ProductMonitoringDrone) {
            return new ProductMonitoringDrone(ComponentElectricMotor.getPower(), ComponentMotionSensor.getColorSensor(), ComponentMotionSensor.getRange(), ComponentBattery.getLoad());
        } else if (product instanceof ProductRemoteCar) {
            return new ProductRemoteCar(ComponentElectricMotor.getPower(), ComponentBattery.getLoad());
        } else if (product instanceof ProductSecurityAlarm) {
            return new ProductSecurityAlarm(ComponentBattery.getLoad(), ComponentMotionSensor.getColorSensor(), ComponentMotionSensor.getRange());
        } else if (product instanceof ProductTrackingRobot) {
            return new ProductTrackingRobot(ComponentElectricMotor.getPower(), ComponentMotionSensor.getColorSensor(), ComponentMotionSensor.getRange());
        } else {
            return null;
        }
    }
}
