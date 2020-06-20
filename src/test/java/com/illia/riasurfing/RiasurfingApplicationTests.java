package com.illia.riasurfing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles(value = "test")
@TestPropertySource({"classpath:application-test.properties"})
class RiasurfingApplicationTests {

//	@Test
//	void contextLoads() {
//	}

}
