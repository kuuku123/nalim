package one.nalim;

import one.nalim.example.Cpu;
import org.junit.jupiter.api.Test;

public class NativeTest {

    @Test
    public void test1() {
        long rdtsc = Cpu.rdtsc();
    }
}
