package org.example.dom.facilities;

import org.example.dom.Patient;
import org.example.dom.queues.ClinicQueue;
import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

public class CommunityCenter extends HealthcareFacility {
    private final ClinicQueue nurseQueue;

    public CommunityCenter(TriageType type) {
        this.nurseQueue = createQueue(type);
    }

    public void triagePatient(String name, int gravity) {
        triagePatient(new Patient(name, VisibleSymptom.NULL, gravity));
    }

    public void triagePatient(Patient patient) {
        if (patient.visibleSymptom() == VisibleSymptom.CORONAVIRUS) {
            return; // rejet
        }

        nurseQueue.addPatient(patient);
    }

    public Patient nextNursePatient(){
        return nurseQueue.nextPatient();
    }
}
