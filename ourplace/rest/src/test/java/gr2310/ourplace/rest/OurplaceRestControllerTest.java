package gr2310.ourplace.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2310.ourplace.core.Post;

@SpringBootTest(classes = OurplaceRestController.class)
@AutoConfigureMockMvc
public class OurplaceRestControllerTest {

    // @Autowired
    // private MockMvc mockMvc;
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new OurplaceRestController("test-posts")).build();

    @MockBean
    private Server server;

    @Test
    public void testPublishPost() throws Exception {
        // Create a sample Post object
        Post post = Post.create("Erlend", "TestHeader", "TestBody");

        // Serialize the Post object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(post);

        MockHttpServletRequestBuilder requestBuilder = post("/posts/add")
                .content(jsonPayload)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testFetchAllPosts() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        // Perform GET request to /posts/get
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/posts/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Deserialize the JSON response into a List<Post>
        List<Post> posts = objectMapper.readValue(responseContent, new TypeReference<List<Post>>() {});

        // Assert or validate the retrieved posts as needed
        // For example, check the size of the list or specific properties of posts
        // For instance, if you want to check that at least one post is retrieved:
        assert !posts.isEmpty();
    }
    
    @Test
    public void testLikePost() throws Exception {
        String postUuid = "test1"; // Replace with an actual UUID
        
        mockMvc.perform(MockMvcRequestBuilders.put("/posts/like")
        .param("uuid", postUuid)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}
