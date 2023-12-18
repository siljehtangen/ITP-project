package gr2310.ourplace.core;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PostTest {

  /**
   * Test create function and getters.
   */
  @Test
  public void testPost() {
    Post testPost = Post.create("Nora", "HeaderTest", "This is a body test");
    assertEquals("Did not get correct creator", "Nora", testPost.getCreator());
    assertEquals("Did not get correct header", "HeaderTest", testPost.getHeader());
    assertEquals("Did not get correct body", "This is a body test", testPost.getBody());

    testPost.prepareToPublish();
    String currentDate = LocalDate.now().toString();
    assertEquals("Did not get correct date", currentDate, testPost.getDate());
  }

}
