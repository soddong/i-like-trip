<template>
  <v-app>
    <HeaderNav v-if="$route.name !== 'plan-write'" />
    <div v-if="$route.name !== 'plan-write'" style="background-color: rgb(204 226 232);">
      <div :style="{ height: $route.name == 'Home' ? '100dvh' : '30dvh' }"></div>
      <div class="position-absolute overflow-hidden" style="width: 100%; height: 400px;"
        :style="{ top: $route.name == 'Home' ? '75dvh' : '60px' }">
        <img class="position-absolute wave wave1" style="top:0px; left:-50px; animation-duration: 8s;"
          src="/src/assets/landing-page/wave3.png" alt="">
        <img class="position-absolute wave wave2" style="left: -50px;"
          :style="{ top: $route.name == 'Home' ? '50px' : '25px' }" src="/src/assets/landing-page/wave2.png" alt="">
        <img class="position-absolute wave wave3" style="left: -50px; animation-duration: 5s;"
          :style="{ top: $route.name == 'Home' ? '100px' : '50px' }" src="/src/assets/landing-page/wave1.png" alt="">
        <div class="position-absolute" style="top:90px; left: 40px;">
          <h1>{{ $route.name }}</h1>
        </div>
      </div>
      <div class="position-absolute" style="width: 100%;" :style="{ top: $route.name == 'Home' ? '0dvh' : '80px' }">
        <RouterView />
      </div>
    </div>
    <RouterView v-if="$route.name == 'plan-write'" />
  </v-app>
</template>

<script setup>
import HeaderNav from "@/components/layout/HeaderNav.vue";

</script>

<style>
#main-area {
  background-color: rgb(204 226 232)
}

.v-application {
  font-family: 'SeoulNamsanM' !important;
  color: #20435C
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

@font-face {
  font-family: 'SeoulHangangM';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/SeoulHangangM.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'seolleimcool-SemiBold';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/seolleimcool-SemiBold.woff2') format('woff2');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'JalnanGothic';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/JalnanGothic.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'GeekbleMalang2WOFF2';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/GeekbleMalang2WOFF2.woff2') format('woff2');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'Pretendard-Regular';
  src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
  font-weight: 400;
  font-style: normal;
}

@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 400;
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot');
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot?#iefix') format('embedded-opentype'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.woff') format('woff'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.ttf') format('truetype');
}

@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 700;
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot');
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot?#iefix') format('embedded-opentype'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.woff') format('woff'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.ttf') format('truetype')
}

@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 300;
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');
  src: url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'),
    url('//fastly.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');
}

@font-face {
  font-family: 'SeoulNamsanM';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/SeoulNamsanM.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

.nanumbarungothic * {
  font-family: 'NanumBarunGothic', sans-serif;
}
</style>