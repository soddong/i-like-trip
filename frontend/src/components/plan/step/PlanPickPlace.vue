<script setup>
import { ref } from 'vue';
const list = ref([
    {
        id: 1,
        numberList: [{ content: 1 }, { content: 2 }]
    },
    {
        id: 2,
        numberList: [{ content: 3 }, { content: 4 }, { content: 5 }, { content: 6 }]
    },
    {
        id: 3,
        numberList: [{ content: 7 }, { content: 8 }, { content: 9 }]
    }
])
function startDrag(event, item) {
    event.dataTransfer.dropEffect = "move"
    event.dataTransfer.effectAllowed = "move"
    event.dataTransfer.setData("selectedItem", item.content)
}
function onDrop(event, colNum) {
    const selectedItem = Number(event.dataTransfer.getData("selectedItem"))

    // 리스트에서 선택된 아이템과 같은 content 값을 가진 요소를 찾아 index를 반환한다.
    let targetIdx
    let targetItem
    list.value.forEach((obj, index) => {
        obj.numberList.forEach((ob) => {
            if (ob.content === selectedItem) {
                targetIdx = index
                targetItem = ob
            }
        })
    })

    // drop이 된 <div> index(=colNum)를 받아 리스트에 추가한다. 
    // 기존 리스트에서는 요소를 삭제한다. (splice() 사용)
    list.value[colNum].numberList.push(targetItem)
    list.value[targetIdx].numberList.splice(list.value[targetIdx].numberList.indexOf(targetItem), 1)
}

</script>

<template>
    <v-container fluid class="border fill-height">

    </v-container>
    <!-- <div v-for="(item, idx) in list" :key="item.id">
        <div class="col" @drop.prevent="onDrop($event, idx)" @dragenter.prevent @dragover.prevent>
            <div v-for="(numItem, idx) in item.numberList" :key="idx" class="box"
                @dragstart="startDrag($event, numItem, item.id)" draggable="true">
                <p>{{ numItem.content }}</p>
            </div>
        </div>
    </div> -->
</template>

<style scoped>
.playground {
    display: flex;

}

.col {
    display: flex;
    height: 500px;
    width: 150px;
    background-color: #fff;
    border: 1px solid lightgrey;
    margin-left: 5px;
    flex-direction: column;
    align-items: center;
    border-radius: 8px;

}

.box {
    width: 130px;
    height: 50px;
    background-color: #7b71b7;
    margin-top: 10px;
    border-radius: 5px;


}

p {
    text-align: center;
    line-height: 50px;
    color: white;
}
</style>