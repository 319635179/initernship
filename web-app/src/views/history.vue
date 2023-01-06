<template>
    <div class="history">
        <div class="history-card">
            <div style="width: 60%; height: 60px; display: flex; justify-content: space-between; align-items: center;">
                <button type="button" @click="clearHistory"
                    style="width: 40%; height: 60%; border: 0; background-color: darkred; border-radius: 24px; color: bisque;">清空历史记录</button>
                <button type="button" @click="$router.push({ name: 'login' })"
                    style="width: 40%; height: 60%; border: 0; background-color: darkred; border-radius: 24px; color: bisque;">返回登录</button>
            </div>
            <div class="aitem" v-for="(item, i) in historySub" @click="login(i)" :key="i">
                <div>姓名：{{ item[0] }}</div>
                <div>电话：{{ item[1] }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router';
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
    name: 'historyView',
    setup() {
        const store = useStore();
        let historySub = ref([]);
        let answerResult = localStorage.getItem('answerResult');
        historySub.value = answerResult.split(',');
        for (let i = 0; i < historySub.value.length; i++)
            historySub.value[i] = historySub.value[i].split('-');

        const login = (i) => {
            store.commit('updateUser', {
                username: historySub.value[i][0],
                tel: historySub.value[i][1],
                is_login: true,
            })
            router.push({ name: 'home' });
        }

        const clearHistory = () => {
            localStorage.removeItem('answerResult');
            router.push({ name: 'login' });
        }
        return {
            historySub,
            login,
            clearHistory
        }
    }
}

</script>

<style scoped>
.history {
    width: 100vw;
    height: 100vh;
    min-width: 720px;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(to bottom right, rgb(253, 185, 129), rgb(179, 107, 0));
}

.history-card {
    border-radius: 10px;
    box-shadow: 0px 0px 5px gray;
    padding: 10px 16px;
    width: 600px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    flex-shrink: 0;
    background-color: chocolate;
    color: tomato;
}

.aitem {
    margin: 10px 0;
    width: 60%;
    height: 60px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: black;
    border-radius: 5px;
}
</style>