package com.github.easyzhang.manyrequesttest;

public class ManyRequestTest {

    public static void main(String[] args) {
        for(int i=1;i<=100;i++) {
            new Thread(new RequestTask(i*10000000000000000L,i*10000000000000000L+999)).start();
        }
    }
}
