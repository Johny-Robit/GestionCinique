package org.example.dom.facilities;

import org.example.dom.Patient;
import org.example.dom.queues.ClinicQueue;
import org.example.dom.queues.FifoQueue;
import org.example.dom.queues.GravityQueue;
import org.example.enums.TriageType;

public abstract class HealthcareFacility {
    protected ClinicQueue clinicQueue;

    public abstract void triagePatient(Patient patient);

    protected ClinicQueue createQueue(TriageType type){
        if (type == TriageType.FIFO) {
            return new FifoQueue();
        }
        if (type == TriageType.GRAVITY) {
            return new GravityQueue();
        }
        return null;
    }
}
