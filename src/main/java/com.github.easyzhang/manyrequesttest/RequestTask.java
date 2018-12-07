package com.github.easyzhang.manyrequesttest;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;

/**
 * @author EasyZhang
 * @date 2018/12/7 -  15:05
 */

public class RequestTask implements Runnable {

    private static final String BASE_URL = "up-manager75.djtest.cn";
    private static final String url = "https://up-manager75.djtest.cn/order/getOrderById?id=";

    private long idStart;
    private long idEnd;

    public RequestTask(long idStart,long idEnd){
        this.idStart = idStart;
        this.idEnd = idEnd;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"启动");
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie city = new BasicClientCookie("cityIds", "1,2");
        city.setVersion(0);
        city.setDomain(BASE_URL);   //设置范围
        city.setPath("/");
        cookieStore.addCookie(city);

        BasicClientCookie name = new BasicClientCookie("user", Thread.currentThread().getName());
        name.setVersion(0);
        name.setDomain(BASE_URL);   //设置范围
        name.setPath("/");
        cookieStore.addCookie(name);

        BasicClientCookie uid = new BasicClientCookie("uid", "123");
        uid.setVersion(0);
        uid.setDomain(BASE_URL);   //设置范围
        uid.setPath("/");
        cookieStore.addCookie(uid);

        BasicClientCookie phone = new BasicClientCookie("phone", "13012341234");
        phone.setVersion(0);
        phone.setDomain(BASE_URL);   //设置范围
        phone.setPath("/");
        cookieStore.addCookie(phone);

        while(idStart<idEnd){
            //第一步：拿到httpClient 对象
            HttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
            HttpGet httpGet = new HttpGet(url + idStart);
            try {
                httpClient.execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            idStart++;
        }

    }
}
