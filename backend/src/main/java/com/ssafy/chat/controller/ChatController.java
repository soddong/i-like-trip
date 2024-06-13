package com.ssafy.chat.controller;

import com.ssafy.chat.model.ChatMessageDto;
import com.ssafy.chat.model.ChatRoom;
import com.ssafy.chat.service.ChatService;
import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.ImageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService service;

    @GetMapping("/{planId}")
    public ResponseEntity<?> findRoomById(@PathVariable int planId) {
        try {
            ChatRoom room = service.findByPlanId(planId);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

//    @PostMapping("/message")
//    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageDto chatMessage) {
//        try {
//            service.sendMessageToRoom(chatMessage);
//            service.saveMessage(chatMessage);
//            System.out.println(chatMessage);
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}