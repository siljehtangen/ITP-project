package gr2310.ourplace.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

public class AppTest extends ApplicationTest {
  /**
   * Launch the JavaFX application.
   * 
   * @throws Exception
   */
  private void launchApp() throws Exception {
    launch(App.class);
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

  /**
   * Proves that the files Feed.fxml and Post.fxml exist.
   */
  @Test
  public void testFindResource() {
    URL resource = getClass().getResource("/Feed.fxml");
    if (resource == null) {
      throw new IllegalArgumentException("Couldn't find the scene file: Feed.fxml");
    }
    URL resource2 = getClass().getResource("/Post.fxml");
    if (resource2 == null) {
      throw new IllegalArgumentException("Couldn't find the scene file: Feed.fxml");
    }
  }

  @Test
  public void testsetScene() throws IOException {
    App app = new App();
   try{
    app.setScene(null);
    fail("Expected IllegalArgumentException");
   } catch (IllegalArgumentException e) {
     assertEquals("No scene with filename null has been preloaded.", e.getMessage());
   }
  }

  /**
   * Use TestFX to find and verify the existence of the newPostButton and click
   * on
   * it to see the Posts view. Then type "Hello" in the titlefield and verify
   * that
   * it was written.
   *
   * @throws Exception if app wil not launch
   */

  @Test
  public void testUI() throws Exception {
    FxRobot robot = new FxRobot();
    launchApp();
    // Check that the button exists
    verifyThat("#newPostButton", hasText("New post"));
    // Click on it
    robot.clickOn("#newPostButton");
    // Wait for view to swicth
    sleep(1000);

    // Test emptypost alert (completely empty)
    robot.clickOn("#postbutton");
    // Check for the alert dialog
    sleep(1000); // Wait for the alert dialog to appear
    DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
    assertEquals("You cannot publish an empty post", dialogPane.getContentText());
    robot.clickOn("OK");

    // Test emptypost alert (empty body)
    robot.clickOn("#titlefield");
    robot.write("Hello");
    verifyThat("#titlefield", TextInputControlMatchers.hasText("Hello"));
    robot.clickOn("#postbutton");
    // Check for the alert dialog
    sleep(1000); // Wait for the alert dialog to appear
    dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
    assertEquals("You cannot publish an empty post", dialogPane.getContentText());
    robot.clickOn("OK");

    // Test emptypost alert (empty title)
    robot.clickOn("#titlefield");
    robot.eraseText(5);
    robot.clickOn("#postfield");
    robot.write("Hello");
    robot.clickOn("#postbutton");
    // Check for the alert dialog
    sleep(1000); // Wait for the alert dialog to appear
    dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
    assertEquals("You cannot publish an empty post", dialogPane.getContentText());
    robot.clickOn("OK");

    // Test Title too long alert
    robot.clickOn("#titlefield");
    robot.write("111111111111111111111111111111111111111111111111111111111111111111111");
    robot.clickOn("#postbutton");
    // Check for the alert dialog
    sleep(1000); // Wait for the alert dialog to appear
    dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
    assertEquals("Title cannot be longer than 50 characters", dialogPane.getContentText());
    robot.clickOn("OK");

    // Test Body too long alert
    robot.clickOn("#titlefield");
    robot.eraseText(50);
    robot.clickOn("#postfield");
    robot.write(
        "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    robot.clickOn("#postbutton");
    // Check for the alert dialog
    sleep(1000); // Wait for the alert dialog to appear
    dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
    assertEquals("Body cannot be longer than 250 characters", dialogPane.getContentText());
    robot.clickOn("OK");

    // Test cancel button
    robot.clickOn("#cancelbutton");
    //verify that the scene is set to e Feed.fxml which has the newPostButton
    verifyThat("#newPostButton", hasText("New post"));
  }
}
