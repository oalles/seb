package es.neivi.seb.event;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.util.Assert;
import org.springframework.util.ErrorHandler;

import es.neivi.smb.handler.AbstractMessageHandler;
import es.neivi.smb.handler.MessageHandler;
import es.neivi.smb.publisher.MessagePublisher;

//@Component
public class SEBEventMulticaster extends SimpleApplicationEventMulticaster {

	// Publishing responsabilities
	@Autowired
	private MessagePublisher eventPublisher;

	private final MessageHandler messageHandler = new AbstractMessageHandler() {

		@Override
		public void handleMessage(Object event) {
			Assert.isTrue(event instanceof ApplicationEvent);
			invokeListeners((ApplicationEvent) event);
		}
	};

	/**
	 * multicast as publish, invokeListeners as handler
	 */
	@Override
	public void multicastEvent(final ApplicationEvent event) {
		try {
			eventPublisher.publish(event);
		} catch (Throwable t) {
			super.multicastEvent(event);
		}
	}

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

	/**
	 * Invoke the given listener with the given event.
	 * 
	 * @param listener
	 *            the ApplicationListener to invoke
	 * @param event
	 *            the current event to propagate
	 * @since 4.1
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void invokeListener(ApplicationListener listener,
			ApplicationEvent event) {
		ErrorHandler errorHandler = getErrorHandler();
		if (errorHandler != null) {
			try {
				listener.onApplicationEvent(event);
			} catch (Throwable err) {
				errorHandler.handleError(err);
			}
		} else {
			listener.onApplicationEvent(event);
		}
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

}
