import { createStore } from 'vuex';

export default createStore({
  state: {
    currentPage: null
  },
  mutations: {
    setCurrentPage(state, page) {
      state.currentPage = page;
    }
  },
  actions: {
    setCurrentPage({ commit }, page) {
      commit('setCurrentPage', page);
    }
  },
  modules: {}
});
