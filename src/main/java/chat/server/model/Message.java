package chat.server.model;

import java.util.Date;

public class Message {

  private long id;
  
  private Date date;
  
  private String message;
  
  private User user;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  public static Message create(final String username, final String messageText) {
    final Message message = new Message();
    message.message = messageText;
    message.user = new User();
    message.user.setUsername(username);
    return message;
  }
  
}
