import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testAdd() {
        App app = new App();
        assertEquals(5, app.add(2, 3));
    }

    @Test
    public void testAddNegative() {
        App app = new App();
        assertEquals(0, app.add(-1, 1));
    }
}
```

Then:
```
Ctrl + S  →  Save the file