package gr2310.ourplace.core;

import java.time.LocalDate;
import java.util.UUID;

public class Post {
  /** The id of the post. */
  private String uuid;
  /** The name of the person who made the post. */
  private String creator;
  /** The text in the post's header. */
  private String header;
  /** The text in the post's body. */
  private String body;
  /** The string representation of the date the post was made. */
  private String date;
  // private Comment[] comments; (Coming soon)
  /** The number of likes a post have. */
  private int likes;
  /** The boolean representation of whether the post has been liked or not. */
  private boolean liked;

  /** Public empty constructor. */
  public Post() {
  }

  /** Value of the ending to shorten the uuid. */
  private static final int END_INDEX_UUID = 5;

  /**
   * Create a <b>Post</b>. Date is not assigned until you publish the post.
   *
   * @param creator Username of Post creator
   * @param header  Post header/title
   * @param body    Post body/content
   *
   * @return the post that was made
   */
  public static Post create(final String creator, final String header,
      final String body) {
    Post post = new Post();

    post.creator = creator;
    post.header = header;
    post.body = body;

    post.uuid = UUID.randomUUID().toString().substring(0, END_INDEX_UUID);

    return post;
  }

  /**
   * @return the creator of the post.
   */
  public String getCreator() {
    return creator;
  }

  /**
   * @return the header of the post.
   */
  public String getHeader() {
    return header;
  }

  /**
   * @return the body of the post.
   */
  public String getBody() {
    return body;
  }

  // public Comment[] getComments() {
  // return comments; (Coming soon.)
  // }

  /**
   * @return the date of the post.
   */
  public String getDate() {
    return date;
  }

  /** Set <b>Post.date</b> to current date and id. */
  public void prepareToPublish() {
    date = LocalDate.now().toString();
  }

  /**
   * Sets the posts creator.
   *
   * @param creator the creator to be set
   */
  public void setCreator(final String creator) {
    this.creator = creator;
  }

  /**
   *
   * @return number of likes of the post.
   */
  public int getLikes() {
    return likes;
  }

  /**
   * Increments likes.
   */
  public void incrementLikes() {
    likes++;
  }

  /**
   *
   * @return whether the post has been liked or not.
   */
  public boolean isLiked() {
    return liked;
  }

  /**
   * Sets the boolean value liked.
   *
   * @param liked
   */
  public void setLiked(final boolean liked) {
    this.liked = liked;
  }

  /**
   *
   * @return the post id.
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * Sets the post id to id.
   *
   * @param id of the post.
   */
  public void setUuid(final String id) {
    this.uuid = id;
  }
}
