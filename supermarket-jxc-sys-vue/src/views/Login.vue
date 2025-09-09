<template>
    <div class="login-background">
        
        <el-card class="login-card">
            <h2 class="title">登录到您的账户</h2>
            <el-form ref="form" :model="loginForm" @submit.prevent="handleLogin">
                <el-form-item label="用户名" prop="username" label-width="">
                    <el-input v-model="loginForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="loginForm.password" type="password"></el-input>
                </el-form-item>

                <el-form-item label="角色">

                    <el-radio-group v-model="loginForm.roleId" class="ml-4">
                        <el-radio :value="1" size="large">管理员</el-radio>
                        <el-radio :value="0" size="large">员工</el-radio>
                    </el-radio-group>

                </el-form-item>

                <el-form-item>
                    <el-button class="full-width" type="primary" @click="handleLogin"
                    >登录
                    </el-button
                    >
                </el-form-item>
            </el-form>
        </el-card>
        
    </div>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import {getCaptchaNbr, login} from "@/api/user";
import {ElMessage} from "element-plus";
import {useStore} from "vuex";

const store = useStore();
const code = ref("");

async function init() {
    const {data} = await getCaptchaNbr();
    code.value = data;
}

init();
const router = useRouter();
const form = ref();
const loginForm = ref({
    username: "",
    password: "",
    roleId: 1,
});
const handleLogin = async () => {
    // 实际登录逻辑会在这里实现，例如 API 调用
    await init();
    const res = await login({...loginForm.value, captcha: code.value});
    if (res.status === "fail") {
        ElMessage.warning(res.message);
        return;
    }
    const token = res.data;
    let strings = token.split("."); //截取token，获取载体
    var userinfo = JSON.parse(
        decodeURIComponent(
            escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/")))
        )
    ); //解析，需要吧‘_’,'-'进行转换否则会无法解析
    localStorage.setItem("token", token);

    store.commit("setUser", {...JSON.parse(userinfo.sub), ...loginForm.value});
    window.localStorage.setItem("showDialog", true);
    localStorage.setItem("userInfo", JSON.stringify(store.state.userInfo));
    store.commit("changeUserPermissions", "admin");
    router.push("/admin/product-management");
};
</script>

<style scoped>
.login-background {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    /* background: linear-gradient(135deg, #667eea 0%, #4995d7 100%); */
    background: url('../views/login_bg.png') no-repeat center center;
    background-size: cover;
}

.login-card {
    width: 100%;
    max-width: 360px;
    padding: 40px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.title {
    text-align: center;
    margin-bottom: 30px;
    color: #333;
}

.full-width {
    width: 100%;
}
.input-field {
    width: 100%;
}
.register-link {
    text-align: center;
    display: block;
    color: #333;
}
</style>
