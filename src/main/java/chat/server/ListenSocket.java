package chat.server;

import chat.server.events.MessageEvent;
import chat.server.events.PersonJoined;
import chat.server.model.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/listen")
public class ListenSocket {

  private static final List<Session> SESSIONS = new ArrayList<>();
  
  @Inject
  Event<PersonJoined> personJoinedEvent;
  
  @OnOpen
  public void openConnection(final Session session) {
    SESSIONS.add(session);
  }
  
  @OnMessage
  public void rename(final String message) {
    personJoinedEvent.fire(new PersonJoined(message));    
  }
  
  @OnClose
  public void closeConnection(final Session session) throws IOException {
    SESSIONS.remove(session);
    session.close();
  }
  
  public void speak(@Observes(during = TransactionPhase.AFTER_SUCCESS) MessageEvent messageEvent) {
    if(!messageEvent.isVetoed()) {
      final Message message = messageEvent.getMessage();
      for (final Session session : SESSIONS) {
        session.getAsyncRemote().sendText(message.getUser().getUsername() + ":" + message.getMessage());
      }
    }
  }
  
}
