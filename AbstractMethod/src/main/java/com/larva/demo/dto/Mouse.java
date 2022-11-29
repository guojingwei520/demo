package com.larva.demo.dto;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-27 9:36
 * @describe:
 **/
public class Mouse {
    int left;
    int right;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Mouse(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
