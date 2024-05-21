<script setup>

</script>

<template>
    <v-container class="head position-relative" fluid
        style="height: 800px; background-image: url('/src/assets/landing-page/background.png'); background-size: cover;">
        <v-row align="center">
            <v-col>
                <div class="d-flex flex-column align-center justify-center">
                    <p class="title">간단한 여행플래너</p>
                    <p class="subtitle">조아요행으로 스케줄링 하고 친구와 공유해보세요.</p>
                    <br>
                    <v-btn size="large" rounded color="#F0BBB1" :to="{ name: 'plan-write' }"
                        style=" width: 250px; font-size: 15px; color: white">
                        일정 계획하기</v-btn>
                </div>
            </v-col>
            <!-- <v-col class="border rounded-xl" inline style="background: rgb(220,247,255);
background: linear-gradient(0deg, rgba(220,247,255,1) 0%, rgba(255,255,255,1) 100%);">
                <v-card width="600px">
                    <v-img src="src/assets/feature_capture.png"></v-img>
                </v-card>
            </v-col> -->
        </v-row>
        <div class="water position-absolute" style="
            bottom: 0px;
            left: 0px;
            height: 200px;
            width: 100%;
            overflow: hidden;
        ">
            <!-- background-image: url('/src/assets/landing-page/wave3.png');" -->
            <img class="position-absolute" style="top:0px; left: 0px;" src="/src/assets/landing-page/wave3.png" alt="">
            <img class="position-absolute" style="top:50px; left: 0px;" src="/src/assets/landing-page/wave2.png" alt="">
            <img class="position-absolute" style="top:100px; left: 0px;" src="/src/assets/landing-page/wave1.png"
                alt="">
        </div>
    </v-container>
</template>

<style scoped>
.title {
    font-size: 45px;
    font-weight: bold
}

.subtitle {
    font-size: 20px;
}

.head {
    margin-right: 30%;
    padding-top: 200px;
    padding-bottom: 200px;
}
</style>

<style>
html {
    color: #20435C
}
</style>