<script setup>
import { ref, watch, onMounted } from 'vue';
import Editor from '@toast-ui/editor';
import '@toast-ui/editor/dist/toastui-editor.css';

const props = defineProps({
  modelValue: String,
});

const emit = defineEmits(['update:modelValue']);
const editorRef = ref(null);

async function uploadImage(blob) {
  const formData = new FormData();
  formData.append('image', blob);

  try {
    const response = await fetch('http://localhost:8080/upload', {
      method: 'POST',
      body: formData,
    });
    if (!response.ok) throw new Error('Server error occurred');
    return await response.json();
  } catch (error) {
    console.error('Upload failed:', error);
    return null;
  }
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
        const uploadResult = await uploadImage(blob);
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