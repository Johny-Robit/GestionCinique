package org.example.dom;

import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

public class Clinic {
    private ClinicQueue doctorQueue;
    private ClinicQueue radiologyQueue;

    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        this.doctorQueue = createQueue(doctorTriageType);
        this.radiologyQueue = createQueue(radiologyTriageType);
    }

    public Clinic(ClinicQueue doctorQueue, ClinicQueue radiologyQueue){
        this.doctorQueue = doctorQueue;
        this.radiologyQueue = radiologyQueue;
    }

    public void registerPatient(Patient patient){
        doctorQueue.addPatient(patient);
        if (patient.visibleSymptom() == VisibleSymptom.BROKEN_BONE || patient.visibleSymptom() == VisibleSymptom.SPRAIN) {
            radiologyQueue.addPatient(patient);
        }
    }

    private ClinicQueue createQueue(TriageType type){
        if (type == TriageType.FIFO) {
            return new FifoQueue();
        }
        // plus tard : return new GravityQueue();
        return null;
    }

    public Patient nextDoctorPatient(){
        return doctorQueue.nextPatient();
    }
}
