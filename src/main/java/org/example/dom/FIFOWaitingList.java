package org.example.dom;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOWaitingList implements WaitingList {

    Queue<Patient> queue;

    public FIFOWaitingList() {

        queue = new LinkedList<>();

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

        queue.remove(patient);

    }
}
