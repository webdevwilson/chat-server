package chat.server.events;

import chat.server.model.Message;

public class MessageEvent {

  private final Message message;
  
  private boolean vetoed;

  public MessageEvent(Message message) {
    this.message = message;
  }

  public Message getMessage() {
    return message;
  }
  
  public void veto() {
    vetoed = true;
  }
  
  public boolean isVetoed() {
    return vetoed;
  }
  
}
