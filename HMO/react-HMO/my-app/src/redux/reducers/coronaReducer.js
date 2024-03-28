// CoronaReducer.js

import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  listCorona: [],
  
};

export const coronaSlice = createSlice({
  name: 'corona',
  initialState,
  reducers: {
    getCorona: (state, action) => {
      state.listCorona = action.payload;
    },
    
  },



  
});

export const { getCorona} = coronaSlice.actions;
export default coronaSlice.reducer;
