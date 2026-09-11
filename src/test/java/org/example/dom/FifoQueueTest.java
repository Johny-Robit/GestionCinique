package org.example.dom;

import org.example.enums.VisibleSymptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FifoQueueTest {

    private FifoQueue fifoQueue;

    private Patient PATIENT_WITH_FLU_GRAVITY_1 = new Patient("Jane Doe", VisibleSymptom.FLU, 1);
    private Patient PATIENT_WITH_FLU_GRAVITY_2 = new Patient("John Doe", VisibleSymptom.FLU, 2);
    private Patient PATIENT_WITH_FLU_GRAVITY_7 = new Patient("Wu Han", VisibleSymptom.FLU, 7);
    private Patient PATIENT_WITH_MIGRAINE_GRAVITY_1 = new Patient("Jack OfBlades", VisibleSymptom.MIGRAINE, 1);
    private Patient PATIENT_WITH_BROKEN_BONE_GRAVITY_5 = new Patient("Bruce Wayne", VisibleSymptom.BROKEN_BONE, 5);
    private Patient PATIENT_WITH_SPRAIN_GRAVITY_3 = new Patient("Cat Woman", VisibleSymptom.SPRAIN, 3);

    @BeforeEach
    void setup(){
        fifoQueue = new FifoQueue();
    }

    @Test
    public void whenAddPatient_thenPatientCanBeRetrieved(){
        fifoQueue.addPatient(PATIENT_WITH_FLU_GRAVITY_1);

        Patient patient = fifoQueue.nextPatient();

        assertEquals(PATIENT_WITH_FLU_GRAVITY_1, patient);
    }

    @Test
    public void givenLowPriorityPatients_whenAddingPatients_thenPatientsAreAddedInFifoOrder(){
        fifoQueue.addPatient(PATIENT_WITH_FLU_GRAVITY_1);
        fifoQueue.addPatient(PATIENT_WITH_FLU_GRAVITY_2);
        fifoQueue.addPatient(PATIENT_WITH_MIGRAINE_GRAVITY_1);

        Patient firstPatient = fifoQueue.nextPatient();
        Patient secondPatient = fifoQueue.nextPatient();
        Patient thirdPatient = fifoQueue.nextPatient();

        assertEquals(PATIENT_WITH_FLU_GRAVITY_1, firstPatient);
        assertEquals(PATIENT_WITH_FLU_GRAVITY_2, secondPatient);
        assertEquals(PATIENT_WITH_MIGRAINE_GRAVITY_1, thirdPatient);
    }

}