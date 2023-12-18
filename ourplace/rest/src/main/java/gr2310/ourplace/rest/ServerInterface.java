package gr2310.ourplace.rest;

import java.util.Stack;
import gr2310.ourplace.core.Post;

public interface ServerInterface {
  /**
   * Is the Server empty? (No posts in feed)
   * @return boolean
  */
  boolean isEmpty();

  /**
   * Publish a post to Ourplace.
   * @param post Post to be published.
   * */
  void publish(Post post);

  /**
   * Fetch all posts from Ourplace.
   * @return All posts posted to Ourplace - ever.
   * */
  Stack<Post> fetchAll();

  /** Delete last post published to Ourplace. */
  void deleteLast();

  /** Save posts. */
  void save();
}
