package com.example.helbelectro;

import com.example.helbelectro.component.Component;
import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentElectricMotor;
import com.example.helbelectro.component.ComponentMotionSensor;
import com.example.helbelectro.product.*;
import com.example.helbelectro.product.strategyProduct.*;

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
    public Component createComponent(String componentName, String[] values) {
        switch (componentName) {
            case "Batterie":
                String load = values[2];
                ComponentBattery battery = new ComponentBattery(load);
                return battery;
            case "Capteur":
                String range = values[2];
                String color = values[3];
                ComponentMotionSensor motionSensor = new ComponentMotionSensor(range, color);
                return motionSensor;
            case "Moteur":
                String power = values[2];
                ComponentElectricMotor electricMotor = new ComponentElectricMotor(power);
                return electricMotor;
        }
        return null;
    }


    // methode pour simplement créer les produits depuis la strategy, la facoty s'occupe de ca
    Product createNewProduct(Product product) {
        ProductCreationStrategy creationStrategy = null;
        if (product instanceof ProductMotionSensor) {
            creationStrategy = new ProductMotionSensorCreationStrategy();
        } else if (product instanceof ProductBattery) {
            creationStrategy = new ProductBatteryCreationStrategy();
        } else if (product instanceof ProductElectricMotor) {
            creationStrategy = new ProductElectricMotorCreationStrategy();
        } else if (product instanceof ProductMonitoringDrone) {
            creationStrategy = new ProductMonitoringDroneCreationStrategy();
        } else if (product instanceof ProductRemoteCar) {
            creationStrategy = new ProductRemoteCarCreationStrategy();
        } else if (product instanceof ProductSecurityAlarm) {
            creationStrategy = new ProductSecurityAlarmCreationStrategy();
        } else if (product instanceof ProductTrackingRobot) {
            creationStrategy = new ProductTrackingRobotCreationStrategy();
        }

        // si une strategy existe alors il le crée
        if (creationStrategy != null) {
            return creationStrategy.createProduct();
        } else {
            return null;
        }
    }
}
