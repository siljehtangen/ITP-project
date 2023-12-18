package gr2310.ourplace.client;

import java.util.Arrays;
import java.util.Stack;
import java.util.List;

import gr2310.ourplace.core.Post;

import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public final class RestClient {
    /** Decides server endpoint. */
    public static final String ENDPOINT = "http://localhost:8080";

    /**
     * Private empty constructor.
     */
    private RestClient() {
    }

    /**
     * Publish a post to the server.
     *
     * @param post the post to be published
     */
    public static void publishPost(final Post post) {
        // Create RestTemplate
        RestTemplate rest = new RestTemplate();

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP Entity with the post as the request body
        HttpEntity<Post> request = new HttpEntity<Post>(post, headers);

        // Make the POST request
        String url = RestClient.ENDPOINT + "/posts/add";
        rest.postForObject(url, request, Void.class);
    }

    /**
     * Get all posts.
     *
     * @return The retrieved posts as a Stack<Post>
     */
    public static Stack<Post> getPosts() {
        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        final String url = RestClient.ENDPOINT + "/posts/get";

        // Get response entity
        var response = restTemplate.getForEntity(url, Post[].class);

        // Retrieve body from response entity
        Post[] posts = response.getBody();

        // In case feed is empty, create an empty feed
        if (posts == null) {
            posts = new Post[0];
        }

        // Convert array to a List
        List<Post> postList = Arrays.asList(posts);

        // Create a stack
        Stack<Post> postStack = new Stack<>();

        // Push elements from List to Stack
        postStack.addAll(postList);

        return postStack;
    }

    /**
     * Like a post on the server.
     *
     * @param post the post to be liked
     */
    public static void likePost(final Post post) {
        // Create RestTemplate
        RestTemplate rest = new RestTemplate();

        // Make the PUT request
        String url = RestClient.ENDPOINT + "/posts/like?uuid=" + post.getUuid();
        rest.put(url, null);
    }

    /** Delete last post published to Ourplace. */
    public static void deletePost() {
        // Create RestTemplate
        RestTemplate rest = new RestTemplate();

        final String url = RestClient.ENDPOINT + "/posts/delete";

        // rest.delete(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        rest.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
    }
}
