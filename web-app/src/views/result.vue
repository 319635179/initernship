<template>
    <div style="width: 100vw; height: 100vh; display: flex; justify-content: center; flex-wrap: wrap; flex-shrink: 0;
        padding: 20px 50px;">
        <div class="userMesCard">
            <div class="userMes">
                <div>用户名: {{ $store.state.user.username }}</div>
                <div>电话: {{ $store.state.user.tel }}</div>
                <div @click="again">再测一次</div>
                <div @click="logout">退出</div>
            </div>
            <div
                style="width: 100%; height: 100px; margin: 10px 0; display: flex; align-items: center; justify-content: center; font-size: 40px;">
                FPA测试分析
            </div>
        </div>
        <div class="pie" id="pie"></div>
        <div style="width: 100%; display: flex; justify-content: center; height: 80px;">
            <div style="width: 800px; display: flex; align-items: center; justify-content: space-around;">
                <button @click="nowWant = 0"
                    style="border: 0; width: 100px; height: 60%; background-color: crimson; border-radius: 5px; "
                    v-if="color_is[0]">红色性格</button>
                <button @click="nowWant = 1"
                    style="border: 0; width: 100px; height: 60%; background-color: skyblue; border-radius: 5px; "
                    v-if="color_is[1]">蓝色性格</button>
                <button @click="nowWant = 2"
                    style="border: 0; width: 100px; height: 60%; background-color: yellow; border-radius: 5px; "
                    v-if="color_is[2]">黄色性格</button>
                <button @click="nowWant = 3"
                    style="border: 0; width: 100px; height: 60%; background-color: greenyellow; border-radius: 5px; "
                    v-if="color_is[3]">绿色性格</button>
            </div>
        </div>
        <RedCom v-if="nowWant === 0" />
        <BlueCom v-if="nowWant === 1" />
        <YellowCom v-if="nowWant === 2" />
        <GreenCom v-if="nowWant === 3" />
    </div>
</template>

<script>
import { onMounted } from 'vue';
import * as echarts from 'echarts';
import { ref } from 'vue';
import { useStore } from 'vuex';
import $ from 'jquery';
import router from '@/router';
import RedCom from '@/components/red.vue'
import BlueCom from '@/components/blue.vue'
import YellowCom from '@/components/yellow.vue'
import GreenCom from '@/components/green.vue'

export default {
    name: 'ResultView',
    components: { RedCom, BlueCom, YellowCom, GreenCom },
    setup() {
        const store = useStore();
        let option = ref({
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: 40,
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: [
                        { value: store.state.user.color[1], name: '蓝色性格' },
                        { value: store.state.user.color[3], name: '绿色性格' },
                        { value: store.state.user.color[2], name: '黄色性格' },
                        { value: store.state.user.color[0], name: '红色性格' },
                    ]
                }
            ]
        })

        let nowWant = ref(0);
        let color_is = ref([false, false, false, false]);
        let maxn = 0;
        for (let i = 0; i < 4; i++)
            if (store.state.user.color[i] > maxn) maxn = store.state.user.color[i];

        for (let i = 0; i < 4; i++) {
            if (store.state.user.color[i] === maxn) {
                color_is.value[i] = true;
                nowWant.value = i;
            }
        }

        const intiDom = () => {
            let chartDom = document.getElementById('pie');
            let mychart = echarts.init(chartDom);
            option.value && mychart.setOption(option.value);
        }

        onMounted(() => {
            intiDom();
        })

        const again = () => {
            $.ajax({
                url: 'http://localhost:3000/questions/answer/delete',
                type: 'post',
                data: {
                    username: store.state.user.username,
                    tel: store.state.user.tel,
                },
                success: resp => {
                    if (resp.error_message === 'success')
                        router.push({ name: 'home' });
                }
            })
        }

        const logout = () => {
            store.commit('updateUser', {
                username: '',
                tel: '',
                is_login: false,
            })
            router.push({ name: 'login' });
        }

        return {
            again,
            logout,
            nowWant,
            color_is
        }
    }
}

</script>

<style scoped>
.pie {
    width: 600px;
    height: 600px;
}

.userMesCard {
    width: 100%;
    height: 180px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    flex-shrink: 0;
}

.userMes {
    width: 800px;
    height: 70px;
    background-color: rgba(135, 207, 235, 0.377);
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid skyblue;
}

.userMes>div {
    width: 20%;
    display: flex;
    justify-content: center;
}
</style>