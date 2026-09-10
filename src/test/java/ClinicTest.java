import org.example.dom.Clinic;
import org.example.dom.SortAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClinicTest {

    private SortAlgorithm FIRST_SORT_ALGORITHM;
    private SortAlgorithm SECOND_SORT_ALGORITHM;

    private String PATIENT_NAME_1 = "John Doe";


    @BeforeEach
    void setup () {
        FIRST_SORT_ALGORITHM = mock(SortAlgorithm.class);
        SECOND_SORT_ALGORITHM = mock(SortAlgorithm.class);
    }

    @Test
    public void whenPatientArrives_thenPatientIsAddedToDoctorQueue () {
        Clinic clinic = new Clinic(FIRST_SORT_ALGORITHM, SECOND_SORT_ALGORITHM);

        clinic.registerPatient(PATIENT_NAME_1);

        verify(FIRST_SORT_ALGORITHM).addPatient(PATIENT_NAME_1);
    }


}
