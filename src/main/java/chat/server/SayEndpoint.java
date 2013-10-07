package chat.server;

import chat.server.ejb.ChatEjb;
import chat.server.events.MessageEvent;
import chat.server.model.Message;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/say")
@Consumes(MediaType.APPLICATION_JSON)
public class SayEndpoint {

  @EJB
  private ChatEjb chatEjb;
  
  @POST
  public Response speak(final Message message) {
    message.setText(escapeHtml(message.getText()));
    chatEjb.messageReceived(message);
    return Response.ok().build();
  }
  
  private static String escapeHtml(final String message)
  {
    return message.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  }
  
}
