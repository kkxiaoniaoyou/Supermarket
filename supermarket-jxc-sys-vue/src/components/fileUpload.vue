<template>
  <el-form-item label="测试">
    <el-input v-model="testKey"></el-input>
  </el-form-item>
  <el-button type="primary" @click="check" style="margin-bottom: 10px"
    >测试</el-button
  >
  <!-- <el-form-item label="密钥">
    <el-input v-model="secretKey"></el-input>
  </el-form-item> -->
  <el-upload
    :action="uploadUrl"
    :http-request="uploadFile"
    :on-success="handleSuccess"
    :before-upload="beforeUpload"
    accept=".txt,.docx"
    v-slots="{ submit }"
  >
    <el-button type="primary" @click="submit">点击上传</el-button>
  </el-upload>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";

// 外部参数传递到组件
const testKey = ref("");
// const secretKey = ref("");
const str = "abcdefghiklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const getRandomIndex = () => {
  return Math.round(Math.random() * str.length);
};
const genRandomKey = () => {
  let total = 0;
  const arr = [];
  while (true) {
    const index = getRandomIndex();
    const char = str[index];
    if (char) {
      arr.push(char);
      total++;
    }
    if (total === 16) {
      break;
    }
  }
  return arr.join("");
};

// fileUrl是使用v-model绑定的数据
const emit = defineEmits(["success"]);

// 服务器返回的文件URL
const fileUrl = ref("");

function beforeUpload(file) {
  // 根据需要对文件进行验证
  return true;
}

function handleSuccess(response) {
  // 假定response中有一个URL字段，根据服务器返回的实际情况调整
  emit("success");
}
const check = () => {
  if (!testKey.value) {
    ElMessage.warning("请输入");
    return;
  }
  ElMessage.success("测试成功");
};
async function uploadFile(request) {
  const formData = new FormData();
  formData.append("file", request.file);
  formData.append("secretKey", genRandomKey());
  // 自定义上传行为
  axios
    .post("/api/tencent/cos/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `${localStorage.getItem("token")}`,
      },
    })
    .then((response) => {
      // 上传成功，调用Element Plus的成功回调
      request.onSuccess(response.data);
    })
    .catch((error) => {
      // 上传出错，根据需要处理错误情况
      request.onError(error);
      ElMessage.error(`上传失败: ${error.message}`);
    });
}
</script>