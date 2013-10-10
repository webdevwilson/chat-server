package chat.server.bots;

import chat.server.events.MessageEvent;
import chat.server.events.PersonJoined;
import chat.server.model.Message;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class WelcomeBot {

  @Inject
  private Event<MessageEvent> messageEvent;
  
  public void welcome(@Observes PersonJoined personJoined) {
    messageEvent.fire(new MessageEvent(Message.create("WelcomeBot", "type '/help' to view available commands")));
  }
  
}
