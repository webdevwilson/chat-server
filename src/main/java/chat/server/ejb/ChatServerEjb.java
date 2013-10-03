package chat.server.ejb;

import chat.server.events.MessageEvent;
import chat.server.model.Message;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ChatServerEjb {

  @Inject
  Event<MessageEvent> messageEvent;
  
  @Asynchronous
  public void say(final Message message) {
    messageEvent.fire(new MessageEvent(message));
  }
  
}
