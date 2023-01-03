export default {
  state: {
    username: '',
    tel: '',
    answer: [],
    is_login: false,
    is_updating: true,
  },
  getters: {
  },
  mutations: {
    updateUser(state, data){
        state.username = data.username;
        state.tel = data.tel;
        state.is_login = data.is_login;
        if(state.is_login){
            localStorage.setItem('userMes', state.username + '-' + state.tel);
            state.answer = [];
            for(let i = 0; i < 30; i++) state.answer.push(-1);
        }else{
            localStorage.removeItem("userMes");
        }
    },
    updateAnswer(state, data){
        if(data.mes === 'change'){
            state.answer[data.i] = data.answer;
        }else{
            state.answer = [];
            for(let i = 0; i < 30; i++) state.answer.push(-1);
        }
    },
    updateUp(state, data){
        state.is_updating = data;
    }
  },
  actions: {
  },
  modules: {
  }
}