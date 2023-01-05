<template>
    <div class="home">
        <div class="home-view-top">
            <div class="home-view-top-title">
                <div style="font-size: 40px; width: 100%; display: flex; justify-content: center; font-weight: 800;">
                    FPA性格测试</div>
                <div style="color: gray; font-weight: 600; font-size: 14px;">完成测试，获得专业报告</div>
            </div>

            <div class="home-view-top-user">
                <div style="width: 33%; display: flex; align-items: center; justify-content: center;">
                    名字：{{ $store.state.user.username }}
                </div>
                <div style="width: 33%; display: flex; align-items: center; justify-content: center;">
                    电话：{{ $store.state.user.tel }}</div>
                <div style="width: 33%; display: flex; align-items: center; justify-content: center;" @click="logout">
                    退出
                </div>
            </div>
        </div>
        <div class="home-view-bottum">
            <div class="question-card" v-if="!is_search">
                <div class="progress">
                    <div :style="process">{{ process.width === '0%' ? '' : process.width }}</div>
                </div>
                <div class="question-title"> {{ nowQuestion + 1 }}、{{ questions[nowQuestion].question }}</div>
                <div class="question-answer">
                    <div class="question-answer-item" @click="selectItem(0)">
                        <div class="qai-select" :style="ansStyle[0]"></div>
                        <div>A、{{ questions[nowQuestion].ansA }}</div>
                    </div>
                    <div class="question-answer-item" @click="selectItem(1)">
                        <div class="qai-select" :style="ansStyle[1]"></div>
                        <div>B、{{ questions[nowQuestion].ansB }}</div>
                    </div>
                    <div class="question-answer-item" @click="selectItem(2)">
                        <div class="qai-select" :style="ansStyle[2]"></div>
                        <div>C、{{ questions[nowQuestion].ansC }}</div>
                    </div>
                    <div class="question-answer-item" @click="selectItem(3)">
                        <div class="qai-select" :style="ansStyle[3]"></div>
                        <div>D、{{ questions[nowQuestion].ansD }}</div>
                    </div>
                </div>
                <div class="question-card-btn">
                    <button v-if="nowQuestion !== 0" @click="changeNowQuestion(nowQuestion - 1)">上一题</button>
                    <button v-if="nowQuestion !== 29" @click="changeNowQuestion(nowQuestion + 1)">下一题</button>
                    <button v-if="nowQuestion === 29" @click="submit">提交</button>
                </div>

            </div>
            <div class="question-list-item">
                <div v-for="(q, i) in questions" :key="q.id"
                    :style="{ backgroundColor: $store.state.user.answer[i] === -1 ? 'skyblue' : 'coral' }" class="Aitem"
                    @click="changeNowQuestion(i)">
                    {{ i + 1 }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router';
import $ from 'jquery'
import { useStore } from 'vuex';
import { ref } from 'vue';

export default {
    name: 'HomeView',
    setup() {
        const store = useStore();
        let is_search = ref(true);

        let questions = ref();
        let nowQuestion = ref(0);

        let ansStyle = ref([{
            backgroundColor: 'white',
        }, {
            backgroundColor: 'white',
        }, {
            backgroundColor: 'white',
        }, {
            backgroundColor: 'white',
        }])

        $.ajax({
            url: 'http://localhost:3000/questions/answer/getbyid',
            type: 'post',
            data: {
                username: store.state.user.username,
                tel: store.state.user.tel,
            },
            success: resp => {
                if (resp.error_message === 'success') {
                    let userMes = resp.data.split('(')[1].split(', ');
                    store.commit('updateColor', {
                        red: parseInt(userMes[5].split('=')[1]),
                        blue: parseInt(userMes[6].split('=')[1]),
                        yellow: parseInt(userMes[7].split('=')[1]),
                        green: parseInt(userMes[8].split('=')[1]),
                    })
                    router.push({ name: 'result' });
                }
            }
        })

        $.ajax({
            url: 'http://localhost:3000/questions/query',
            type: 'get',
            success: resp => {
                if (resp.code === 0) {
                    questions.value = resp.data;
                    is_search.value = false;
                }
            }
        })

        let answer = store.state.user.answer[0];
        for (let i = 0; i < 4; i++) {
            if (i === answer) ansStyle.value[i].backgroundColor = 'chocolate';
            else ansStyle.value[i].backgroundColor = 'white';
        }
        let pro = 0;

        for (let i = 0; i < 30; i++) {
            if (store.state.user.answer[i] !== -1) pro++;
        }

        let process = ref({
            fontSize: '12px',
            height: '16px',
            width: ((pro) * 100 / 30).toFixed(0) + '%',
            backgroundColor: 'deepskyblue',
            borderRadius: '8px',
            transition: '100ms',
            padding: '0 10px',
        })

        const selectItem = num => {
            if (answer === -1) {
                let width = (pro++ + 1) * 100 / 30;
                process.value.width = width.toFixed(0) + '%';
            }
            for (let i = 0; i < 4; i++) {
                if (i === num) ansStyle.value[i].backgroundColor = 'chocolate';
                else ansStyle.value[i].backgroundColor = 'white';
            }
            answer = num;
            store.commit('updateAnswer', {
                mes: 'change',
                i: nowQuestion.value,
                answer,
            })
            if (nowQuestion.value !== 29)
                changeNowQuestion(nowQuestion.value + 1);
        }

        const logout = () => {
            store.commit('updateUser', {
                username: '',
                tel: '',
                is_login: false,
            })
            router.push({ name: 'login' });
        }

        const changeNowQuestion = num => {
            nowQuestion.value = num;
            answer = store.state.user.answer[num];
            for (let i = 0; i < 4; i++) {
                if (i === answer) ansStyle.value[i].backgroundColor = 'chocolate';
                else ansStyle.value[i].backgroundColor = 'white';
            }
        }

        const submit = () => {
            let submitAns = '';
            for (let i = 0; i < 30; i++) {
                if (store.state.user.answer[i] === -1) {
                    alert("还有题目未完成");
                    return;
                } else {
                    if (store.state.user.answer[i] === 0) submitAns += 'A';
                    if (store.state.user.answer[i] === 1) submitAns += 'B';
                    if (store.state.user.answer[i] === 2) submitAns += 'C';
                    if (store.state.user.answer[i] === 3) submitAns += 'D';
                }
            }
            // let answerResult = localStorage.getItem("answerResult");
            // if (answerResult !== null)
            //     localStorage.removeItem('answerResult');
            // localStorage.setItem(store.state.user.username + '-' + store.state.user.tel);
            $.ajax({
                url: 'http://localhost:3000/questions/answer/add',
                type: 'post',
                data: {
                    username: store.state.user.username,
                    tel: store.state.user.tel,
                    answer: submitAns
                },
                success: resp => {
                    if (resp.error_message === 'success') router.push({ name: 'result' });
                }

            })
        }
        return {
            is_search,
            process,
            questions,
            nowQuestion,
            ansStyle,
            selectItem,
            logout,
            changeNowQuestion,
            submit
        }
    }
}

</script>

<style scoped>
.home {
    width: 100vw;
    min-width: 1000px;
    height: 100vh;
    min-height: 900px;
}

.home-view-top {
    width: 100%;
    height: 37%;
    background-color: goldenrod;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    flex-shrink: 0;
}

.home-view-top-title {
    width: 100%;
    height: 80px;
    /* font-size: 40px; */
    /* font-weight: 800; */
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    flex-shrink: 0;
    transform: translateY(-50px);
}

.home-view-top-user {
    height: 80px;
    width: 500px;
    display: flex;
    align-items: center;
    justify-content: space-around;
    border-radius: 5px;
    background-color: rgba(135, 206, 250, 0.233);
    border: 1px solid lightskyblue;
    position: absolute;
    transform: translateY(50px);
}

.home-view-bottum {
    width: 100%;
    height: 63%;
    display: flex;
    justify-content: center;
}

.question-card {
    width: 800px;
    height: 500px;
    background-color: brown;
    color: oldlace;
    border-radius: 10px;
    box-shadow: 0 0 5px rebeccapurple;
    transform: translateY(-50px);
    padding: 0 20px;
}

.progress {
    height: 16px;
    width: 100%;
    transform: translateY(10px);
    border-radius: 8px;
    background-color: aliceblue;
}

.question-title {
    width: 100%;
    height: 80px;
    line-height: 80px;
    font-size: 20px;
    border-bottom: 1px solid coral;
}

.question-answer {
    margin-top: 10px;
    height: 280px;
    width: 100%;
    line-height: 40px;
}

.question-answer-item {
    display: flex;
    align-items: center;
}

.qai-select {
    width: 14px;
    height: 14px;
    border-radius: 50%;
    border: 1px solid lightskyblue;
    margin: 0 10px;
}

.question-card-btn {
    height: 114px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
}

.question-card-btn>button {
    border: 0;
    background-color: chocolate;
    color: antiquewhite;
    width: 170px;
    height: 50px;
    border-radius: 25px;
}

.question-list-item {
    width: 240px;
    height: 300px;
    display: flex;
    flex-wrap: wrap;
    flex-shrink: 0;
    justify-content: space-around;
    position: absolute;
    left: 20px;
    transform: translateY(20px);
}

.Aitem {
    width: 15%;
    height: 36px;
    margin: 2px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: skyblue;
}
</style>