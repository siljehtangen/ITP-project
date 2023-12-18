package gr2310.ourplace.core;

import java.util.Comparator;
import java.util.Stack;

/**Interface for getting the feed. */
public abstract class Feed {
  /**
   *
   * @param feed is Stack of posts.
   * @param sort type: By likes or dates.
   * @return a sorted copy of the feed based on sort input.
   */
  public static Stack<Post> sort(final Stack<Post> feed, final FeedSort sort) {
    final Stack<Post> copy = new Stack<Post>();
    copy.addAll(0, feed);
    switch (sort) {
      case dateAscending:
        return dateAscending(copy);
      case likesAscending:
        return likesAscending(copy);
      default:
        return copy;
    }
  }

  /**
   *
   * @param input the stack of posts to be sorted.
   * @return the post because it's already sorted by date from newest to oldest.
   */
  private static Stack<Post> dateAscending(final Stack<Post> input) {
    return input;
  }
  /**
   *
   * @param input the stack of posts to be sorted.
   * @return the stack sorted by most to fewest likes.
   */
   private static Stack<Post> likesAscending(final Stack<Post> input) {
    final Comparator<Post> postComparator =
        Comparator.comparing(Post::getLikes);
    input.sort(postComparator);
    return input;
  }
}
