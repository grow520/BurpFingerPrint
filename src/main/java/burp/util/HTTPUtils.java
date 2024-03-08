package burp.util;

import burp.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author： shaun
 * @create： 2024/3/6 20:44
 * @description：TODO
 */
public class HTTPUtils {
    public static Map<String, Object> makeGetRequest(String getUrl) {
        // 解析URL
        String host;
        int port;
        String protocol;
        String path;
        try {
            // 创建URL对象
            URL url = new URL(getUrl);
            // 获取protocol、host、port、path
            protocol = url.getProtocol();
            host = url.getHost();
            port = url.getPort();
            if (port == -1 && protocol.equalsIgnoreCase("http")){
                port = 80;
            } else if (port == -1 && protocol.equalsIgnoreCase("https")) {
                port = 443;
            }
            path = url.getPath();
            // 分析URL
        } catch (Exception e) {
            // 处理可能出现的MalformedURLException
            BurpExtender.stdout.println("Invalid URL: " + getUrl);
            return null;
        }
        // 创建IHttpService对象
        IHttpService httpService = BurpExtender.helpers.buildHttpService(host, port, protocol);

        // 构造GET请求的字节数组
        String request = "GET " + path + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36" + "\r\n" +
                "\r\n";
        byte[] requestBytes = request.getBytes();

        // 发起请求
        IHttpRequestResponse response = BurpExtender.callbacks.makeHttpRequest(httpService, requestBytes);

        // 当前请求的URL，requests，Response，以及findUrl来区别是否为提取出来的URL
        Map<String, Object> originalData = new HashMap<String, Object>();
        originalData.put("responseRequest", response);
        originalData.put("isFindUrl", true);
        originalData.put("method", "GET");

        return originalData;

    }
}
