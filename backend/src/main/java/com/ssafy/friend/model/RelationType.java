package com.ssafy.friend.model;

public enum RelationType {
    NONE,        // 아무 관계 없음
    REQUEST,    // 내가 친구 요청을 한 상태
    PENDING,        // 내가 친구 요청을 받은 상태
    FRIEND;      // 서로 친구인 상태

    public int toInt() {
        switch (this) {
            case NONE:
                return 0;
            case REQUEST:
                return 1;
            case PENDING:
                return 2;
            case FRIEND:
                return 3;
            default:
                throw new IllegalArgumentException("Unknown ImageType: " + this);
        }
    }
}
