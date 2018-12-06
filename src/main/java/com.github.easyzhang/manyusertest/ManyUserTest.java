package com.github.easyzhang.manyusertest;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;

public class ManyUserTest {

    private static final String url = "https://up-manager75.djtest.cn/order/getOrderById?id=181213405510691012";

    public static void main(String[] args) throws IOException {
        for(int i=0;i<300;i++) {

            CookieStore cookieStore = new BasicCookieStore();

            BasicClientCookie city = new BasicClientCookie("cityIds", "1,2");
            city.setVersion(0);
            city.setDomain("up-manager75.djtest.cn");   //设置范围
            city.setPath("/");
            cookieStore.addCookie(city);

            BasicClientCookie name = new BasicClientCookie("user", (i+1200)+"");
            name.setVersion(0);
            name.setDomain("up-manager75.djtest.cn");   //设置范围
            name.setPath("/");
            cookieStore.addCookie(name);

            BasicClientCookie uid = new BasicClientCookie("uid", "123");
            uid.setVersion(0);
            uid.setDomain("up-manager75.djtest.cn");   //设置范围
            uid.setPath("/");
            cookieStore.addCookie(uid);

            BasicClientCookie phone = new BasicClientCookie("phone", "13012341234");
            phone.setVersion(0);
            phone.setDomain("up-manager75.djtest.cn");   //设置范围
            phone.setPath("/");
            cookieStore.addCookie(phone);

            //第一步：拿到httpClient 对象
            HttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
            //第二步：搞一个get对象
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            try {
                Thread.sleep(999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
