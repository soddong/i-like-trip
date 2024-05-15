<script setup>
import { ref, watch } from "vue"
import { useRoute, useRouter } from "vue-router"
import { registArticle, getModifyArticle, modifyArticle } from "@/api/board"
import BoardEditorItem from '@/components/board/item/BoardEditorItem.vue'; 

const router = useRouter()
const route = useRoute()

const props = defineProps({ type: String })

const isUseId = ref(false)

const article = ref({
  articleNo: 0,
  subject: "",
  content: "",
  userId: "",
  userName: "",
  hit: 0,
  registerTime: "",
})

if (props.type === "modify") {
  let { articleno } = route.params
  console.log(articleno + "번글 얻어와서 수정할거야")
  getModifyArticle(
    articleno,
    ({ data }) => {
      article.value = data
      isUseId.value = true
    },
    (error) => {
      console.log(error)
    }
  )
  isUseId.value = true
}

const subjectErrMsg = ref("")
const contentErrMsg = ref("")
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 확인해 주세요!!!"
    } else subjectErrMsg.value = ""
  },
  { immediate: true }
)
watch(
  () => article.value.content,
  (value) => {
    let len = value.length
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 확인해 주세요!!!"
    } else contentErrMsg.value = ""
  },
  { immediate: true }
)

function onSubmit() {
  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value)
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value)
  } else {
    props.type === "regist" ? writeArticle() : updateArticle()
  }
}

function writeArticle() {
  registArticle(
    article.value,
    (response) => {
      let msg = "글등록 처리시 문제 발생했습니다."
      if (response.status == 201) msg = "글등록이 완료되었습니다."
      alert(msg)
      moveList()
    },
    (error) => {
      let msg = "글등록 처리시 문제 발생했습니다."
      console.log(error)
      alert(msg)
    }
  )
}

function updateArticle() {
  console.log(article.value.articleNo + "번글 수정하자!!", article.value)
  modifyArticle(
    article.value,
    (response) => {
      let msg = "글수정 처리시 문제 발생했습니다."
      if (response.status == 200) msg = "글정보 수정이 완료되었습니다."
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function moveList() {
  router.replace({ name: "article-list" })
}
</script>

<template>
  <v-form>
   <v-container>
    <v-row class="mb-3">
      <label for="userid">작성자 ID (임시) : </label>
      <input
        type="text"
        v-model="article.userId"
        :disabled="isUseId"
        placeholder="입력하슈"
      />
    </v-row>
      <v-row>
        제목
      </v-row>
      <v-row>
        <v-text-field
          :counter="50"
          variant="outlined"
          required
          v-model="article.subject"
          maxlength="50"
        ></v-text-field>
      </v-row>
      <v-row>
        내용
      </v-row>
      <v-row>
        <BoardEditorItem v-if="article.content !== ''" v-model="article.content"></BoardEditorItem>
        <BoardEditorItem v-else-if="props.type === 'regist'" v-model="article.content"></BoardEditorItem>
      </v-row>
      <v-row style="margin-top: 100px;">
        <v-col>
          <v-btn @click="onSubmit" variant="outlined" v-if="type === 'regist'" class="mr-2">
            글작성
          </v-btn>
          <v-btn @click="onSubmit" variant="outlined" v-else class="mr-2">
            글수정
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<style scoped>
</style>
