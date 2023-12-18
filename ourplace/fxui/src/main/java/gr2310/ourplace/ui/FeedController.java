package gr2310.ourplace.ui;

import java.io.IOException;
import java.util.Stack;

import gr2310.ourplace.client.RestClient;
import gr2310.ourplace.core.Feed;
import gr2310.ourplace.core.FeedSort;
import gr2310.ourplace.core.Post;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FeedController implements Controller {
  /** The app that is running. */
  private App app;

  /** VBox for the feed. */
  @FXML
  private VBox feed;

  /** Scrollpane for the feed. */
  @FXML
  private ScrollPane scroller;

  /** Sort by date button. */
  @FXML
  private Button sortByDateButton;

  /** Sort by likes button. */
  @FXML
  private Button sortByLikesButton;

  /**
   * Fetches the posts from the server and loads them onto the page.
   */
  @FXML
  public void initialize() {
    data = RestClient.getPosts();

    rebuildFeed();

    VBox.setVgrow(feed, Priority.ALWAYS);
    scroller.setFitToWidth(true);
    VBox.setVgrow(scroller, Priority.ALWAYS);
  }

  private void rebuildFeed() {
    data = RestClient.getPosts();
    final Stack<Post> sortedFeed = Feed.sort(data, sort);

    feed.getChildren().clear(); // Clear feed
    for (Post post : sortedFeed) {
      Node widget = postToWidget(post); // Data to widget
      feed.getChildren().add(0, widget); // Add widget to children
    }
  }

  /** Stack of posts. */
  private Stack<Post> data;

  /** Value sort post by ascending date. */
  private FeedSort sort = FeedSort.dateAscending;

  /** Rebuild feed with post sorted by ascending date. */
  @FXML
  public void sortByDate() {
    sort = FeedSort.dateAscending;
    rebuildFeed();
  }

  /** Rebuild feed with post sorted by ascending likes. */
  @FXML
  public void sortByLikes() {
    sort = FeedSort.likesAscending;
    rebuildFeed();
  }

  /**
   * Sets the scene to post so user can make a new post.
   *
   * @throws IOException when we can't find the file or set the scene.
   */
  public void newPost() throws IOException {
    app.setScene("/Post.fxml");
  }

  /** Min height of post. */
  private static final double DEFAULT_VBOX_HEIGHT = 100;
  /** Padding of post. */
  private static final double POST_PADDING = 5;
  /** Font size of body. */
  private static final double BODY_FONT_SIZE = 15;
  /** Font size of header. */
  private static final double HEADER_FONT_SIZE = 20;
  /** X margin of post. */
  private static final double POST_X_MARGIN = 10;
  /** Y margin of post. */
  private static final double POST_Y_MARGIN = 5;

  /**
   * Generate an FXML widget from this Post.
   *
   * @param post the post we want to wigetize
   * @param likeButton for the post
   */
  public static void handleLikes(final Post post, final Button likeButton) {
    post.incrementLikes();
    likeButton.setText("Liked (" + post.getLikes() + ")");
    likeButton.setDisable(false);
    if (!likeButton.getStyle().contains("-fx-background-color: #f39bac;")) {
      likeButton.setStyle("-fx-background-color: #f39bac;");
    }
    RestClient.likePost(post);
  }

  /**
   * Makes a VBox object of posts and their likes.
   * @param post
   * @return parent
   */
  private VBox postToWidget(final Post post) {
    var textFlow = new TextFlow();

    double height = DEFAULT_VBOX_HEIGHT;

    textFlow.setMinHeight(height);
    textFlow.setStyle("-fx-background-color: #d6d6d6;");
    textFlow.setPadding(new Insets(POST_PADDING, POST_PADDING,
        POST_PADDING, POST_PADDING));

    if (post.getCreator() == null) {
      post.setCreator("Anonymous");
    }

    Font bodyFont = Font.font("System", FontWeight.NORMAL, BODY_FONT_SIZE);
    Font headerFont = Font.font("System", FontWeight.BOLD, HEADER_FONT_SIZE);

    Text creatorText = new Text(post.getCreator() + "\t");
    Text dateText = new Text(post.getDate() + "\n");
    Text headerText = new Text(post.getHeader() + "\n");
    Text bodyText = new Text(post.getBody());

    creatorText.setFont(bodyFont);
    dateText.setFont(bodyFont);
    headerText.setFont(headerFont);
    bodyText.setFont(bodyFont);

    textFlow.getChildren().addAll(creatorText, dateText, headerText, bodyText);

    VBox parent = new VBox();
    String likeLable = "Like";
    if (post.getLikes() > 0) {
      likeLable = "Likes (" + post.getLikes() + ")";
    }
    Button likeButton = new Button(likeLable);
    likeButton.setOnAction(event -> handleLikes(post, likeButton));

    parent.getChildren().addAll(textFlow, likeButton);

    parent.setPadding(new Insets(POST_Y_MARGIN, POST_X_MARGIN,
        POST_Y_MARGIN, POST_X_MARGIN));

    return parent;
  }

  /**
   * Sets the app reference.
   *
   * @param app The app that is running.
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
