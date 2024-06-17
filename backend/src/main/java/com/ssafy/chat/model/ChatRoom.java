package com.ssafy.chat.model;

import com.ssafy.chat.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private int roomId;
    private int planId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(int roomId, int planId){
        this.roomId = roomId;
        this.planId = planId;
    }


    public <T> void sendMessage(T message, ChatService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }
}