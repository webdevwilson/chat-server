package chat.server.ejb;

import chat.server.events.MessageEvent;
import chat.server.model.Message;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateful
public class ChatEjb {

  @Inject
  Event<MessageEvent> messageEvent;
          
  @Asynchronous
  public void messageReceived(final Message message) {
    messageEvent.fire(new MessageEvent(message));
  }
  
}
