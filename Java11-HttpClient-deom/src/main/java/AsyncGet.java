import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * @author leejiawei
 */
public class AsyncGet {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/get"))
                .build();

        long time = System.currentTimeMillis();
        CompletableFuture<String> result = client.
                sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        System.out.println(" time : " + (System.currentTimeMillis() - time) + result.get());

    }
}
