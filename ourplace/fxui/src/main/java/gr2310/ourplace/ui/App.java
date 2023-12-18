package gr2310.ourplace.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * JavaFX App.
 */
public class App extends Application {
    /** Mappings from filenames to URLs. */
    private static HashMap<String, URL> sceneMap = new HashMap<>();
    /** The current stage. */
    private Stage stage;

    /**
     * Looks for requested view file and saves url in sceneMap.
     *
     * @param filename The requested view file
     * @throws IOException If view file doesn't exist
     */
    private void loadScene(final String filename) throws IOException {
        URL url = getClass().getResource(filename);

        if (url == null) {
            throw new IllegalArgumentException("No scene with filename "
                    + filename + " exists.");
        }

        sceneMap.put(filename, url);
    }

    /**
     * Looks for the requested view file in sceneMap and changes the view if it
     * exists.
     *
     * @param filename The requested view file
     * @return The scene that was switched to
     * @throws IOException If requested scene is not in sceneMap
     */
    public Scene setScene(final String filename) throws IOException {
        URL url = sceneMap.get(filename); // The URL of the fxml file
        if (url == null) {
            throw new IllegalArgumentException("No scene with filename "
                    + filename + " has been preloaded.");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        // Get the controller associated with the loaded FXML
        Controller controller = loader.getController();

        // Set this App instance as the controller's reference
        controller.setApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return scene;
    }

    /**
     * Initializes the JavaFX application by setting up the primary stage,
     * loading scene files, and setting the initial scene.
     *
     * @param stage The primary stage for the JavaFX application.
     * @throws IOException              If there is an issue loading the scene
     *                                  files.
     * @throws IllegalArgumentException If the provided stage is null.
     */

    @Override
    public void start(final Stage stage) throws IOException {
        if (stage != null) {
            this.stage = stage;

            try {
                loadScene("/Feed.fxml");
                loadScene("/Post.fxml");
            } catch (IOException e) {
                System.out.println("Missing scene file(s)");
                System.err.println(e.getMessage());
                throw e;
            }

            this.stage.setTitle("Ourplace");
            this.setScene("/Feed.fxml");
        } else {
            throw new IllegalArgumentException("Stage is null");
        }
    }

    /**
     * Launches the app.
     *
     * @param args main method arguments
     */
    public static void main(final String[] args) {
        launch();
    }
}
