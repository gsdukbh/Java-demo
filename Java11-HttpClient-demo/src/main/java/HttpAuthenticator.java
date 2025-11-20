import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author leejiawei
 */
public class HttpAuthenticator {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("user","passwd".toCharArray());
                    }
                })
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/basic-auth/user/passwd"))
                .build();

            HttpResponse<String> httpResponse= client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("httpResponse :"+httpResponse.body());

    }
}
