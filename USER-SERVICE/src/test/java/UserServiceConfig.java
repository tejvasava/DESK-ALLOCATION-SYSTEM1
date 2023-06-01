import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserServiceConfig {

	@Bean
	public TestServiceInstanceListSupplier supplier() {
		return new TestServiceInstanceListSupplier();
	}
}
