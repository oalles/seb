package es.neivi.seb.test.events;

import org.springframework.context.ApplicationEvent;

import es.neivi.smb.annotation.RootMessageEntity;

@RootMessageEntity
public class PersistentApplicationEvent extends ApplicationEvent {

	public PersistentApplicationEvent(Object source) {
		super(source);
	}
}
