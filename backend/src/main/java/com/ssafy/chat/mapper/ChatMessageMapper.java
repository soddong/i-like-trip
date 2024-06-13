package com.ssafy.chat.mapper;

import com.ssafy.chat.model.ChatMessageDto;
import java.util.List;

public interface ChatMessageMapper {
    List<ChatMessageDto> findByRoomId(int roomId);
    void insert(ChatMessageDto chatMessage);
    List<ChatMessageDto> findByPlanId(int planId);
}