package org.example.dom.facilities;

import org.example.dom.Patient;
import org.example.dom.queues.ClinicQueue;
import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

public class Clinic extends HealthcareFacility {
    // Comportement attendu de clinic :
    // décider si un patient doit aller voir le docteur ou le docteur et la radiologie
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

    public void triagePatient(Patient patient){
        switch (patient.visibleSymptom()) {
            case CORONAVIRUS:
                return;
            case BROKEN_BONE, SPRAIN:
                radiologyQueue.addPatient(patient);
            default:
                doctorQueue.addPatient(patient);
        }
    }

    public Patient nextDoctorPatient(){
        return doctorQueue.nextPatient();
    }

    public Patient nextRadiologyPatient(){
        return radiologyQueue.nextPatient();
    }
}
