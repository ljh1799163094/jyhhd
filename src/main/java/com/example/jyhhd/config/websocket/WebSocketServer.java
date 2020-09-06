package com.example.jyhhd.config.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.RestController;


@ServerEndpoint(value = "/webSocket/{id}/{name}")
@RestController
public class WebSocketServer {

    public WebSocketServer() {
    }

    // 用来记录当前连接数的变量
    private static volatile int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    // 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    public Session session;


    @OnOpen
    public void onOpen(Session session, @PathParam("id") long id, @PathParam("name") String name) throws Exception {
        this.session = session;
        webSocketSet.add(this);

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        sendMessage(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
    }

    public void sendMessage(String message) throws Exception {
        if (this.session != null && this.session.isOpen()) {
            this.session.getBasicRemote().sendText(message, true);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}