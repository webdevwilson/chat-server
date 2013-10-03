package chat.server.bots;

import chat.server.events.MessageEvent;
import chat.server.model.Message;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Command("roll")
public class DiceBot extends CommandBot {

  public void roll(@Observes MessageEvent messageEvent) {
    final Message message = messageEvent.getMessage();
    if("/roll".equals(message.getMessage())) {
      messageEvent.veto();
      
    }
  }

  @Override
  public void executeCommand(final Message message, final String[] args) {
    final int max;
    if(args.length >= 1 && args[0].matches("[0-9]+")) {
      max = Integer.parseInt(args[0]);
    } else {
      max = 6;
    }
    final int roll = (int)Math.ceil(Math.random() * max);
    emit(Message.create("OldSchoolDiceBot", message.getUser().getUsername() + " rolled a " + max + " sided dice and got " + roll));
  }
  
  
  
}
