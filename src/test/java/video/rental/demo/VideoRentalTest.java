package video.rental.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

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
	@EnabledOnOs({OS.LINUX, OS.MAC})
	void check_runresult_against_goldenmaster() {
		// Given (Arrange)
		String expected =  goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getRunResult();

		// Then (Assert)
		assertEquals(expected, actual);
	}
	
	@Test
//	@Disabled
	@EnabledOnOs({OS.WINDOWS})
	void check_runresult_against_goldenmaster_on_Windows() {
		// Given (Arrange)
		String expected =  goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getRunResult();

		// Then (Assert)
		assertEquals(expected, actual.replaceAll("\r\n", "\n"));
	}
}
