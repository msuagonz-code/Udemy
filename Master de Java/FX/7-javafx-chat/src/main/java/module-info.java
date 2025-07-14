module org.sam.javafx.chatapp.appjavafxchat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.net.http;
    requires spring.websocket;
    requires spring.messaging;
    requires com.fasterxml.jackson.databind;
    requires java.logging;


    opens org.sam.javafx.chatapp.appjavafxchat.models to javafx.base, com.fasterxml.jackson.databind;
    exports org.sam.javafx.chatapp.appjavafxchat;
}