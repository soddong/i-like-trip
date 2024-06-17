<template>
    <v-container class="chat-container">
      <v-row class="messages" no-gutters>
        <v-col cols="12" v-for="message in messages" :key="message.id" :class="{'message-container': true, 'self': message.sender === userStore.userId, 'other': message.sender !== userStore.userId}">
          <span class="sender">{{ message.sender }}</span>
          <v-row>
            <v-col :cols="message.sender === userStore.userId ? 'offset-6' : ''" cols="6">
              <v-card class="message" :class="{'self-card': message.sender === userStore.userId, 'other-card': message.sender !== userStore.userId}">
                <v-card-text class="text" style="padding: 5px;">{{ message.message }}</v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-row class="input-container">
        <v-col>
          <v-text-field v-model="newMessage" @keyup.enter="sendMessage" variant="outlined" label="Type your message..." outlined dense></v-text-field>
        </v-col>
        <v-col cols="auto">
            <v-btn
            color="primary"
            size="large"
            variant="text"
            @click="sendMessage"
            >
            <v-icon icon="mdi-send" start></v-icon>
        </v-btn>
        </v-col>
      </v-row>
    </v-container>
</template>

<script setup>
import { ref, onMounted, onUnmounted, nextTick, watch } from 'vue';
import { useUserStore } from "@/stores/user";
import { usePlanStore } from "@/stores/plan";

const userStore = useUserStore();
const planStore = usePlanStore();

const messages = ref([]);
const newMessage = ref('');
let socket;
let reconnectTimeout = null;
const reconnectInterval = 5000; // 5초 후 재연결 시도

const setupWebSocket = async() => {
    if (socket && socket.readyState === WebSocket.OPEN) {
        return;
    }
    socket = new WebSocket(`ws://localhost:8080/ws/chat?planId=${planStore.planInfo.planId}`);
    socket.addEventListener('message', (event) => {
        const message = JSON.parse(event.data);
        console.log("Message received: ", message);
        messages.value.push(message);
    });
    socket.onopen = () => {
        console.log("WebSocket connection established");
        if (reconnectTimeout) {
            clearTimeout(reconnectTimeout);
            reconnectTimeout = null;
        }
    };
    socket.onclose = () => {
        console.log("WebSocket connection closed. Attempting to reconnect...");
        attemptReconnect();
    };
    socket.onerror = (error) => {
        console.error("WebSocket error:", error);
        socket.close(); // 오류가 발생하면 연결을 닫고 재연결 시도
    };
};

const attemptReconnect = () => {
    if (!reconnectTimeout) {
        reconnectTimeout = setTimeout(() => {
            setupWebSocket();
        }, reconnectInterval);
    }
};

const sendMessage = async () => {
    if (!newMessage.value.trim()) return;

    const message = {
        planId: planStore.planInfo.planId,
        sender: userStore.userId,
        message: newMessage.value,
    };

    if (socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(message));
        newMessage.value = '';
    } else {
        console.error("WebSocket connection is not open. Message not sent.");
    }
};

onMounted(async() => {
    await setupWebSocket();
});

onUnmounted(() => {
    if (socket) {
        socket.close();
    }
    if (reconnectTimeout) {
        clearTimeout(reconnectTimeout);
    }
});
</script>

<style scoped>
.chat-container {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    max-height: 60vh;
}

.messages {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px;
    background-color: #f9f9f9;
    overflow-y: auto; /* 스크롤바를 보이도록 설정 */
}

.message-container {
    margin-bottom: 10px;
    display: flex;
    flex-direction: column;
    max-width: 100%;
}

.self {
    align-items: flex-end;
    text-align: right;
}

.other {
    align-items: flex-start;
    text-align: left;
}

.sender {
    font-weight: bold;
    margin-bottom: 2px; /* 간격을 줄임 */
}

.message {
    padding: 5px; /* 패딩을 줄임 */
    border-radius: 10px;
    background-color: #f1f1f1;
    word-wrap: break-word;
    width: 100%;
}

.self-card {
    background-color: #dcf8c6;
}

.other-card {
    background-color: #f1f1f1;
}

.input-container {
    display: flex;
    padding-top: 10px;
}
</style>
