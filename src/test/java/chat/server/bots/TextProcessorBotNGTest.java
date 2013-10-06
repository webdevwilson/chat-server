package chat.server.bots;

import org.fest.assertions.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextProcessorBotNGTest {
  
  TextProcessorBot linkerBot;
          
  @BeforeMethod
  public void setup() {
    linkerBot = new TextProcessorBot();
  }
  
  @Test
  public void should_link_http_text() {
    
    String http = linkerBot.replaceLinks("http://www.google.com");
    Assertions.assertThat(http).isEqualTo("<a href=\"http://www.google.com\" target=\"_BLANK\">http://www.google.com</a>");
    
    http = linkerBot.replaceLinks("go to http://www.google.com");
    Assertions.assertThat(http).isEqualTo("go to <a href=\"http://www.google.com\" target=\"_BLANK\">http://www.google.com</a>");
    
    http = linkerBot.replaceLinks("go to http://www.google.com and see");
    Assertions.assertThat(http).isEqualTo("go to <a href=\"http://www.google.com\" target=\"_BLANK\">http://www.google.com</a> and see");
    
    http = linkerBot.replaceLinks("go to https://www.google.com?q=blah&agent=chrome and see");
    Assertions.assertThat(http).isEqualTo("go to <a href=\"https://www.google.com?q=blah&agent=chrome\" target=\"_BLANK\">https://www.google.com?q=blah&agent=chrome</a> and see");
    
    http = linkerBot.replaceLinks("go to https://www.google.com/path.html?q=blah&agent=chrome and see");
    Assertions.assertThat(http).isEqualTo("go to <a href=\"https://www.google.com/path.html?q=blah&agent=chrome\" target=\"_BLANK\">https://www.google.com/path.html?q=blah&agent=chrome</a> and see");
    
    http = linkerBot.replaceLinks("go to https://www.google.com/path.html?q=blah&agent=chrome or http://www.google.com/path.html?q=blah");
    Assertions.assertThat(http).isEqualTo("go to <a href=\"https://www.google.com/path.html?q=blah&agent=chrome\" target=\"_BLANK\">https://www.google.com/path.html?q=blah&agent=chrome</a> or "
            + "<a href=\"http://www.google.com/path.html?q=blah\" target=\"_BLANK\">http://www.google.com/path.html?q=blah</a>");
  }
  
}
