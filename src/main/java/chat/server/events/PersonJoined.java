package chat.server.events;

public class PersonJoined {

  private final String username;

  public PersonJoined(final String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
  
}
