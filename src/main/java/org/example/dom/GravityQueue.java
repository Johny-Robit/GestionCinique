package org.example.dom;

import java.util.LinkedList;
import java.util.Queue;

public class GravityQueue implements ClinicQueue{
    private LinkedList<Patient> gravityQueue = new LinkedList<>();

    /**
     * Inserts a patient while preserving gravity order.
     */
    @Override
    public void addPatient(Patient newPatient) {

        // Scan for the first patient with lower gravity
        for (int i = 0; i < gravityQueue.size(); i++) {
            Patient patientInList = gravityQueue.get(i);

            if (patientInList.gravity() < newPatient.gravity()) {
                gravityQueue.add(i, newPatient); // Insert at position
                return;
            }
        }

        // No lower gravity found -> append at the end
        gravityQueue.addLast(newPatient);
    }

    @Override
    public Patient nextPatient() {
        return gravityQueue.poll();
    }
}
