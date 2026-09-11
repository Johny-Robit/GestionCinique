package org.example.dom;

import org.example.dom.queues.GravityQueue;
import org.example.enums.VisibleSymptom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GravityQueueTest {

    private Patient PATIENT_WITH_GRAVITY_1 = new Patient("Jane Doe", VisibleSymptom.FLU, 1);
    private Patient PATIENT_WITH_GRAVITY_2 = new Patient("John Doe", VisibleSymptom.FLU, 2);
    private Patient PATIENT_WITH_GRAVITY_3 = new Patient("Cat Woman", VisibleSymptom.SPRAIN, 3);
    private Patient PATIENT_WITH_GRAVITY_4 = new Patient("Bruce Wayne", VisibleSymptom.BROKEN_BONE, 4);
    private Patient PATIENT_WITH_GRAVITY_5 = new Patient("Jack OfBlades", VisibleSymptom.MIGRAINE, 5);



    @Test
    void whenPatientAdded_thenPatientCanBeRetrieved() {
        GravityQueue gravityQueue = new GravityQueue();

        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_1);
        Patient retrievedPatient = gravityQueue.nextPatient();

        assertEquals(PATIENT_WITH_GRAVITY_1, retrievedPatient);
    }

    @Test
    void whenAddingPatientWithHigherPriority_thenNewPatientIsInFrontOfQueue(){
        GravityQueue gravityQueue = new GravityQueue();

        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_1);
        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_2);
        Patient frontQueuePatient = gravityQueue.nextPatient();

        assertEquals(PATIENT_WITH_GRAVITY_2, frontQueuePatient);
    }

    @Test
    void whenAddingPatientWithDifferentGravity_thenSortInOrder(){
        GravityQueue gravityQueue = new GravityQueue();

        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_3);
        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_2);
        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_4);
        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_1);
        gravityQueue.addPatient(PATIENT_WITH_GRAVITY_5);
        Patient firstPatient = gravityQueue.nextPatient();
        Patient secondPatient = gravityQueue.nextPatient();
        Patient thirdPatient = gravityQueue.nextPatient();
        Patient fourthPatient = gravityQueue.nextPatient();
        Patient fifthPatient = gravityQueue.nextPatient();

        assertEquals(PATIENT_WITH_GRAVITY_5, firstPatient);
        assertEquals(PATIENT_WITH_GRAVITY_4, secondPatient);
        assertEquals(PATIENT_WITH_GRAVITY_3, thirdPatient);
        assertEquals(PATIENT_WITH_GRAVITY_2, fourthPatient);
        assertEquals(PATIENT_WITH_GRAVITY_1, fifthPatient);
    }


}