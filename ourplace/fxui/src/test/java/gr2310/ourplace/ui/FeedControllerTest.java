package gr2310.ourplace.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

import gr2310.ourplace.client.RestClient;
import gr2310.ourplace.core.Post;
import org.junit.jupiter.api.BeforeEach;

public class FeedControllerTest extends ApplicationTest {
  /**
   * // * Launch the JavaFX application.
   * // *
   * // * @throws Exception
   * //
   */
  private void launchApp() throws Exception {
    launch(App.class);
  }

  @BeforeEach
  public void setUp() throws Exception {
    launchApp();
  }

  /**
  * Start application.
  *
  * @throws IOException
  */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Feed.fxml"));
    Parent root = fxmlLoader.load();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /** Tests if the handleLikesButton is working correctly */
  @Test
  public void testHandleLikesButton() {
    Button likeButton = new Button();
    Post post = Post.create("TestName", "TestHeader", "TestBody");
    RestClient.getPosts();
    FeedController.handleLikes(post, likeButton);

    // Asserts that the number of likes is 1
    assertEquals(1, post.getLikes());

    // Deletes test post from feed
    RestClient.getPosts().pop();

  }
}
