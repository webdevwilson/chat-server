package chat.server;

import chat.server.ejb.ChatServerEjb;
import chat.server.model.Message;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/say")
@Consumes(MediaType.APPLICATION_JSON)
public class SayEndpoint {

  @EJB
  private ChatServerEjb chatServer;
  
  @POST
  public Response speak(final Message message) {
    chatServer.say(message);
    return Response.ok().build();
  }
  
}
