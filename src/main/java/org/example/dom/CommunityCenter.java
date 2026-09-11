package org.example.dom;

import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;

public class CommunityCenter extends HealthcareFacility{
    private ClinicQueue nurseQueue;

    public CommunityCenter(TriageType type) {
        this.nurseQueue = createQueue(type);
    }

    public void triagePatient(String name, int gravity) {
        triagePatient(new Patient(name, VisibleSymptom.NULL, gravity));
    }

    public void triagePatient(Patient patient) {
        nurseQueue.addPatient(patient);
    }

    public Patient nextNursePatient(){
        return nurseQueue.nextPatient();
    }
}
