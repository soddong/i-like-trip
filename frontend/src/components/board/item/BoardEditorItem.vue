<script setup>
import { ref, watch, onMounted } from 'vue';
import Editor from '@toast-ui/editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import { uploadImage } from "@/api/image.js";

const props = defineProps({
  modelValue: String,
});

const emit = defineEmits(['update:modelValue']);
const editorRef = ref(null);

async function handleUploadImage(blob) {
  const formData = new FormData();
  formData.append('image', blob);

  return new Promise((resolve, reject) => {
    uploadImage(formData, 
      (response) => {
        if (response.data) {
          resolve(response.data);
        } else {
          reject(new Error('No data in response'));
        }
      }, 
      (error) => {
        console.error('Upload failed:', error);
        reject(error);
      });
  });
}

onMounted(() => {
  editorRef.value = new Editor({
    el: document.querySelector("#editor"),
    height: "500px",
    initialEditType: "markdown",
    initialValue: props.modelValue,
    theme: 'dark',
    hooks: {
      addImageBlobHook: async (blob, callback) => {
        const uploadResult = await handleUploadImage(blob);
        if (uploadResult) {
          callback(uploadResult.imageAccessUrl, 'alt text optional');
        }
      }
    }
  });

  editorRef.value.on('change', () => {
    emit('update:modelValue', editorRef.value.getMarkdown());
  });
});

watch(() => props.modelValue, (newVal) => {
  if (editorRef.value) {
    editorRef.value.setMarkdown(newVal);
  }
});
</script>


<template>
  <div id="editor"></div>
</template>