package org.example.dom;

public class Patient {
    String name;
    int gravity;
    VisibleSymptom symptom;

    Patient (String name, int gravity, VisibleSymptom symptom) {
        this.name = name;
        this.symptom = symptom;
        this.gravity = gravity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisibleSymptom getSymptom() {
        return symptom;
    }

    public void setSymptom(VisibleSymptom symptom) {
        this.symptom = symptom;
    }


    public int getGravity() {
        return this.gravity;
    }


}
