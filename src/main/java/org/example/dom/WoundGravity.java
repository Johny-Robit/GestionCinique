package org.example.dom;

import java.util.Comparator;
import java.util.PriorityQueue;

public class WoundGravity implements WaitingList {

    PriorityQueue<Patient> queue;


    public WoundGravity() {

        queue = new PriorityQueue<>(Comparator.comparingInt(Patient::getGravity));

    }

    @Override
    public void addPatient(Patient patient) {
        queue.add(patient);
    }

    @Override
    public Patient chooseNextPatient() {
        return queue.peek();
    }

    @Override
    public void removePatient(Patient patient) {

    }
}
