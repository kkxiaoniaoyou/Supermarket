<template>
  <el-container class="eid-management-container">
    <el-card class="eid-management-card">
      <h2>人员管理</h2>
      <el-button v-if="store.state.userInfo.roleId === 1" type="primary" @click="showDialog = true">新增</el-button>
      <el-table :data="list" style="width: 100%; margin-top: 20px">
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="phoneNumber" label="电话"></el-table-column>

        <el-table-column label="操作" v-if="store.state.userInfo.roleId === 1">
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
        <el-form-item label="用户名" prop="name">
          <el-input v-model="selectedModel.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="selectedModel.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="selectedModel.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="selectedModel.phoneNumber"></el-input>
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
import * as api from "@/api/Employee.js";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import upload from "@/components/upload";
const store = useStore();
const showDialog = ref(false);
const selectedModel = ref({});
const list = ref([
  // 更多申请记录
]);
const open = () => {
  showDialog.value = true;
};
const viewDetails = (item) => {
  selectedModel.value = { ...item };
  open();
  // 实现查看申请详情的逻辑
};
const cancel = () => {
  showDialog.value = false;
  selectedModel.value = {};
};
const submit = async () => {
  const fn = selectedModel.value.id ? api.updateItem : api.addItem;
  const id = selectedModel.value.id ? selectedModel.value.id : null;
  const { code, message } = await fn({
    ...selectedModel.value,
    Name: selectedModel.value.name,
    Password: selectedModel.value.password,
    Id: selectedModel.value.id,
    PhoneNumber: selectedModel.value.phoneNumber,
    Email: selectedModel.value.email,
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
    pageNum,
    pageSize,
    list: arr,
    total,
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
const del = async ({ id }) => {
  await api.delItem({ Id: id });
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
