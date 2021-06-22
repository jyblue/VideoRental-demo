package video.rental.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class VideoRentalTest {
	
	private GoldenMaster goldenMaster = new GoldenMaster();
	
	@Test
	@Disabled
	void generateGoldenMaster() {
		// Given (Arrange)
		
		// When (Act)
		goldenMaster.generate();

		// Then (Assert)
	}
	
	@Test
//	@Disabled
	void check_runresult_against_goldenmaster() {
		// Given (Arrange)
		String expected =  goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getRunResult();

		// Then (Assert)
		assertEquals(expected, actual.replaceAll("\r\n", "\n"));
	}
}
