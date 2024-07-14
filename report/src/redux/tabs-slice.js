import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  tabAside: 'tab3',
  tabMain: 'tab1',
  tabTest: 'tab1',
};

export const TabsSlice = createSlice({
  name: 'tabs',
  initialState,
  reducers: {
    setTabsAside: (state, action) => {
      state.tabAside = action.payload;
    },
    setTabsMain: (state, action) => {
      state.tabMain = action.payload;
    },
    setTabsTest: (state, action) => {
      state.tabTest = action.payload;
    },
  },
});

export const { setTabsAside, setTabsMain, setTabsTest } = TabsSlice.actions;
export const tabsReducer = TabsSlice.reducer;
