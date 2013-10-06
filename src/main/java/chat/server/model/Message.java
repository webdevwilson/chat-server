package chat.server.model;

import java.util.Date;

public class Message {

  private long id;
  
  private Date date;
  
  private String text;
  
  private User user;

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(final Date date) {
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }
  
  public Message edit(final String newText) {
    final Message newMessage = new Message();
    newMessage.setDate(date);
    newMessage.setUser(user);
    newMessage.setText(newText);
    return newMessage;
  }
  
  public static Message create(final String username, final String messageText) {
    final Message message = new Message();
    message.text = messageText;
    message.user = new User();
    message.user.setUsername(username);
    return message;
  }
  
}
