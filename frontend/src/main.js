// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import { aliases, mdi } from "vuetify/iconsets/mdi-svg";
import { ko } from 'vuetify/locale';

const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
    },
  },
  locale: {
    locale: 'ko',
    fallback: 'ko',
    messages: { ko },
  },
});
//kakao map api
import { useKakao } from "vue3-kakao-maps/@utils";

useKakao("c7a4277e35876cd976b36b4258c8c300");

//vue
import { createApp } from "vue";
import { createPinia } from "pinia";

//router
import router from "./router";

import App from "./App.vue";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(vuetify);

app.mount("#app");
