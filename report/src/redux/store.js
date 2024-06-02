import { configureStore } from '@reduxjs/toolkit/react';
import { tabsReducer } from './tabs-slice';
import { fileReducer } from './file-slice';

export const store = configureStore({
  reducer: {
    tabs: tabsReducer,
    file: fileReducer,
  },
});
