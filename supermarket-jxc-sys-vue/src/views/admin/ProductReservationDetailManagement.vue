<template>
  <el-container class="eid-management-container">
    <el-card class="eid-management-card">
      <h2>预约详情管理</h2>
      <el-button type="primary" @click="showDialog = true">新增</el-button>
      <el-table :data="list" style="width: 100%; margin-top: 20px">
        <el-table-column prop="productName" label="商品名称"></el-table-column>
        <el-table-column prop="CreateTime" label="创建时间"></el-table-column>
        <el-table-column
          prop="reservationQuantity"
          label="预约数量"
        ></el-table-column>
        <el-table-column label="操作">
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

    <el-dialog title="预约详情" v-model="showDialog">
      <el-form :model="selectedModel" label-width="120px">
        <el-form-item label="商品">
          <el-select v-model="selectedModel.productName">
            <el-option
              v-for="item in products"
              :key="item.id"
              :value="item.name"
              :label="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预约数" prop="reservationQuantity">
          <el-input-number
            v-model="selectedModel.reservationQuantity"
          ></el-input-number>
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
import * as api from "@/api/ProductReservationDetail.js";
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
const open = () => {
  showDialog.value = true;
};
const viewDetails = (item) => {
console.log(item)
  selectedModel.value = { ...item };
  open();
  // 实现查看申请详情的逻辑
};
const cancel = () => {
  showDialog.value = false;
  selectedModel.value = {};
};
const submit = async () => {
  const fn = selectedModel.value.id
    ? api.updateItem
    : api.addItem;
  const ProductReservationId = selectedModel.value.id
    ? selectedModel.value.productReservationId
    : null;
  const { code, message } = await fn({
    ...selectedModel.value,
    ReservationQuantity: selectedModel.value.reservationQuantity,
    ProductName: selectedModel.value.productName,
    ProductReservationId,
    CustomerId: selectedModel.value.customerId || 111,
    SupplierId: selectedModel.value.supplierId || 111,
    Id: selectedModel.value.id
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