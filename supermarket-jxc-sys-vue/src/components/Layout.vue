<template>
  <el-container style="height: 100vh">
    <el-header>
      <el-menu
        :default-active="activeIndex"
        mode="horizontal"
        @select="handleSelect"
        active-text-color="black"
        :ellipsis="false"
      >
        <el-menu-item
          v-for="item in accessibleNavigation"
          :key="item.path"
          :index="item.path"
        >
          {{ item.name }}
        </el-menu-item>

        <div style="display: flex; justify-content: right; flex: 1"></div>
        <div @click="logout">退出</div>
      </el-menu>
    </el-header>

    <el-main
      style="
        box-shadow: rgba(0, 0, 0, 0.25) 0px 14px 28px,
          rgba(0, 0, 0, 0.22) 0px 10px 10px;
        margin: 15px;
        box-sizing: border-box;
      "
    >
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const accessibleNavigation = computed(
      () => store.getters.accessibleNavigation
    );

    const activeIndex = computed(() => router.currentRoute.value.path);

    const handleSelect = (index) => {
      router.push(index);
    };
    const logout = () => {
      window.localStorage.setItem("showDialog", true);
      router.push("/");
    };
    return {
      accessibleNavigation,
      activeIndex,
      handleSelect,
      logout,
    };
  },
};
</script>

<style>
/* 这里可以添加一些自定义样式来美化导航栏 */
.el-header {
  background: #4995d7;
  color: #fff;
  line-height: 60px;
  padding: 0 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}
.el-menu {
  background: #4995d7 !important;

  border: 0;
  flex-shrink: 0;
}
</style>
