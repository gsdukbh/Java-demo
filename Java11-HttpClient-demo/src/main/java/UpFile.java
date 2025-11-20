import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author leejiawei
 */
public class UpFile {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/anything"))
//                .header("Content-Type", "multipart/form-data;boundary=")
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("src/main/resources/FX_GmlG_RIA.jpg")))
                .build();

        CompletableFuture<Object> result = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        System.out.println("result:" + result.get());
    }

}
