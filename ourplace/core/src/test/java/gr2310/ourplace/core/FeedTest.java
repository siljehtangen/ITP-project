package gr2310.ourplace.core;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedTest {
    /**Post used in testing.*/
    public Post post1 = Post.create("Anonymous", "This is a header text", "This is a body text");
    /**Post used in testing.*/
    public Post post2 = Post.create("Anonymous", "This is a second header text", "This is a second body text");
    
    /**Tests if the posts gets sorted from newest date to oldest.*/
    @Test
    void testSortByDateAscending() {
        Stack<Post> testFeed = createTestFeed();
        Stack<Post> sortedTestFeed = Feed.sort(testFeed, FeedSort.dateAscending);
        assertEquals(post2, sortedTestFeed.peek());
    }

    /**Tests if the posts gets sorted from most likes to fewest.*/
    @Test
    void testSortByLikesAscending() {
        Stack<Post> testFeed = createTestFeed();
        post1.incrementLikes();
        Stack<Post> sortedTestFeed = Feed.sort(testFeed, FeedSort.likesAscending);
        assertEquals(post1, sortedTestFeed.peek());
    }

    /**
     * Helping method. Makes a feed based on the two test posts.
     * @return feed of posts.
     */
    private Stack<Post> createTestFeed() {
        Stack<Post> feed = new Stack<>();
        feed.push(post1);
        feed.push(post2);
        return feed;
    }
}

