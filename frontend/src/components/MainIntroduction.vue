<script setup>
import { ref } from 'vue';

const moved = ref({
    intro: {
    },
    main: {
        bottom: '-250px'
    }
})
function introduce() {
    moved.value = {
        intro: {
            bottom: '-400px'
        },
        main: {
            bottom: '30%'
        }
    }
}
function getRandomValue(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
</script>

<template>
    <div class="head position-relative h-screen overflow-hidden"
        style=" width: 100%; background-color: #CCE2E8; margin-top:-64px;">
        <img class="position-absolute wave" style="bottom:350px; right: 15%; height: 50px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/boat.png" alt="">
        <img class="position-absolute wave" style="bottom:250px; left: 15%;" src="/src/assets/landing-page/boat.png" alt="" :style=" {animationDuration: getRandomValue(5, 20)+'s'}">

        <img class="position-absolute wave" style="top:150px; left: 25%; height: 20px;"  :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/wave_small.png" alt="">
        <img class="position-absolute wave" style="top:90px; right: 5%; height: 20px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/wave_small.png" alt="">

        <img class="position-absolute wave" style="top:50px; left: 45%; height: 30px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/cloud_reverse.png" alt="">
        <img class="position-absolute wave" style="top:80px; left: 5%; height: 40px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/cloud.png" alt="">
        <img class="position-absolute wave" style="top:220px; left: 10%; height: 80px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/cloud.png" alt="">
        <img class="position-absolute wave" style="top:100px; right: 20%;" src="/src/assets/landing-page/cloud_reverse.png" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            alt="">
        <img class="position-absolute wave" style="top:200px; right: 5%; height: 20px;" :style=" {animationDuration: getRandomValue(5, 20)+'s'}"
            src="/src/assets/landing-page/cloud.png" alt="">



        <div class="position-absolute bg-white"
            style="left: -100px;bottom: 50px;height: 300px; width: 200px; border-radius: 100%;"></div>
        <div class="position-absolute bg-white"
            style="right: -100px;bottom: 50px;height: 300px; width: 200px; border-radius: 100%;"> </div>


        <img class="position-absolute move"
            style=" bottom:300px; left:calc(50% - 500px); transform: scale(0.7) rotateY(180deg) rotate(60deg);"
            :style="moved.intro" src="/src/assets/landing-page/dolphin.png" alt="">
        <img class="position-absolute wave wave1" style="bottom:-280px; left:-50px; animation-duration: 8s;"
            src="/src/assets/landing-page/wave3.png" alt="">
        <div class="d-flex justify-center position-absolute move" style="bottom: 20%;width: 100%;" :style="moved.intro">
            <div class="d-flex flex-column align-center justify-center"
                style="background: rgb(220,247,255);
background: linear-gradient(0deg, rgba(220,247,255,1) 0%, rgba(255,255,255,1) 100%); border-radius: 100%; width: 500px; height: 500px;">
                <p class="title">여행</p>
                <p class="subtitle">여행을 계획하고 계신가요?</p>
                <p class="subtitle">하지만 하나하나 찾아보기 번거롭죠?</p>
                <br>
                <v-btn size="large" rounded color="#F0BBB1" style=" width: 250px; font-size: 15px; color: white"
                    @click="introduce">
                    네!</v-btn>
            </div>
        </div>


        <img class="position-absolute wave wave2" style="bottom:-330px; left: -50px;"
            src="/src/assets/landing-page/wave2.png" alt="">

        <img class="position-absolute move" style=" bottom:200px; right: calc(50% - 500px); transform: rotate(60deg);"
            :style="moved.intro" src="/src/assets/landing-page/dolphin.png" alt="">
        <div class="d-flex justify-center position-absolute move" style=" width: 100%;" :style="moved.main">
            <div class="d-flex flex-column align-center justify-center">
                <p class="title">간단한 여행플래너</p>
                <p class="subtitle">조아요행으로 스케줄링 하고 친구와 공유해보세요.</p>
                <br>
                <v-btn size="large" rounded color="#F0BBB1" :to="{ name: 'plan-write' }"
                    style=" width: 250px; font-size: 15px; color: white">
                    일정 계획하기</v-btn>
            </div>
            <div style="width: 200px;"></div>
            <div class="border rounded-xl" inline style="background: rgb(220,247,255);
background: linear-gradient(0deg, rgba(220,247,255,1) 0%, rgba(255,255,255,1) 100%);">
                <v-card width="600px">
                    <v-img src="src/assets/feature_capture.png"></v-img>
                </v-card>
            </div>
        </div>
        <img class="position-absolute wave wave3" style="bottom:-380px; left: -50px; animation-duration: 5s;"
            src="/src/assets/landing-page/wave1.png" alt="">
    </div>
</template>

<style scoped>
.move {
    transition-duration: 2s;
}

.title {
    font-size: 80px;
    font-weight: bold
}

.subtitle {
    font-size: 20px;
}

.wave {
    animation: moveLeftRight 10s infinite alternate linear;
}

@keyframes moveLeftRight {
    from {
        transform: translate(-50px, 0px);
    }

    to {
        transform: translate(50px, 0px);
    }
}
</style>

<style>
html {
    color: #20435C
}
</style>