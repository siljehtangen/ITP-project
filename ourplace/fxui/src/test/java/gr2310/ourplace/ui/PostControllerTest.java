package gr2310.ourplace.ui;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit.ApplicationTest;

import gr2310.ourplace.client.RestClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PostControllerTest extends ApplicationTest {
  private PostController controller;

  /**
   * Launch the JavaFX application.
   *
   * @throws Exception
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
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Post.fxml"));
    Parent root = fxmlLoader.load();
    controller = fxmlLoader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Tests if you cannot publish an empty post
   * 
   * @throws IOException
   */

  @Test
  public void testEmptyPublish() throws IOException {
    PostResult result = controller.publish();

    // Assert that the post is empty
    assertTrue(result == PostResult.empty);
  }

  /**
   * Tests that you cannot publish a post with too long title
   * 
   * @throws IOException
   */
  @Test
  public void testTitle() throws IOException {
    controller.setTitle("012345678901234567890123456789012345678901234567890123456789");
    controller.setBody("Test");

    PostResult result = controller.publish();

    // Assert that the header is longer than 50 character limit
    assertTrue(result == PostResult.headerLong);

  }

  /**
   * Tests that you cannot publish a post with too long body
   * 
   * @throws IOException
   */
  @Test
  public void testBody() throws IOException {
    controller.setTitle("Test");
    controller.setBody(
        "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891");
    PostResult result = controller.publish();

    // Assert that the body is longer than 250 character limit
    assertTrue(result == PostResult.bodyLong);
  }

  /**
   * Tests that you can publish a post within the character limits
   * 
   * @throws IOException
   */

  @Test
  public void testSuccess() throws IOException {
    controller.setTitle("TestTitleSuccess");
    controller.setBody("TestBodySuccess");
    PostResult result = controller.publish();
    assertTrue(result == PostResult.success);

    // Deletes the test post from feed
    RestClient.deletePost();
  }
}