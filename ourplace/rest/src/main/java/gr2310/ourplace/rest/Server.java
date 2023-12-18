package gr2310.ourplace.rest;

import java.util.Stack;

import gr2310.ourplace.json.FileManager;
import gr2310.ourplace.core.Post;

public class Server implements ServerInterface {
  /** FileManager. */
  private FileManager fileManager;

  /**
   * Create a Server with FileManager set to write to ${databaseName}.json.
   * @param databaseName Database name.
  */
  public Server(final String databaseName) {
    // this.databaseName = databaseName;
    final String fileName = databaseName + ".json";
    fileManager = new FileManager(fileName);
  }

  /** Stack of all the posts in the feed. */
  private Stack<Post> feed = new Stack<Post>();

  /**
   * Is the Server empty? (No posts in feed)
   * @return boolean
  */
  public boolean isEmpty() {
    return feed.isEmpty();
  }

  /**
   * Calls FileManager and writes the post to the feed.
   *
   * @param post the post to be published.
   */
  public void publish(final Post post) {
    post.prepareToPublish();
    post.setCreator("Anonymous");
    feed.push(post);
    save();
  }

  /**
   * Gets all the posts from the feed.
   *
   * @return the stack of posts in the feed.
   */
  @SuppressWarnings("unchecked")
  public Stack<Post> fetchAll() {
    feed = fileManager.getPosts();

    return (Stack<Post>) feed.clone();
  }

  /**
   * Used in testing.
   * Deletes the last post that was published to the feed.
   */
  public void deleteLast() {
    feed.pop();
    save();
  }
  /**
   * Delegates storage operations to FileManager.
   */
  public void save() {
    fileManager.writePosts(feed);
  }
}
