package com.ssafy.chat.model;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatMessageDto {
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type; // 메시지 타입
    private int planId;
    private int roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String time; // 채팅 발송 시간간

    public ChatMessageDto() {
        this.type = MessageType.TALK; // 기본값 설정
        this.time = java.time.LocalDateTime.now().toString(); // 현재 시간으로 기본값 설정
    }
}