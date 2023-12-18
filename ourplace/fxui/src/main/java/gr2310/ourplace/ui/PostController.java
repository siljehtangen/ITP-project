package gr2310.ourplace.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import gr2310.ourplace.core.Post;
import gr2310.ourplace.client.RestClient;

public class PostController implements Controller {
  /** The app that is running. */
  private App app;
  /** Text field for the post title. */
  @FXML
  private TextField titlefield;
  /** Text field for post body. */
  @FXML
  private TextArea postfield;
  /** Max header length for post. */
  private static final int HEADER_LENGTH_MAX = 50;
  /** Max body length for post. */
  private static final int BODY_LENGTH_MAX = 250;

  /** Constructs a new PostController with initialized title and post fields. */
  public PostController() {
    this.titlefield = new TextField();
    this.postfield = new TextArea();
  }

  /**
   * Gets the title from the associated text field.
   *
   * @return The title as a String.
   */
  public String getTitle() {
    return titlefield.getText();
  }

  /**
   * Sets the title of the associated text field.
   *
   * @param title The title to set.
   */
  public void setTitle(final String title) {
    titlefield.setText(title);
  }

  /**
   * Gets the body from the associated text area.
   *
   * @return The body as a String.
   */
  public String getBody() {
    return postfield.getText();
  }

  /**
   * Sets the body of the associated text area.
   *
   * @param body The body to set.
   */
  public void setBody(final String body) {
    postfield.setText(body);
  }

  /**
   * Attempts to publish a post.
   * Displays a warning alert if there are issues with the input data.
   * Navigates to the feed scene upon successful publishing.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void fxmlPublish() throws IOException {
    PostResult result = publish();
    switch (result) {
      case empty:
        alert("You cannot publish an empty post");
        break;
      case headerLong:
        alert("Title cannot be longer than 50 characters");
        break;
      case bodyLong:
        alert("Body cannot be longer than 250 characters");
        break;
      case success:
        app.setScene("/Feed.fxml");
        break;
      default:
        return;
    }
  }

  /**
   * Displays a warning alert with the specified message.
   *
   * @param message The message to be displayed in the alert.
   */
  private void alert(final String message) {
    final String title = "Warning";

    final Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.show();
  }

  /**
   * Attempts to publish a post.
   *
   * @return The result of the publish operation as a PostResult enum.
   * @throws IOException If an I/O error occurs.
   */
  public PostResult publish() throws IOException {
    String header = titlefield.getText();
    String body = postfield.getText();

    if (header.trim().isEmpty() || body.trim().isEmpty()) {
      return PostResult.empty;
    }

    if (header.length() > HEADER_LENGTH_MAX) {
      return PostResult.headerLong;
    }

    if (body.length() > BODY_LENGTH_MAX) {
      return PostResult.bodyLong;
    }
    // Makes a new post and adds it to the post stack in Server
    Post post = Post.create(null, header, body);

    RestClient.publishPost(post);

    return PostResult.success;
  }

  /** Switches the scene back to feed without making a post. */
  public void cancelPost() throws IOException {
    app.setScene("/Feed.fxml");
  }

  /**
   * Sets app reference.
   *
   * @param app the app that is running.
   */
  @Override
  public void setApp(final App app) {
    if (app != null) {
      this.app = app;
    } else {
      throw new IllegalArgumentException("App in setApp method was null");
    }
  }
}
