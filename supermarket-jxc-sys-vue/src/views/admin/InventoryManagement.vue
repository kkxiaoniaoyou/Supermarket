<template>
  <el-container class="eid-management-container">
    <el-card class="eid-management-card">
      <h2>库存管理</h2>
      <el-button v-if="store.state.userInfo.roleId === 1" type="primary" @click="openAdd">新增</el-button>
      <el-table :data="list" style="width: 100%; margin-top: 20px">
        <el-table-column prop="productName" label="商品名称"></el-table-column>
        <el-table-column prop="salePrice" label="售价"></el-table-column>
        <el-table-column prop="productImage" label="图片">
          <template #default="scope">
            <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.productImage"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="stockQuantity" label="库存"></el-table-column>

        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="text" @click="viewDetails(scope.row)">修改</el-button
            >
            <el-button type="text" @click="del(scope.row)" v-if="store.state.userInfo.roleId === 1">删除</el-button>
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

    <el-dialog title="库存" v-model="showDialog">
      <el-form :model="selectedModel" label-width="120px">
        <el-form-item label="商品">
          <el-select v-model="selectedModel.productId" :disabled="!isAdd">
            <el-option
              v-for="item in products"
              :key="item.id"
              :value="item.id"
              :label="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="库存" prop="stockQuantity">
          <el-input-number
            v-model="selectedModel.stockQuantity"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="售价" prop="salePrice">
          <el-input-number :disabled="!(store.state.userInfo.roleId === 1)" v-model="selectedModel.salePrice"></el-input-number>
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
import * as api from "@/api/Inventory.js";
import * as productapi from "@/api/Product.js";

import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import upload from "@/components/upload";
const store = useStore();
const showDialog = ref(false);
const selectedModel = ref({});
const list = ref([
  // 更多申请记录
]);

const openAdd = () => {
    isAdd.value = true
    showDialog.value = true
}

const isAdd = ref(true)
const open = () => {
  showDialog.value = true;
};
const viewDetails = (item) => {
    isAdd.value = false
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
    SalePrice: selectedModel.value.salePrice,
    Id: selectedModel.value.id,
    StockQuantity: selectedModel.value.stockQuantity,
    ProductId: selectedModel.value.productId,
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
const products = ref([]);
const getProducts = async () => {
  const { data } = await productapi.getAllList();
  products.value = data;
  return data;
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
  const data = await getProducts();
  list.value = arr.map((item) => {
    const a = data.find((i) => i.id === item.productId);
    return {
      ...item,
      productName: a ? a.name : '',
      productImage: a ? a.image : '',
    };
  });
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
