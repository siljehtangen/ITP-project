package gr2310.ourplace.rest;

import org.junit.jupiter.api.Test;
import gr2310.ourplace.core.Post;
import java.util.Stack;

public class ServerTest {
    /** Tests if the stack updates after a new post */
    @Test
    public void testFileValid() {
        // Fetches the posts already in the stack and checks the size
        final Server server = new Server("test-posts");
        Stack<Post> startStack = server.fetchAll();
        int startStackSize = startStack.size();

        // Publishes a new post
        server.publish(Post.create("TestName", "TestHeader", "TestBody"));

        // Reads the updated stack and checks the size
        Stack<Post> updatedStack = server.fetchAll();
        int updatedStackSize = updatedStack.size();

        // Compare the old stack with the new updated one
        if (updatedStackSize == (startStackSize + 1)) {
            System.out.println("The stack is updated");
        } else {
            System.err.println("Error: The stack is not updated");
        } 

        // Deletes the test post so it will be no consequences for the user
        server.deleteLast();
    }
}

