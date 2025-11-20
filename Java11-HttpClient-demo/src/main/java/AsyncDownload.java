import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * @author leejiawei
 */
public class AsyncDownload {
    public static void main(String[] args) throws Exception{
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/image/jpeg"))
                .build();
//        CompletableFuture<Path> result= client
//                .sendAsync(request, HttpResponse.BodyHandlers.ofFile(Path.of("src/main/resources/imgs.jpeg")))
//                .thenApply(HttpResponse::body);

        CompletableFuture<InputStream> future= client
                .sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body);

        System.out.println(future.get());

//        System.out.println(result.get());

    }
}
