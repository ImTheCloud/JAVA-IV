package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class MySubjectObserved {
    private List<Observer> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observer observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(Observer observateur) {
        observateurs.remove(observateur);
    }

    public void notifierObservateurs() {
        for (Observer observateur : observateurs) {
            observateur.update();
        }
    }
}
