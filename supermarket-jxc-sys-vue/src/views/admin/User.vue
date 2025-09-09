<template>
  <el-container class="eid-management-container">
    <el-card class="eid-management-card">
      <h2>人员管理</h2>
      <el-button type="primary" @click="showDialog = true">新增</el-button>
      <el-table :data="list" style="width: 100%; margin-top: 20px">
        <el-table-column prop="username" label="姓名"></el-table-column>
        <el-table-column prop="nickName" label="昵称"></el-table-column>
        <el-table-column prop="introduction" label="介绍"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="phoneNumber" label="电话"></el-table-column>

        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="text" @click="viewDetails(scope.row)"
              >修改</el-button
            >
            <el-button @click="del(scope.row)" type="text">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="pagination"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pager.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pager.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total"
      />
    </el-card>

    <el-dialog title="新增/修改" v-model="showDialog">
      <el-form>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="selectedModel.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="selectedModel.password"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="selectedModel.nickName"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="selectedModel.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="selectedModel.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="介绍信息" prop="introduction">
          <el-input v-model="selectedModel.introduction"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script setup>
import { ref, reactive } from "vue";
import * as api from "@/api/user.js";
import { ElMessage } from "element-plus";
import upload from "@/components/upload";
const showDialog = ref(false);
const selectedModel = ref({});
const list = ref([]);
const open = () => {
  showDialog.value = true;
};
const viewDetails = (item) => {
  selectedModel.value = { ...item };
  open();
};
const cancel = () => {
  showDialog.value = false;
  selectedModel.value = {};
};
const submit = async () => {
  const fn = selectedModel.value.userId ? api.update : api.register;
  const userId = selectedModel.value.userId ? selectedModel.value.userId : null;
  const { code, message } = await fn({
    ...selectedModel.value,
    userId,
    nickName: selectedModel.value.username,
  });
  if (code !== 200) {
    ElMessage.warning(message);
    return;
  }
  await getList();
  cancel();
};

const pager = ref({
  pageNum: 1,
  pageSize: 10,
  total: 0,
});
const handleSizeChange = (newSize) => {
  pager.value.pageSize = newSize;
  pager.value.pageNum = 1; // Reset to first page on size change
  getList();
};

const handleCurrentChange = (newPage) => {
  pager.value.pageNum = newPage; // Reset to first page on size change
  getList();
};
const getList = async () => {
  const {
    data: { pageNum, pageSize, list: arr, total },
  } = await api.getList({
    ...pager.value,
    params: {},
  });
  pager.value = {
    pageNum,
    pageSize,
    total,
  };
  list.value = arr;
};
const del = async ({ userId }) => {
  await api.delItem(userId);
  await getList();
};
const update = async (item, approvalStatus) => {
  await api.update({ ...item, approvalStatus });
  await getList();
};
getList();
</script>

<style scoped>
.eid-management-container {
  padding: 30px;
}

.eid-management-card {
  width: 100%;
}

h2 {
  margin-bottom: 20px;
}

.dialog-footer {
  text-align: right;
}
</style>