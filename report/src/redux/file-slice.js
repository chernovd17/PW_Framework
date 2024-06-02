import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  fileList: [],
  fileInfo: {},
};

export const FileSlice = createSlice({
  name: 'file',
  initialState,
  reducers: {
    setFile: (state, action) => {
      state.fileInfo = action.payload;
    },
    setFileList: (state, action) => {
      state.fileList = action.payload;
    },
  },
});

export const { setFile, setFileList } = FileSlice.actions;
export const fileReducer = FileSlice.reducer;
