package chat.server.bots;

import chat.server.events.MessageEvent;
import chat.server.model.Message;
import java.util.Arrays;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class CommandBot {
  
  @Inject
  private Event<MessageEvent> messageEventDispatch;
  
  private final String command;

  public CommandBot() {
    command = getClass().getAnnotation(Command.class).value();
  }
  
  public final void message(@Observes MessageEvent messageEvent) {
    final String message = messageEvent.getMessage().getMessage();
    final String[] messageParts = message.split(" ");
    if(messageParts[0].equalsIgnoreCase("/" + command)) {
      messageEvent.veto();
      final String[] args;
      if(messageParts.length > 1) {
        args = Arrays.copyOfRange(messageParts, 1, messageParts.length);
      } else {
        args = new String[0];
      }
      executeCommand(messageEvent.getMessage(), args);
    }
  }
  
  protected void emit(final Message message) {
    messageEventDispatch.fire(new MessageEvent(message));
  }
  
  public abstract void executeCommand(Message message, String[] args);
  
}
