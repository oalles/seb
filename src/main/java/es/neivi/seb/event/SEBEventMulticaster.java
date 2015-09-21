package es.neivi.seb.event;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import es.neivi.smb.annotation.RootMessageEntityDescriptor;
import es.neivi.smb.publisher.MessagePublisher;

public class SEBEventMulticaster extends SimpleApplicationEventMulticaster
		implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private transient Class<?> rootMessageEntityType;

	// Publishing responsabilities
	private MessagePublisher eventPublisher;

	/**
	 * Here is where de multicaster operates as an EVENT PUBLISHER to a MONGO
	 * CAPPED COLLECTION
	 */
	@Override
	public void multicastEvent(final ApplicationEvent event) {
		try {
			if (!validatePayload(event))
				throw new RuntimeException(
						"Invalid Message to be published to mongo db");
			getEventPublisher().publish(event);
		} catch (Throwable t) {
			// Problems: Lets proceed as usual.
			super.multicastEvent(event);
		}
	}

	/**
	 * Here is where de multicaster operates as an EVENT CONSUMER from a MONGO
	 * CAPPED COLLECTION. It is not called by the multicaster, is invoked when
	 * there is an event to be processed.
	 */
	public final void invokeListeners(ApplicationEvent event) {
		for (final ApplicationListener<?> listener : getApplicationListeners(event)) {
			Executor executor = getTaskExecutor();
			if (executor != null) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						invokeListener(listener, event);
					}
				});
			} else {
				invokeListener(listener, event);
			}
		}
	}

	@Autowired
	public void setRootMessageEntityDescriptor(
			RootMessageEntityDescriptor rootMessageEntityType) {
		this.rootMessageEntityType = rootMessageEntityType
				.getRootMessageEntityType();
	}

	public boolean validatePayload(Object payload) {
		return rootMessageEntityType.isAssignableFrom(payload.getClass());
	}

	public MessagePublisher getEventPublisher() {
		if (eventPublisher == null)
			eventPublisher = getApplicationContext().getBean(MessagePublisher.class);
		return eventPublisher;
	}

	private ApplicationContext getApplicationContext() {
		if (this.applicationContext == null) {
			throw new IllegalStateException(
					"ApplicationEventMulticaster cannot retrieve listener beans "
							+ "because it is not associated with a BeanFactory");
		}
		return this.applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
