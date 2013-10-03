package chat.server.bots;

import chat.server.model.Message;

@Command("help")
public class HelpBot extends CommandBot {

  @Override
  public void executeCommand(Message message, String[] args) {
    emit(Message.create("HelpyHelperton", "roll {sides} - to roll the dice"));
  }

}
