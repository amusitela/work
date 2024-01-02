//package com.cumt.bankapp.websocket;
//
//
//import com.cumt.bankapp.domain.TransferMoney;
//import com.cumt.bankapp.service.ITransferMoneyService;
//import com.cumt.bankapp.tools.jwt.BaseContext;
//import lombok.val;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.math.BigDecimaBigDecimal;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * WebSocket服务
// */
//@Component
//@ServerEndpoint("/chat/{id}")
//public class WebSocketServer {
//
//    @Resource
//    private ITransferMoneyService tms;
//
//    //存放会话对象
//    private static Map<String, Session> sessionMap = new HashMap();
//
//    /**
//     * 连接建立成功调用的方法
//     */
//    @OnOpen
//    public void onOpen(Session session, @PathParam("id") String sid) {
//
//
//        System.out.println("客户端：" + sid + "建立连接");
//        sessionMap.put(sid, session);
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     */
//    @OnMessage
//    public void onMessage(String message, @PathParam("id") String sid) {
//        String id = message.split("@")[0];
//        String text = message.split("@")[1];
//        JSONObject jsonObject = new JSONObject(message);
//        String toAccount = jsonObject.getString("toAccount");
//        String fromAccount = jsonObject.getString("fromAccount");
//        BigDecimal amount = jsonObject.getBigDecimal("amount");
//
//        String description = jsonObject.getString("description");
//        System.out.println("收到来自客户端：" + sid + "的信息:" + text);
//
//        Session session = sessionMap.get(id);
//        TransferMoney transferMoney = new TransferMoney();
//        transferMoney.setAmount(amount);
//        transferMoney.setDescription(description);
//        transferMoney.setFromAccount(fromAccount);
//        transferMoney.setToAccount(toAccount);
//
//
//
//        if(session==null){
//            transferMoney.setRead(0);
//            tms.insertTransferMoney(transferMoney);
//            System.out.println("当前用户已离线");
//            return;
//        }
//
//        tms.insertTransferMoney(transferMoney);
//        sendToAllClient(text,session);
//    }
//
//    /**
//     * 连接关闭调用的方法
//     *
//     * @param sid
//     */
//    @OnClose
//    public void onClose(@PathParam("id") String sid) {
//        System.out.println("连接断开:" + sid);
//        sessionMap.remove(sid);
//    }
//
//    /**
//     * 群发
//     *
//     * @param message
//     */
//    public void sendToAllClient(String message,Session session1) {
//        Collection<Session> sessions = sessionMap.values();
//        for (Session session : sessions) {
//            try {
//                //服务器向客户端发送消息
//                System.out.println(session);
//                if(session1.equals(session)){
//                session.getBasicRemote().sendText(message);
//                System.out.println("成功发送消息");
//                break;
//                };
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public void sendToAllClient(String message) {
//        Collection<Session> sessions = sessionMap.values();
//        for (Session session : sessions) {
//            try {
//                //服务器向客户端发送消息
//                System.out.println(session);
//
//                session.getBasicRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
