package chat.server.bots;

import chat.server.model.Message;

@Command("help")
public class HelpBot extends AbstractCommandBot {

  @Override
  public void executeCommand(Message message, String[] args) {
    emit(Message.create("HelpyHelperton", "available commands:"
            + "\n/help - display help"
            + "\n/roll {sides} - to roll the dice"));
  }

}
