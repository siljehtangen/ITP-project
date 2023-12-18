package gr2310.ourplace.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2310.ourplace.core.Post;

public final class FileManager {
    /** File name with extension. */
    private String fileName;

    /**
     * Create a FileManager that modifies file with name "fileName".
     * @param fileName File name with extension.
    */
    public FileManager(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Set target file name.
     * @param name File name with extension.
    */
    public void setFileName(final String name) {
        fileName = name;
    }

    /**
     * Return relative file path.
     *
     * @return the path to posts.json, located in user's user.home.
     */
    public String getPath() {
        Path path = Paths.get(System.getProperty("user.home"), "/" + fileName);
        return path.toString();
    }

    /**
     * Read and get feed from file.
     *
     * @return the stack of posts
     */
    public Stack<Post> getPosts() {
        ObjectMapper mapper = new ObjectMapper();
        Stack<Post> posts = new Stack<>();

        try {
            File file = new File(getPath());
            if (file.exists()) {
                posts = mapper.readValue(file,
                        new TypeReference<Stack<Post>>() {
                        });
            } else {
                System.err.println("posts.json not found or is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }

    /**
     * Writes the feed post stack to file.
     *
     * @param posts the feed to be written to file
     */
    public void writePosts(final Stack<Post> posts) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(getPath());
            mapper.writeValue(file, posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
