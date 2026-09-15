package org.example.dom;

import org.example.dom.facilities.Clinic;
import org.example.dom.queues.ClinicQueue;
import org.example.enums.TriageType;
import org.example.enums.VisibleSymptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClinicTest {

    private TriageType DOCTOR_TRIAGE_TYPE_FIFO = TriageType.FIFO;
    private TriageType DOCTOR_TRIAGE_TYPE_GRAVITY = TriageType.GRAVITY;
    private TriageType RADIOLOGY_TRIAGE_TYPE_FIFO = TriageType.FIFO;
    private TriageType RADIOLOGY_TRIAGE_TYPE_GRAVITY = TriageType.GRAVITY;

    private ClinicQueue DOCTOR_QUEUE;
    private ClinicQueue RADIOLOGY_QUEUE;

    private Patient PATIENT_WITH_FLU_GRAVITY_1 = new Patient("Jane Doe", VisibleSymptom.FLU, 1);
    private Patient PATIENT_WITH_FLU_GRAVITY_2 = new Patient("John Doe", VisibleSymptom.FLU, 2);
    private Patient PATIENT_WITH_FLU_GRAVITY_7 = new Patient("Wu Han", VisibleSymptom.FLU, 7);
    private Patient PATIENT_WITH_MIGRAINE_GRAVITY_1 = new Patient("Jack OfBlades", VisibleSymptom.MIGRAINE, 1);
    private Patient PATIENT_WITH_BROKEN_BONE_GRAVITY_4 = new Patient("Bruce Wayne", VisibleSymptom.BROKEN_BONE, 4);
    private Patient PATIENT_WITH_BROKEN_BONE_GRAVITY_7 = new Patient("Bruce Lee", VisibleSymptom.BROKEN_BONE, 7);
    private Patient PATIENT_WITH_SPRAIN_GRAVITY_3 = new Patient("Cat Woman", VisibleSymptom.SPRAIN, 3);



    @BeforeEach
    void setup () {
        DOCTOR_QUEUE = mock(ClinicQueue.class);
        RADIOLOGY_QUEUE = mock(ClinicQueue.class);
    }

    @Test
    public void whenPatientArrives_thenPatientIsAddedToDoctorQueue () {
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_2);

        verify(DOCTOR_QUEUE).addPatient(PATIENT_WITH_FLU_GRAVITY_2);
    }

    @Test
    public void givenPatientWithBrokenBone_whenClinicRegisters_thenAddToRadiologyQueue(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_BROKEN_BONE_GRAVITY_4);

        verify(RADIOLOGY_QUEUE).addPatient(PATIENT_WITH_BROKEN_BONE_GRAVITY_4);
    }

    @Test
    public void givenPatientWithSprain_whenClinicRegisters_thenAddToRadiologyQueue(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_SPRAIN_GRAVITY_3);

        verify(RADIOLOGY_QUEUE).addPatient(PATIENT_WITH_SPRAIN_GRAVITY_3);
    }

    @Test
    public void whenPatientArrivesWithSprain_thenIsQueuedToDoctor(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_SPRAIN_GRAVITY_3);

        verify(DOCTOR_QUEUE).addPatient(PATIENT_WITH_SPRAIN_GRAVITY_3);
    }

    @Test
    public void givenPatientWithMigraine_whenRegister_thenIsQueuedToDoctor(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);

        verify(DOCTOR_QUEUE).addPatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);
    }

    @Test
    public void givenPatientWithMigraine_whenRegister_thenIsNotQueuedToRadiology(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);

        verify(RADIOLOGY_QUEUE, never()).addPatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);
    }

    @Test
    public void givenFifoQueue_whenTwoPatients_thenSecondPatientIsSecondInQueue(){
        Clinic clinic = new Clinic(DOCTOR_TRIAGE_TYPE_FIFO, RADIOLOGY_TRIAGE_TYPE_FIFO);
        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_1);
        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_2);

        clinic.nextDoctorPatient();
        Patient secondPatient = clinic.nextDoctorPatient();

        assertEquals(PATIENT_WITH_FLU_GRAVITY_2, secondPatient);
    }

    @Test
    public void whenTwoPatientsWithFlue_thenAreNotQueuedToRadiology(){
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_1);
        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_2);

        verify(RADIOLOGY_QUEUE, never()).addPatient(any());
    }

    @Test
    public void givenHighPriorityPatient_whenRegisteringPatient_thenPatientAddedInFrontOfQueue(){
        Clinic clinic = new Clinic(DOCTOR_TRIAGE_TYPE_GRAVITY, RADIOLOGY_TRIAGE_TYPE_FIFO);

        clinic.triagePatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);
        clinic.triagePatient(PATIENT_WITH_FLU_GRAVITY_7);
        Patient frontPatient = clinic.nextDoctorPatient();

        assertEquals(PATIENT_WITH_FLU_GRAVITY_7, frontPatient);
    }

    @Test
    public void whenGravityDoctorQueueWithOnePatientInRadiologyQueue_whenBrokenBonePatientIsAdded_thenPatientIsSecondInRadiologyQueue(){
        Clinic clinic = new Clinic(DOCTOR_TRIAGE_TYPE_GRAVITY, RADIOLOGY_TRIAGE_TYPE_FIFO);

        clinic.triagePatient(PATIENT_WITH_SPRAIN_GRAVITY_3);
        clinic.triagePatient(PATIENT_WITH_BROKEN_BONE_GRAVITY_7);
        clinic.nextRadiologyPatient();
        Patient secondRadiologyPatient = clinic.nextRadiologyPatient();

        assertEquals(PATIENT_WITH_BROKEN_BONE_GRAVITY_7, secondRadiologyPatient);
    }

    @Test
    public void givenRadiologyQueueByGravity_whenAddingBrokenBonePatientWithHigherPriority_thenSortByGravity(){
        Clinic clinic = new Clinic(DOCTOR_TRIAGE_TYPE_GRAVITY, RADIOLOGY_TRIAGE_TYPE_GRAVITY);

        clinic.triagePatient(PATIENT_WITH_BROKEN_BONE_GRAVITY_4);
        clinic.triagePatient(PATIENT_WITH_BROKEN_BONE_GRAVITY_7);
        Patient firstPatient = clinic.nextRadiologyPatient();

        assertEquals(PATIENT_WITH_BROKEN_BONE_GRAVITY_7, firstPatient);
    }

    @Test
    public void givenCoronavirusPatient_whenClinicTriage_thenDoctorQueueDoesNotReceivePatient() {
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        Patient coronavirusPatient = new Patient("Patient Zero", VisibleSymptom.CORONAVIRUS, 10);

        clinic.triagePatient(coronavirusPatient);

        verify(DOCTOR_QUEUE, never()).addPatient(any());
    }

    @Test
    public void givenCoronavirusPatient_whenClinicTriage_thenRadiologyQueueDoesNotReceivePatient() {
        Clinic clinic = new Clinic(DOCTOR_QUEUE, RADIOLOGY_QUEUE);

        Patient coronavirusPatient = new Patient("Patient Zero", VisibleSymptom.CORONAVIRUS, 10);

        clinic.triagePatient(coronavirusPatient);

        verify(RADIOLOGY_QUEUE, never()).addPatient(any());
    }

}
