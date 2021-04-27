import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @author leejiawei
 */

public class HttpConnectTimeoutException {
    public static void main(String[] args) throws Exception {
//    设置连接,
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(2000))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
//        设置请求，
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://google.com/ip"))
                .timeout(Duration.ofMillis(1500))
                .build();
//       响应,使用同步发送请求
        HttpResponse<String> response = null;

        response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        assert response != null;
        System.out.println("response: " + response.body());
    }

}
