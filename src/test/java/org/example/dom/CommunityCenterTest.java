package org.example.dom;

import org.example.dom.facilities.CommunityCenter;
import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommunityCenterTest {

    private Patient PATIENT_FLU_1 = new Patient("Jane Doe", VisibleSymptom.FLU, 1);
    private Patient PATIENT_FLU_7 = new Patient("John Doe", VisibleSymptom.FLU, 7);
    private Patient PATIENT_CORONAVIRUS = new Patient("Patient Zero", VisibleSymptom.CORONAVIRUS, 10);

    @Test
    public void givenFifoTriage_whenAddingPatient_thenPatientIsAddedToNurseQueue() {
        CommunityCenter center = new CommunityCenter(TriageType.FIFO);

        center.triagePatient(PATIENT_FLU_1);
        Patient patient = center.nextNursePatient();

        assertEquals(PATIENT_FLU_1, patient);
    }

    @Test
    public void givenFifoTriage_whenAddingMultiplePatients_thenOrderIsByArrival() {
        CommunityCenter center = new CommunityCenter(TriageType.FIFO);

        center.triagePatient(PATIENT_FLU_1);
        center.triagePatient(PATIENT_FLU_7);

        Patient first = center.nextNursePatient();
        Patient second = center.nextNursePatient();

        assertEquals(PATIENT_FLU_1, first);
        assertEquals(PATIENT_FLU_7, second);
    }

    @Test
    public void givenGravityTriage_whenAddingHighGravityPatient_thenPatientIsAddedToNurseQueue() {
        CommunityCenter center = new CommunityCenter(TriageType.GRAVITY);

        center.triagePatient(PATIENT_FLU_1);
        Patient patient = center.nextNursePatient();

        assertEquals(PATIENT_FLU_1, patient);
    }

    @Test
    public void givenGravityTriage_whenAddingMultiplePatients_thenOrderIsByGravity() {
        CommunityCenter center = new CommunityCenter(TriageType.GRAVITY);

        center.triagePatient(PATIENT_FLU_1); // gravité 1
        center.triagePatient(PATIENT_FLU_7); // gravité 7

        Patient first = center.nextNursePatient();
        Patient second = center.nextNursePatient();

        assertEquals(PATIENT_FLU_7, first);
        assertEquals(PATIENT_FLU_1, second);
    }


    @Test
    public void givenCoronavirusPatient_whenTriage_thenNurseQueueDoesNotReceivePatient() {
        CommunityCenter center = new CommunityCenter(TriageType.FIFO);

        center.triagePatient(PATIENT_CORONAVIRUS);

        Patient patient = center.nextNursePatient();

        assertNull(patient);
    }

}
