package gr2310.ourplace.ui;

/** PostResult holds a group of constant values used for PostController. */
public enum PostResult {
  /**This holds the empty result. */
  empty,
  /**This holds the result for header is longer than 50 character limit. */
  headerLong,
  /**This holds the result for body is longer than 250 character limit. */
  bodyLong,
  /**This holds the success (the post is published). */
  success,
}
