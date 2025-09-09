<template>
  <el-upload
    class="avatar-uploader"
    :action="uploadUrl"
    :show-file-list="false"
    :on-success="handleSuccess"
    :on-error="handleError"
    :before-upload="beforeImageUpload"
    :headers="headers"
  >
    <img v-if="internalImageUrl" :src="internalImageUrl" class="avatar" />
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>

<script>
import { defineComponent, ref, watch } from "vue";

export default defineComponent({
  name: "SingleImageUpload",
  props: {
    modelValue: String, // 接收外部v-model绑定的值
  },
  emits: ["update:modelValue"], // 允许组件更新v-model绑定的值
  setup(props, { emit }) {
    const internalImageUrl = ref(props.modelValue);
    const uploadUrl = "/api/tencent/cos/upload"; // 替换成你的上传URL
    const headers = {
      Authorization: `${localStorage.getItem("token")}`,
    };
    // 监听外部v-model变化，更新内部状态
    watch(
      () => props.modelValue,
      (newValue) => {
        internalImageUrl.value = newValue;
      }
    );

    // 上传成功
    const handleSuccess = (response, file) => {
      const {
        data: { url },
      } = response;
      // 假设后端返回的图片地址是 response.url
      emit("update:modelValue", url); // 用事件更新v-model

      internalImageUrl.value = url; // 更新组件内的图片显示
    };

    // 上传失败
    const handleError = () => {
      emit("update:modelValue", ""); // 如果上传失败，清空v-model绑定的值
      internalImageUrl.value = ""; // 清空组件内的图片显示
    };

    // 上传之前的验证
    const beforeImageUpload = (file) => {
      const isJPG = file.type === "image/jpeg";
      const isPNG = file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG && !isPNG) {
        this.$message.error("上传图片只能是 JPG/PNG 格式!");
        return false;
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
        return false;
      }
      return true;
    };

    return {
      internalImageUrl,
      uploadUrl,
      handleSuccess,
      handleError,
      beforeImageUpload,
      headers,
    };
  },
});
</script>

<style scoped>
.avatar-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>