import org.example.dom.Clinic;
import org.example.dom.WaitingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class ClinicTest {

    private WaitingList algo1;
    private WaitingList algo2;


    @BeforeEach
    void setup () {
        algo1 = mock(WaitingList.class);
        algo2 = mock(WaitingList.class);
    }

    @Test
    public void whenClinic_InstanciatedWithTwoValidAlgorithm_ShouldCreateClinic () {
        Clinic uneClinique = new Clinic(algo1, algo2);

        assertNotNull(uneClinique);
    }

    @Test
    public void n() {}

}
