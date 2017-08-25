package me.dev1001.coin.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author hongzong.li
 */
public class HttpClientHelper {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static <T> T postForObject(String url, Map<String, String> params, Class<T> clazz)
        throws IOException {
        RequestBuilder postBuilder = RequestBuilder.post().setUri(getURI(url));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            postBuilder.addParameter(entry.getKey(), entry.getValue());
        }
        HttpUriRequest post = postBuilder.build();
        return httpClient.execute(post, response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? JSON.parseObject(EntityUtils.toString(entity), clazz) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        });
    }

    private static URI getURI(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Invalid url address: %s", url), e);
        }
    }
}
