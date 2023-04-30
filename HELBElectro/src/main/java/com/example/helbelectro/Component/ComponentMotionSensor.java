package com.example.helbelectro.Component;

    public class ComponentMotionSensor {
        private String resistance;
        private String couleur;

        public ComponentMotionSensor(String resistance, String couleur) {
            this.resistance = resistance;
            this.couleur = couleur;
        }

        public String getResistance() {
            return resistance;
        }

        public String getCouleur() {
            return couleur;
        }

        public double lireMesure() {
            // Code pour lire la mesure du capteur et la retourner en tant que double
            return 0;
        }
    }

