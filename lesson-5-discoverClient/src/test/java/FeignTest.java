import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-28 下午3:00
 */
public class FeignTest {

    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
    }

    public static void main(String... args) {
        GitHub github = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("tengxing", "Spring-Cloud-Learn");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }
}
