export default {
  state: {
    username: '',
    tel: '',
    answer: [],
    is_login: false,
    is_updating: true,
    is_searching: true,
    color: [0,0,0,0]
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
        state.color = [0, 0, 0, 0]
    },
    updateAnswer(state, data){
        if(data.mes === 'change'){
            if(data.i < 15){
                if(data.answer[data.i]!==-1)state.color[state.answer[data.i]]--;
            }else{
                if(data.answer[data.i]!==-1)state.color[3 - state.answer[data.i]]--;
            }
            state.answer[data.i] = data.answer;
            if(data.i < 15)
                state.color[state.answer[data.i]]++;
            else
                state.color[3 - state.answer[data.i]]++;
        }else{
            state.answer = [];
            for(let i = 0; i < 30; i++) state.answer.push(-1);
        }
    },
    updateColor(state, data){
        state.color[0] = data.red;
        state.color[1] = data.blue;
        state.color[2] = data.yellow;
        state.color[3] = data.green;
    },
    updateUp(state, data){
        state.is_updating = data;
    },
    updateIsSearch(state, data){
        state.is_searching = data;
    }
  },
  actions: {
  },
  modules: {
  }
}