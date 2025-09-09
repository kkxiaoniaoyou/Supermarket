<template>
  <el-container class="eid-management-container">
    <el-card class="eid-management-card">
      <h2>商品管理</h2>
      <el-button v-if="store.state.userInfo.roleId === 1" type="primary" @click="showDialog = true">新增</el-button>
      <el-table :data="list" style="width: 100%; margin-top: 20px">
        <el-table-column prop="name" label="商品名称"></el-table-column>
        <el-table-column prop="image" label="图片">
          <template #default="scope">
            <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.image"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="costPrice" label="进价"></el-table-column>
        <el-table-column prop="description" label="介绍"></el-table-column>
        <el-table-column prop="expiryDate" label="失效日期"></el-table-column>

        <el-table-column v-if="store.state.userInfo.roleId === 1" label="操作">
          <template #default="scope">
            <el-button type="text" @click="viewDetails(scope.row)"
              >修改</el-button
            >
            <el-button type="text" @click="del(scope.row)">删除</el-button>
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

    <el-dialog title="商品" v-model="showDialog">
      <el-form :model="selectedModel" label-width="120px">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="selectedModel.name"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="image">
          <upload v-model="selectedModel.image"></upload>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="selectedModel.description"></el-input>
        </el-form-item>
        <el-form-item label="失效日期" prop="expiryDate">
          <el-date-picker
            v-model="selectedModel.expiryDate"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="进价" prop="costPrice">
          <el-input-number v-model="selectedModel.costPrice"></el-input-number>
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
import * as api from "@/api/Product.js";
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
    id,
    CostPrice: selectedModel.value.costPrice,
    Description: selectedModel.value.description,
    Id: selectedModel.value.id,
    Image: selectedModel.value.image,
    Name: selectedModel.value.name,
    ExpiryDate: selectedModel.value.expiryDate,
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
