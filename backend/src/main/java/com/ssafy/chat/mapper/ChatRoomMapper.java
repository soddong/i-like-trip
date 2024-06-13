package com.ssafy.chat.mapper;

import com.ssafy.chat.model.ChatRoom;
import java.util.List;

public interface ChatRoomMapper {
    List<ChatRoom> findAll();
    ChatRoom findById(int id);
    ChatRoom findByPlanId(int planId);
    ChatRoom insert(ChatRoom chatRoom);
    void update(ChatRoom chatRoom);
    void delete(int id);
}
