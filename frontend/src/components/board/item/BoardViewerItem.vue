<script setup>
import "@toast-ui/editor/dist/toastui-editor.css";
import { ref, onMounted, watch, defineProps } from 'vue';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';

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

  if (props.content) {
    editorRef.value.setMarkdown(props.content);
  }
});

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