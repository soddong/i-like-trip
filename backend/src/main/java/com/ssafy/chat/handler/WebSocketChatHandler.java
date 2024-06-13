package com.ssafy.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.chat.model.ChatMessageDto;
import com.ssafy.chat.model.ChatRoom;
import com.ssafy.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;
    private final ChatService chatService;
    private final Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Received payload: {}", payload);

        ChatMessageDto chatMessage = mapper.readValue(payload, ChatMessageDto.class);
        log.info("Received message: {}", chatMessage);

        ChatRoom room = chatService.findByPlanId(chatMessage.getPlanId());
        chatMessage.setRoomId(room.getRoomId());
        log.info("Found room: {}", room);

        room.handleAction(session, chatMessage, chatService);
        chatService.sendMessageToRoom(chatMessage);
        chatService.saveMessage(chatMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String planIdParam = session.getUri().getQuery().split("=")[1];
        int planId = Integer.parseInt(planIdParam);
        chatService.registerSession(chatService.findByPlanId(planId).getRoomId(), session);
        sessionMap.put(session.getId(), session);
        log.info("WebSocket connection established with session ID: {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (status.getCode() == CloseStatus.NORMAL.getCode()) {
            sessionMap.remove(session.getId());
            log.info("WebSocket connection closed normally with session ID: {}", session.getId());
        } else {
            log.warn("WebSocket connection closed abnormally with session ID: {}", session.getId());
            // Optionally, you could re-open the session or take other actions here
        }
    }
}
