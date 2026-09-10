import org.example.dom.Clinic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.dom.AlgoDeTriage;

public class ClinicTest {

    @BeforeEach
    public void setup () {
        AlgoDeTriage algo = new AlgoDeTriage();
    }


    @Test
    public void whenClinique_InstanciateWithAlgorithm () {
        Clinic uneClinique = new Clinic(algo);
    }
}
