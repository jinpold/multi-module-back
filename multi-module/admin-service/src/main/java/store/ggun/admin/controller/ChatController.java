//package store.ggun.admin.chat.controller;
//import store.ggun.admin.chat.model.ChatMessage;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequiredArgsConstructor
//@Controller
//public class ChatController {
//
//    private final SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/chat/message")
//    public void message(ChatMessage message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//}