package gr2310.ourplace.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import gr2310.ourplace.core.Post;
import org.junit.jupiter.api.*;

import java.util.Stack;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestClientTest {
  private WireMockServer wireMockServer;
  private static ObjectMapper objectMapper;

  /**
   * Before running tests. Makes and starts a new Wiremock server on port 8080.
   */
  @BeforeAll
  void setUp() {
    objectMapper = new ObjectMapper();
    wireMockServer = new WireMockServer(options().port(8080));
    wireMockServer.start();
  }

  /** Stops the server after all the tests have run. */
  @AfterAll
  void stopWireMockServer() {
    wireMockServer.stop();
  }

  /**
   * Checks if it makes a correct HTTP POST request to a specific URL path
   * ("/posts/add").
   */
  @Test
  void publishPostRequestOK() {
    stubPublicPostRequest();
    verify(exactly(0), postRequestedFor(urlPathMatching("/posts/add")));
    RestClient.publishPost(Post.create("creator", "header", "body"));
    verify(moreThanOrExactly(1), postRequestedFor(urlPathMatching("/posts/add")));
  }

  /** Checks that RestClient.getPosts() behaves correctly based on request. */
  @Test
  void getPostsRequestOK() throws JsonProcessingException {
    Post[] posts = new Post[2];
    posts[0] = Post.create("creator1", "header1", "header1");
    posts[1] = Post.create("creator2", "header2", "header2");
    stubGetPostsRequest(posts);
    Stack<Post> requestResult = RestClient.getPosts();
    Assertions.assertEquals(2, requestResult.size());
    Assertions.assertNotEquals(requestResult.get(0).getUuid(), requestResult.get(1).getUuid());
    verify(moreThanOrExactly(1), getRequestedFor(urlPathMatching("/posts/get")));
  }

  /**
   * Checks that RestClient.getPosts() behaves correctly when there are no posts
   * returned from the request.
   */
  @Test
  void getPostsNoPostsFromRequest() throws JsonProcessingException {
    stubGetPostsRequest(null);
    Stack<Post> requestResult = RestClient.getPosts();
    Assertions.assertEquals(0, requestResult.size());
    verify(moreThanOrExactly(1), getRequestedFor(urlPathMatching("/posts/get")));
  }

  /**
   * checking that the RestClient.likePost method correctly makes an HTTP PUT
   * request to a specific URL path ("/posts/like") when called.
   */
  @Test
  void likePostResponseOK() {
    stubPostLikesRequest();
    RestClient.likePost(Post.create("creator", "header", "body"));
    verify(moreThanOrExactly(1), putRequestedFor(urlPathMatching("/posts/like")));
  }

  /**
   * Ensures that the RestClient.likePost correctly makes an HTTP PUT request to a
   * specific URL
   * path ("/posts/like") with the correct UUID of the post.
   */
  @Test
  void likePostPutOnCorrectPostId() {
    stubPostLikesRequest();
    Post post = Post.create("creator", "header", "body");
    verify(exactly(0), putRequestedFor(urlEqualTo("/posts/like?uuid=" + post.getUuid())));
    RestClient.likePost(post);
    verify(exactly(1), putRequestedFor(urlEqualTo("/posts/like?uuid=" + post.getUuid())));
  }

  /**
   * Methods used only in test scenario to tell what response we expects after a
   * request.
   */
  private void stubPublicPostRequest() {
    wireMockServer.stubFor(WireMock.post(urlPathMatching("/posts/add"))
        .willReturn(aResponse().withHeader("Content-Type", "application/json;charset=UTF-8").withStatus(200)));
  }

  /**
   * Methods used only in test scenario to tell what response we expects after a
   * request.
   * 
   * @param posts input, empty post array.
   */
  private void stubGetPostsRequest(Post[] posts) throws JsonProcessingException {
    wireMockServer.stubFor(WireMock.get(urlPathMatching("/posts/get"))
        .willReturn(aResponse().withHeader("Content-Type", "application/json;charset=UTF-8").withStatus(200)
            .withBody(objectMapper.writeValueAsString(posts))));
  }

  /**
   * Methods used only in test scenario to tell what response we expects after a
   * request.
   */
  private void stubPostLikesRequest() {
    wireMockServer.stubFor(WireMock.put(urlPathMatching("/posts/like"))
        .willReturn(aResponse().withHeader("Content-Type", "application/json;charset=UTF-8").withStatus(200)));
  }
}
