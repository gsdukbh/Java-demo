import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author leejiawei
 */
public class HeaderSet {
    public static void main(String[] args) throws  Exception {
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/bearer"))
                .header("Authorization","Bearer google")
                .build();

            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("httpResponse:" +httpResponse.body());

    }
}
