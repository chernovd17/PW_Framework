import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  fileList: [],
  fileInfo: {},
  testName: null
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
    setTestName: (state, action) => {
      state.testName = action.payload
    }
  },
});

export const { setFile, setFileList, setTestName } = FileSlice.actions;
export const fileReducer = FileSlice.reducer;
