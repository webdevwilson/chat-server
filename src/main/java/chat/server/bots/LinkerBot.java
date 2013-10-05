package chat.server.bots;

import chat.server.events.MessageEvent;
import chat.server.model.Message;
import java.util.regex.Pattern;
import javax.enterprise.event.Observes;

public class LinkerBot {

  private final Pattern pattern = Pattern.compile("((?:https?)://[\\-A-Za-z0-9+&@#\\/%?=~_|!:,.;]*)");
  
  public void process(@Observes final MessageEvent messageEvent) {
    final Message message = messageEvent.getMessage();
    final String messageText = replaceLinks(message.getMessage());
    message.setMessage(messageText);
  }
  
  String replaceLinks(final String message) {
    return pattern.matcher(message).replaceAll("<a href=\"$1\" target=\"_BLANK\">$1</a>");
  }
  
}
