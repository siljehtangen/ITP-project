package gr2310.ourplace.json;

import gr2310.ourplace.core.Post;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class FileManagerTest {
  private FileManager fileManager = new FileManager("test-posts.json");

  /** Tests if the FileManager class is reading and writing correctly */
  @Test
  public void testReadingAndWritingFromFile() throws IOException {
    // Fetches the posts already in the stack

    Stack<Post> stack1 = fileManager.getPosts();

    // If its no posts in stack, then we are finished
    if (stack1.size() == 0) {
      return;
    }

    // Gets the top (most recently) post from the stack
    Post topMost1 = stack1.peek();
    // Writes the whole stack to the FileManager class
    fileManager.writePosts(stack1);

    // Fetches the written posts in the json file
    Stack<Post> stack2 = fileManager.getPosts();
    // Gets the top (most recently) post from the updated stack from the json file
    Post topMost2 = stack2.peek();

    // Compares the stack already in file with the latest stack that has been
    // written to file
    assertEquals(topMost1.getCreator(), topMost2.getCreator());
    assertEquals(topMost1.getHeader(), topMost2.getHeader());
    assertEquals(topMost1.getBody(), topMost2.getBody());
  }
}
