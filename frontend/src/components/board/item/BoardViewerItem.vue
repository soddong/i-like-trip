<script setup>
import "@toast-ui/editor/dist/toastui-editor.css";
import { ref, onMounted, watch, defineProps } from 'vue';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';

// Props 정의
const props = defineProps({
  content: String
});

const editorRef = ref(null);

onMounted(() => {
  editorRef.value = new Viewer({
    el: document.querySelector('#viewer'),
    height: "500px",
    initialEditType: "markdown",
    theme: 'dark',
  });

  // Props로 받은 content를 Viewer에 설정
  if (props.content) {
    editorRef.value.setMarkdown(props.content);
  }
});

// Props 변경 감지
watch(() => props.content, (newContent) => {
  if (editorRef.value && newContent) {
    editorRef.value.setMarkdown(newContent);
  }
}, { immediate: true });
</script>

<template>
  <div id="viewer"></div>
</template>

<style scoped>
.toastui-editor-contents {
  font-size: 30px !important;
}
</style>