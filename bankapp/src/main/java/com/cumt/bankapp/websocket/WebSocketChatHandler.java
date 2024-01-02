package com.cumt.bankapp.websocket;

import com.cumt.bankapp.domain.*;
import com.cumt.bankapp.mapper.MessageMapper;
import com.cumt.bankapp.service.ITransferMoneyService;
import com.cumt.bankapp.service.IUserInformationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/chat/{id}")
@Component
public class WebSocketChatHandler {

    private static ITransferMoneyService tms ;
    private static IUserInformationService uis ;


    @Autowired
    public void setIUserInformationService(IUserInformationService uis) {
        WebSocketChatHandler.uis = uis;
    }

    @Autowired
    public void setITransferMoneyService(ITransferMoneyService tms) {
        WebSocketChatHandler.tms = tms;
    }

    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;

//    private Session session ;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArrayList<WebSocketChatHandler> clients = new CopyOnWriteArrayList<>();

    private String userId;
    private static Set<String> onlineUsers = new HashSet<String>();


    @OnOpen
    public void onOpen(Session session, @PathParam("id") String userId) {

        System.out.println("客户端：" + userId + "建立连接");
        sessionMap.put(userId, session);

    }

//    private void broadcastOnlineUsers() {
//        try {
//            // 构建在线用户列表的 JSON 字符串
//            String onlineUsersJson = buildOnlineUsersJson();
//
//            // 遍历所有连接的客户端并发送消息
//            for (Session client : sessionMap.values()) {
//                client.getBasicRemote().sendText(onlineUsersJson);
//            }
//        } catch (IOException e) {
//            // 处理异常
//        }
//    }

//    private String buildOnlineUsersJson() {
//        // 构建在线用户列表的 JSON 字符串
//        // 例如：{"onlineUsers": ["User1", "User2", "User3"]}
//        // 可以使用 JSON 库如 Jackson 或 Gson 来构建 JSON 数据
//        // 这里简单示范，具体构建方式根据您的需求来定义
//        StringBuilder jsonBuilder = new StringBuilder();
//        jsonBuilder.append("{");
//        jsonBuilder.append("\"onlineUsers\": [");
//        for (String user : onlineUsers) {
//            jsonBuilder.append("\"").append(user).append("\",");
//        }
//        // 移除末尾多余的逗号
//        if (onlineUsers.size() > 0) {
//            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
//        }
//        jsonBuilder.append("]");
//        jsonBuilder.append("}");
//        return jsonBuilder.toString();
//    }


    @OnMessage
    public void onMessage(String message) {
//        System.out.println(message);
        JSONObject jsonObject = new JSONObject(message);

        if (jsonObject.has("temp")&&jsonObject.getInt("temp")==1) {
            String text = message;
            System.out.println(jsonObject);
            String toAccount = jsonObject.getString("toAccount");
            System.out.println(toAccount);
            String id = uis.selectUseId(toAccount);
            String fromAccount = jsonObject.getString("fromAccount");
            Double amount = jsonObject.getDouble("amount");

            String description = jsonObject.getString("description");

            // 获取当前时间戳
            long currentTimestampMillis = System.currentTimeMillis();
            // 创建 Date 对象
            Date currentDate = new Date(currentTimestampMillis);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(currentDate);
            jsonObject.put("transferDate", formattedDate);

            System.out.println("收到来自客户端：" + id + "的信息:" + text);


            Session session = sessionMap.get(id);
            TransferMoney transferMoney = new TransferMoney();
            transferMoney.setAmount(amount);
            transferMoney.setDescription(description);
            transferMoney.setFromAccount(fromAccount);
            transferMoney.setToAccount(toAccount);
            transferMoney.setRead(1);

            System.out.println(session);
            if (session == null) {
                transferMoney.setRead(0);
                tms.insertTransferMoney(transferMoney);
                System.out.println("当前用户已离线");
                return;
            }

            tms.insertTransferMoney(transferMoney);
            System.out.println("重复结果");
            sendToAllClient(jsonObject.toString(), session);
            return;


        }
//        }else{
//            // 处理收到的消息
//            System.out.println(message);
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode jsonNode = objectMapper.readTree(message);
//                String targetUserId = jsonNode.get("targetUserId").asText();
//                String senderId = jsonNode.get("senderId").asText();
//                String content = jsonNode.get("content").asText();
//                System.out.println(message);
//                // 创建消息实体
//                Message chatMessage = new Message();
//                Session session=sessionMap.get(targetUserId);
//                chatMessage.setSenderId(senderId);
//                chatMessage.setReceiverId(targetUserId);
//                chatMessage.setContent(content);
//                chatMessage.setTimestamp(new Date());// 设置当前时间为消息时间
//                chatMessage.setIfread("未读");
//                MessageMapper messageMapper = BeanUtil.getMessageMapper();
//                // 保存消息到数据库
//                messageMapper.insertMessage(chatMessage);
//                if(session==null){
//                    System.out.println("当前用户已离线");
//                    Session session1=sessionMap.get(senderId);
//                    sendToAllClient("当前无客服",session1);
//                    return;
//                }
//                sendToAllClient(message,session);
//                // 遍历所有连接的WebSocket会话，找到目标用户并发送消息
////                for (WebSocketChatHandler client : clients) {
////                    if (client.userId.equals(targetUserId)) {
////                        try {
////                            synchronized (client) {
////                                client.session.getBasicRemote().sendText(message);
////                            }
////                        } catch (IOException e) {
////                            // 处理异常
////                        }
//////                    }
////                }
//            } catch (IOException e) {
//                // 处理消息解析异常
//            }
//
//
//        }


    }

    /**
     * 群发
     *
     * @param message
     */
    public void sendToAllClient(String message,Session session1) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                System.out.println(session);
                if(session1.equals(session)){
                    session.getBasicRemote().sendText(message);
                    System.out.println("成功发送消息");
                    break;
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                System.out.println(session);

                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @OnClose
    public void onClose() {
        clients.remove(this);
    }

//    private void broadcast(String message) {
//        for (WebSocketChatHandler client : clients) {
//            try {
//                synchronized (client) {
//                    client.session.getBasicRemote().sendText(message);
//                }
//            } catch (IOException e) {
//                // 处理异常
//            }
//        }
//    }

//    public static void onTransferSuccess(String receiverId, String amount) {
//        // 构建转账成功的消息内容
//        String transferMessage = buildTransferSuccessMessage(receiverId, amount);
//
//        // 查找目标用户并发送消息
//        for (WebSocketChatHandler client : clients) {
//            if (client.userId.equals(receiverId)) {
//                try {
//                    synchronized (client) {
//                        client.session.getBasicRemote().sendText(transferMessage);
//                    }
//                } catch (IOException e) {
//                    // 处理异常
//                }
//            }
//        }
//    }
//
//    // 构建转账成功的消息内容
//    private static String buildTransferSuccessMessage(String receiverId, String amount) {
//        // 构建消息的JSON格式
//        return String.format("{\"type\":\"transfer\", \"receiverId\":\"%s\", \"amount\":\"%s\", \"message\":\"转账成功\"}", receiverId, amount);
//    }
}
