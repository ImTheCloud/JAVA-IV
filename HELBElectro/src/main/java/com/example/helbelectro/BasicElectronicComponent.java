package com.example.helbelectro;

public abstract class BasicElectronicComponent implements Observer {

    private Battery battery;
    private ElectricMotor electricMotor;
    private MotionSensor motionSensor;
    private double sellingPrice;
    private int ecoScore;
    private int manufacturingDuration;

    public BasicElectronicComponent(Battery battery, ElectricMotor electricMotor, MotionSensor motionSensor) {
        this.battery = battery;
        this.electricMotor = electricMotor;
        this.motionSensor = motionSensor;
    }

    public BasicElectronicComponent() {

    }

    public abstract void afficher();

    public String getInfo(){
        return  " - " ;
    }

    public void update(Object obj) {

        if(obj instanceof Battery){
            Battery cf = (Battery) obj;
            //battery = String.join(", ", cf.getListeTrainsEnApproche());
        }

        if(obj instanceof ElectricMotor){
            ElectricMotor cm = (ElectricMotor) obj;
           // electricMotor = String.join(", ", cm.getListeMagasinsOuverts());
        }

        if(obj instanceof MotionSensor){
            MotionSensor cm = (MotionSensor) obj;
           // motionSensor = String.join(", ", cm.getListeMagasinsOuverts());
        }
    }


}


