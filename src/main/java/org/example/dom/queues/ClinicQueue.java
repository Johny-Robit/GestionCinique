package org.example.dom.queues;

import org.example.dom.Patient;

public interface ClinicQueue {

    public void addPatient(Patient patient);
    public Patient nextPatient();
}
