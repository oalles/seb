package es.omarall.seb.test.events;

import org.springframework.context.ApplicationEvent;

import es.omarall.smb.annotation.RootMessageEntity;

@RootMessageEntity
public class PersistentApplicationEvent extends ApplicationEvent {

	public PersistentApplicationEvent(Object source) {
		super(source);
	}
}
