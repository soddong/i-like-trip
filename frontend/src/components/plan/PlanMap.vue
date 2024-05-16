<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { mdiFolder, mdiAccountMultiple, mdiStar } from '@mdi/js';
const coordinate = {
    lat: 37.566826,
    lng: 126.9786567
};
</script>

<template>
    <v-navigation-drawer expand-on-hover rail>
        <v-list>
            <v-list-item prepend-avatar="src/assets/logo2.png" subtitle="sandra_a88@gmailcom" title="Sandra Adams"
                :to="{ name: 'Home' }"></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>
            <v-list-item :prepend-icon="mdiFolder" title="My Files" value="myfiles"></v-list-item>
            <v-list-item :prepend-icon="mdiAccountMultiple" title="Shared with me" value="shared"></v-list-item>
            <v-list-item :prepend-icon="mdiStar" title="Starred" value="starred"></v-list-item>
        </v-list>
    </v-navigation-drawer>

    <v-main>
        <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true" height="100%" width="100%">
            <KakaoMapMarker :lat="coordinate.lat" :lng="coordinate.lng"></KakaoMapMarker>
        </KakaoMap>
    </v-main>

    <v-navigation-drawer expand-on-hover rail location="right">
        <v-list>
            <v-list-item prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
                subtitle="sandra_a88@gmailcom" title="Sandra Adams"></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>
            <v-list-item :prepend-icon="mdiFolder" title="My Files" value="myfiles"></v-list-item>
            <v-list-item :prepend-icon="mdiAccountMultiple" title="Shared with me" value="shared"></v-list-item>
            <v-list-item :prepend-icon="mdiStar" title="Starred" value="starred"></v-list-item>
        </v-list>
    </v-navigation-drawer>

</template>

<style scoped></style>