package org.example.dom;

public class Clinic {
    WaitingList algoDeTriageRadiologie;
    WaitingList algoDeTriageDocteur;

    public Clinic(WaitingList algoDeTriageRadiologie, WaitingList algoDeTriageDocteur) {

        this.algoDeTriageRadiologie = algoDeTriageRadiologie;
        this.algoDeTriageDocteur = algoDeTriageDocteur;
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        switch (visibleSymptom) {

            case BROKEN_BONE, SPRAIN:

                assignRadiologistPatientQueue();
                break;

            default:
                assignDoctorPatientQueue();
                break;

        }
    }

    public void treatRadiologistPatient() {
        // TODO
    }

    public void treatDoctorPatient() {
        // TODO
    }

    public void assignDoctorPatientQueue() {
        // TODO
    }

    public void assignRadiologistPatientQueue() {
        // TODO
    }



    // D'autres méthodes peuvent être nécessaires

}
