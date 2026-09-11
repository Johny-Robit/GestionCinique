package org.example.dom;

import org.example.dom.Patient;
import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

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
