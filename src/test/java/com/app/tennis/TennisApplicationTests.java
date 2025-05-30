package com.app.tennis;

import com.app.tennis.repository.HealthCheckRepository;
import com.app.tennis.service.HealthCheckService;
import com.app.tennis.web.HealthCheckController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TennisApplicationTests {

	@Autowired
	private HealthCheckController healthCheckController;

	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private HealthCheckRepository healthCheckRepository;


	@Test
	void contextLoads() {
		Assertions.assertThat(healthCheckController).isNotNull();
		Assertions.assertThat(healthCheckService).isNotNull();
		Assertions.assertThat(healthCheckRepository).isNotNull();
	}

}
