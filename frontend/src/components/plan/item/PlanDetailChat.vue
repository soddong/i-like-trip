<template>
    <div class="chat-container">
        <div class="messages">
            <div v-for="message in messages" :key="message.id" :class="{'message-container': true, 'self': message.sender === userStore.userId, 'other': message.sender !== userStore.userId}">
                <span class="sender">{{ message.sender }}</span>
                <div class="message">
                    <span class="text">{{ message.message }}</span>
                </div>
            </div>
        </div>
        <div class="input-container">
            <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message..." />
            <button @click="sendMessage">Send</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useUserStore } from "@/stores/user";
import { usePlanStore } from "@/stores/plan";

const userStore = useUserStore();
const planStore = usePlanStore();

const messages = ref([]);
const newMessage = ref('');
let socket;

const setupWebSocket = async() => {
    if (socket && socket.readyState === WebSocket.OPEN) {
        return;
    }
    socket = new WebSocket(`ws://localhost:8080/ws/chat?planId=${planStore.planInfo.planId}`);
    socket.addEventListener('message', (event) => {
        const message = JSON.parse(event.data);
        console.log("Message received: ", message);  // 수신된 메시지를 콘솔에 출력
        messages.value.push(message);
    });
    socket.onopen = () => {
        console.log("WebSocket connection established");
    };
    socket.onclose = () => {
        console.log("WebSocket connection closed");
    };
    socket.onerror = (error) => {
        console.error("WebSocket error:", error);
    };
};

const sendMessage = async () => {
    if (!newMessage.value.trim()) return;

    const message = {
        planId: planStore.planInfo.planId,
        sender: userStore.userId,
        message: newMessage.value,
    };

    socket.send(JSON.stringify(message));
    newMessage.value = '';
};

onMounted(async() => {
    await setupWebSocket();
});

onUnmounted(() => {
    if (socket) {
        socket.close();
    }
});
</script>

<style scoped>
.chat-container {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
}

.messages {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px;
    background-color: #f9f9f9;
    overflow-y: scroll;
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
    margin-bottom: 5px;
}

.message {
    padding: 10px;
    border-radius: 10px;
    background-color: #f1f1f1;
    word-wrap: break-word;
    width: 150px;
}

.self .message {
    background-color: #dcf8c6;
}

.input-container {
    display: flex;
}

input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px 0 0 5px;
    margin-right: -1px;
}

button {
    padding: 10px;
    border: 1px solid #ddd;
    border-left: none;
    border-radius: 0 5px 5px 0;
    background-color: #007bff;
    color: white;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}
</style>
