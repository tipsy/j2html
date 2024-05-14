package j2html.comparison;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@Ignore
@BenchmarkOptions(callgc = false, benchmarkRounds = 20_000, warmupRounds = 200, concurrency = 2, clock = Clock.NANO_TIME)
public class RenderPerformanceComparisonTest {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Test
    public void j2htmlPerformance() throws Exception {
        TestOptionsForJ2html.helloWorld();
        TestOptionsForJ2html.fiveHundredEmployees();
        TestOptionsForJ2html.macros();
        TestOptionsForJ2html.multiplicationTable();
    }

    @Test
    public void velocityPerformance() throws Exception {
        TestOptionsForVelocity.helloWorld();
        TestOptionsForVelocity.fiveHundredEmployees();
        TestOptionsForVelocity.macros();
        TestOptionsForVelocity.multiplicationTable();
    }

}
