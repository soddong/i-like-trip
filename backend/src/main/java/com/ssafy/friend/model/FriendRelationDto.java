package com.ssafy.friend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FriendRelationDto {
    private String userId;
    private String friendId;
    private int relation;
}
