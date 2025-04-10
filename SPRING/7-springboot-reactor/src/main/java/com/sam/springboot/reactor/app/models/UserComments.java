package com.sam.springboot.reactor.app.models;

public class UserComments {
    private User user;
    private Comments comments;

    public UserComments(User user, Comments comments) {
        this.user = user;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "{" +
                "user: " + user +
                ", comments: " + comments +
                '}';
    }
}
