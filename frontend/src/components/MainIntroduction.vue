<script setup>

</script>

<template>
    <v-container class="head">
        <v-row align="center">
            <v-col>
                <div class="d-flex flex-column align-center justify-center">
                    <p class="title">간단한 여행플래너</p>
                    <p class="subtitle">조아요행으로 스케줄링 하고 친구와 공유해보세요.</p>
                    <br>
                    <v-btn width="300px" height="70px" color="#072a40" :to="{ name: 'plan' }"><span
                            style="font-size: 20px">일정 계획하기</span></v-btn>
                </div>
            </v-col>
            <v-col>
                <v-card elevation="16" width="600px">
                    <v-img src="src/assets/feature_capture.png"></v-img>
                </v-card>
            </v-col>
        </v-row>

    </v-container>
</template>

<style scoped>
.title {
    font-size: 45px;
}

.subtitle {
    font-size: 15px;
}

.head {
    margin-right: 30%;
    padding-top: 200px;
    padding-bottom: 200px;
}
</style>