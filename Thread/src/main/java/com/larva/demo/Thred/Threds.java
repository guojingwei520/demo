package com.larva.demo.Thred;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-25 15:59
 * @describe:
 **/
public class Threds {
    public void out(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        final Threds o = new Threds();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程1
                while(true){
                    o.out("1111");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程2
                while(true){
                    o.out("2222");
                }
            }
        }).start();
    }
}
