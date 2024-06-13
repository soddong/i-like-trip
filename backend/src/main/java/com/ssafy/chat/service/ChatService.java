package com.ssafy.chat.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.chat.mapper.ChatMessageMapper;
import com.ssafy.chat.mapper.ChatRoomMapper;
import com.ssafy.chat.model.ChatMessageDto;
import com.ssafy.chat.model.ChatRoom;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {
    private final ObjectMapper mapper;
    private final ChatRoomMapper chatRoomMapper;
    private final ChatMessageMapper chatMessageMapper;
    private final Map<Integer, Set<WebSocketSession>> chatRoomSessions = new HashMap<>();

    public ChatRoom findByPlanId(int planId){
        return chatRoomMapper.findByPlanId(planId);
    }

    public void createRoom(int planId) {
        ChatRoom room = ChatRoom.builder()
                .planId(planId)
                .build();

        chatRoomMapper.insert(room);
    }

    public void saveMessage(ChatMessageDto chatMessage) {
        chatMessageMapper.insert(chatMessage);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendMessageToRoom(ChatMessageDto chatMessage) {
        Set<WebSocketSession> sessions = chatRoomSessions.get(chatMessage.getPlanId());
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                sendMessage(session, chatMessage);
            }
        }
    }

    public void registerSession(int roomId, WebSocketSession session) {
        chatRoomSessions.computeIfAbsent(roomId, k -> new HashSet<>()).add(session);
        // 이전 메시지 이력 가져와서 새로 연결된 세션에 전송
        List<ChatMessageDto> messages = getMessagesByRoomId(roomId);
        if (messages != null) {
            for (ChatMessageDto message : messages) {
                sendMessage(session, message);
            }
        }
    }

    public List<ChatMessageDto> getMessagesByRoomId(int roomId) {
        return chatMessageMapper.findByRoomId(roomId);
    }
}