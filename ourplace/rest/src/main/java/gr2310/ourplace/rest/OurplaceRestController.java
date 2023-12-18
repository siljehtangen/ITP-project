package gr2310.ourplace.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr2310.ourplace.core.Post;

@RestController
public class OurplaceRestController {
    /**
     * Default constructor. Will set databaseName to "posts".
    */
    public OurplaceRestController() {
        server = new Server("posts");
    }

    /**
     * Constructor for creating a RestController with a custom databaseName.
     * @param databaseName Database name.
    */
    public OurplaceRestController(final String databaseName) {
        server = new Server(databaseName);
    }

    /**
     * Server instance used by OurplaceRestController.
     * */
    private ServerInterface server;

    /**
     * Publish a post to Ourplace.
     *
     * @param post The post to be published.
     * @return ResponseEntity<Void>
     */
    @PostMapping(value = "/posts/add", consumes = "application/json")
    public ResponseEntity<Void> publish(@RequestBody final Post post) {
        server.publish(post);
        return ResponseEntity.ok().build(); // Return a 200 OK response
    }

    /**
     * Fetch all posts from Ourplace.
     *
     * @return ResponseEntity<Post[]>
     */
    @GetMapping(value = "/posts/get", produces = "application/json")
    public ResponseEntity<List<Post>> fetchAll() {
        Stack<Post> postStack = server.fetchAll();
        List<Post> postList = new ArrayList<>(postStack);
        return ResponseEntity.ok().body(postList);
    }

    /**
     * Delete the last post from Ourplace.
     *
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(value = "/posts/delete")
    public ResponseEntity<Void> deletePost() {
        if (!server.isEmpty()) {
            server.deleteLast(); // Delete last post and save
        }

        return ResponseEntity.ok().build();
    }

    /**
     * PUT request to increment the number of likes.
     *
     * @param uuid ID of the Post you want to like.
     * @return ResponseEntity<Void>
     */
    @PutMapping(value = "/posts/like")
    public ResponseEntity<Void> like(@RequestParam final String uuid) {
        Stack<Post> postStack = server.fetchAll();
        for (Post post : postStack) {
            if (post.getUuid().equals(uuid)) {
                post.incrementLikes();
                break;
            }
        }
        server.save();

        return ResponseEntity.ok().build();
    }
}
