package org.example.dom;

import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

public class Clinic extends HealthcareFacility{
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

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        triagePatient(new Patient(name, visibleSymptom, gravity));
    }

    public void triagePatient(Patient patient){
        if (patient.visibleSymptom() == VisibleSymptom.CORONAVIRUS) {
            return; // rejet
        }

        doctorQueue.addPatient(patient);

        if (patient.visibleSymptom() == VisibleSymptom.BROKEN_BONE || patient.visibleSymptom() == VisibleSymptom.SPRAIN) {
            radiologyQueue.addPatient(patient);
        }
    }

    public Patient nextDoctorPatient(){
        return doctorQueue.nextPatient();
    }

    public Patient nextRadiologyPatient(){
        return radiologyQueue.nextPatient();
    }
}
