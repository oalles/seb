package es.omarall.seb.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import es.omarall.seb.test.events.MessageCreatedEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class }, loader = AnnotationConfigContextLoader.class)
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
// @DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class SEBTests extends AbstractJUnit4SpringContextTests {

	private static Logger LOG = LoggerFactory.getLogger(SEBTests.class);

	@After
	public void afterTest() {
	}

	@Test
	public void publishAndConsume() throws Exception {
		LOG.debug("We are publishing ...");
		applicationContext.publishEvent(new MessageCreatedEvent("ctx1",
				"Hola CTX2"));
		LOG.debug("Published");

		// WAIT for completion
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			LOG.error("Thread was interrupted", e);
			Thread.currentThread().interrupt();
		}
	}
}