package com.example.websocket.websocket;

import com.example.websocket.Entity.Message;
import com.example.websocket.Mapper.MessageMapper;
import com.example.websocket.util.BeanUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint("/chat/{userId}")
public class WebSocketChatHandler {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArrayList<WebSocketChatHandler> clients = new CopyOnWriteArrayList<>();
    private Session session;
    private String userId;
    private static Set<String> onlineUsers = new HashSet<String>();
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.onlineUsers.add(userId);
        this.session = session;
        this.userId = userId;
        System.out.println(userId);
        clients.add(this);

        // 广播在线用户列表给所有连接的用户
        broadcastOnlineUsers();
    }

    private void broadcastOnlineUsers() {
        try {
            // 构建在线用户列表的 JSON 字符串
            String onlineUsersJson = buildOnlineUsersJson();

            // 遍历所有连接的客户端并发送消息
            for (WebSocketChatHandler client : clients) {
                client.session.getBasicRemote().sendText(onlineUsersJson);
            }
        } catch (IOException e) {
            // 处理异常
        }
    }

    private String buildOnlineUsersJson() {
        // 构建在线用户列表的 JSON 字符串
        // 例如：{"onlineUsers": ["User1", "User2", "User3"]}
        // 可以使用 JSON 库如 Jackson 或 Gson 来构建 JSON 数据
        // 这里简单示范，具体构建方式根据您的需求来定义
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"onlineUsers\": [");
        for (String user : onlineUsers) {
            jsonBuilder.append("\"").append(user).append("\",");
        }
        // 移除末尾多余的逗号
        if (onlineUsers.size() > 0) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        }
        jsonBuilder.append("]");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }


    @OnMessage
    public void onMessage(String message) {
        // 处理收到的消息
        System.out.println(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String targetUserId = jsonNode.get("targetUserId").asText();
            String senderId = jsonNode.get("senderId").asText();
            String content = jsonNode.get("content").asText();

            // 创建消息实体
            Message chatMessage = new Message();
            chatMessage.setSenderId(senderId);
            chatMessage.setReceiverId(targetUserId);
            chatMessage.setContent(content);
            chatMessage.setTimestamp(new Date());// 设置当前时间为消息时间
            chatMessage.setIfread("未读");
            MessageMapper messageMapper = BeanUtil.getMessageMapper();

            // 保存消息到数据库
            messageMapper.insertMessage(chatMessage);


            // 遍历所有连接的WebSocket会话，找到目标用户并发送消息
            for (WebSocketChatHandler client : clients) {
                if (client.userId.equals(targetUserId)) {
                    try {
                        synchronized (client) {
                            client.session.getBasicRemote().sendText(message);
                        }
                    } catch (IOException e) {
                        // 处理异常
                    }
                }
            }
        } catch (IOException e) {
            // 处理消息解析异常
        }
    }


    @OnClose
    public void onClose() {
        clients.remove(this);
    }

    private void broadcast(String message) {
        for (WebSocketChatHandler client : clients) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                // 处理异常
            }
        }
    }
}
