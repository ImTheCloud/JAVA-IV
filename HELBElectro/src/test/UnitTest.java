import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.helbelectro.Factory;
import com.example.helbelectro.conponent.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTest {
    private Factory factory;
    @BeforeEach
    public void setUp() {
        factory = Factory.getInstance();
    }

    @Test
    void testCreateComponent() {
        String[] batteryValues = { "C-Type-1", "SomeValue", "LoadValue" };
        Component battery = factory.createComponent("Batterie", batteryValues);
        assertEquals("C-Type-1", battery.getName());

    }
}
