// MemberReducer.js

import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  listMembers: [],
  selectedMember: {}
};

export const memberSlice = createSlice({
  name: 'member',
  initialState,
  reducers: {
    getMembers: (state, action) => {
      state.listMembers = action.payload;
    },
    addMember: (state, action) => {
      state.listMembers.push(action.payload);
    },
    updateMember: (state, action) => {
      const currentMemberIndex = state.listMembers.findIndex(m => m.memberId === action.payload.memberId);
      state.listMembers[currentMemberIndex] = action.payload.member;
    },
    deleteMember: (state, action) => {
      state.listMembers = state.listMembers.filter(member => member.memberId !== action.payload);
      if (state.selectedMember.memberId === action.payload) {
        state.selectedMember = {};
      }
    },
    updateSelectedMember: (state, action) => {
      state.selectedMember = action.payload;
    },

    updateCorona: (state, action) => {
      const { memberId, updatedCoronaData } = action.payload;
      // Update corona data for the selected member if it matches the memberId
      if (state.selectedMember.memberId === memberId) {
        state.selectedMember.corona = updatedCoronaData;
      }
      // Update corona data for the corresponding member in the list of members
      const memberToUpdate = state.listMembers.find(member => member.memberId === memberId);
      if (memberToUpdate) {
        memberToUpdate.corona = updatedCoronaData;
      }
    },
    addVaccine: (state, action) => {
      const { memberId, newVaccine } = action.payload;
      // Find the selected member and update the corona object by adding the new vaccine
      if (state.selectedMember.memberId === memberId) {
        state.selectedMember.corona.vaccineList.push(newVaccine);
      }
      // Find the corresponding member in the list of members and update the corona object by adding the new vaccine
      const memberToUpdate = state.listMembers.find(member => member.memberId === memberId);
      if (memberToUpdate) {
        memberToUpdate.corona.vaccineList.push(newVaccine);
      }
    }
  },



  
});

export const { getMembers, addMember, updateMember, deleteMember, updateSelectedMember ,updateCorona,addVaccine} = memberSlice.actions;
export default memberSlice.reducer;
