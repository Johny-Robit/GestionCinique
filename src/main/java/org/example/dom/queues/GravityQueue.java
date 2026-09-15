package org.example.dom.queues;

import org.example.dom.Patient;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GravityQueue implements ClinicQueue {

    private PriorityQueue<Patient> gravityQueue = new PriorityQueue<>(Comparator.comparingInt(Patient::gravity).reversed());

    /**
     * Inserts a patient while preserving gravity order.
     */
    @Override
    public void addPatient(Patient newPatient) {

        gravityQueue.add(newPatient);
    }

    @Override
    public Patient nextPatient() {
        return gravityQueue.poll();
    }
}

