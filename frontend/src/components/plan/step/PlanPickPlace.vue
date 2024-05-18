<script setup>
import { ref, onMounted } from 'vue';
import PlanCheckDate from "./item/PlanCheckDate.vue";
import { GridStack } from 'gridstack';

const count = ref(0);
const info = ref("");
let grid = null;
const items = [];
function addNewWidget() {
    const node = items[count.value] || {
        x: 1,
        y: 1,
        w: 1,
        h: 1,
    };
    node.id = node.content = String(count.value++);
    grid.addWidget(node);
}
onMounted(() => {
    grid = GridStack.init({
        float:true,
        cellHeight: "70px",
        minRow: 1,
        column:1
    });

    grid.on("dragstop", function (event, element) {
        const node = element.gridstackNode;
        info.value = `you just dragged node #${node.id} to ${node.x},${node.y} – good job!`;
    });
});

</script>

<template>
    <v-container fluid class="border fill-height">
        <PlanCheckDate />
        <v-btn color="success" @click="addNewWidget">text</v-btn>
        <div class="border  overflow-auto" style="height: 700px; width: 300px;" >
            <div class="grid-stack">
        </div>
        </div>

    </v-container>

</template>

<style >
@import "gridstack/dist/gridstack.css";

.grid-stack-item{
    border: 1px solid black;
    background-color: red;
}
</style>