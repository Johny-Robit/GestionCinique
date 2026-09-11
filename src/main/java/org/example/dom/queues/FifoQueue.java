package org.example.dom.queues;

import org.example.dom.Patient;

import java.util.ArrayDeque;
import java.util.Queue;

public class FifoQueue implements ClinicQueue {
    private Queue<Patient> fifoQueue = new ArrayDeque<>();

    public void addPatient(Patient patient){
        fifoQueue.add(patient);
    }
    public Patient nextPatient(){
        return fifoQueue.poll();
    }
}
