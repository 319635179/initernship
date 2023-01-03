<template>
    <div class="login" v-if="!$store.state.user.is_updating">
        <div class="login-card">
            <div class="login-card-title">FPA性格测试</div>
            <div>
                <label for="username">名字</label>
                <input type="text" placeholder="请输入名字" id="username" name="username" v-model="username">
            </div>
            <div>
                <label for="tel">电话</label>
                <input type="text" placeholder="请输入电话" id="tel" name="tel" v-model="tel">
            </div>
            <div>
                <button type="button" @click="login">去评测</button>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router';
import { ref } from 'vue';
import { useStore } from 'vuex';


export default {
    name: 'LoginView',
    setup() {
        const store = useStore();
        let username = ref(''), tel = ref('');

        const userMes = localStorage.getItem('userMes');
        if (userMes) {
            const user = userMes.split('-');
            store.commit("updateUser", {
                username: user[0],
                tel: user[1],
                is_login: true,
            })
            router.push({ name: 'home' });
        }
        store.commit('updateUp', false);

        const login = () => {
            if (username.value !== '' && tel.value !== '' && username.value !== null && tel.value !== null) {
                store.commit('updateUser', {
                    username: username.value,
                    tel: tel.value,
                    is_login: true,
                })
                router.push({ name: 'home' });
            } else {
                window.alert("用户名或电话不能为空");
            }
        }
        return {
            username,
            tel,
            login
        }
    }
}

</script>

<style scoped>
.login {
    width: 100vw;
    height: 100vh;
    min-width: 720px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-card {
    border-radius: 10px;
    box-shadow: 0px 0px 5px gray;
    padding: 10px 16px;
    width: 600px;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    flex-shrink: 0;
    background-color: snow;
    color: tomato;
}

.login-card-title {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    font-size: 30px;
    font-weight: 700;

}

.login-card>div {
    width: 70%;
    margin: 10px 15%;
    height: 60px;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    flex-shrink: 0;
}

.login-card>div>label {
    width: 30%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 18px;
    font-weight: 600;
    border-left: 5px solid lightblue;
}

.login-card>div>input {
    width: 70%;
    height: 40px;
    border: 1px solid tomato;
    border-radius: 30px;
    padding: 0 10px;
}

.login-card>div>button {
    width: 100%;
    border: 0;
    height: 40px;
    border-radius: 20px;
    background-color: chocolate;
    color: antiquewhite;
}
</style>