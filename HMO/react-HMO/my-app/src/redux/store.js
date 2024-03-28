import { configureStore } from '@reduxjs/toolkit';
import memberReducer from './reducers/memberReducer';
import coronaReducer from './reducers/coronaReducer';
import { getMemberMidd, addMemberWithoutImgMidd,addMemberMidd ,deleteMemberMidd,updateMemberMidd,updateCoronaMidd,addVaccineMidd} from './middleware/memberMiddleware'; // Make sure to import the middleware correctly
import { getCoronaMidd } from './middleware/coronaMiddleware';
export const store = configureStore({
  reducer: {
    member: memberReducer,
    corona:coronaReducer

  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ serializableCheck: false }).concat(getMemberMidd,addMemberWithoutImgMidd, addMemberMidd,deleteMemberMidd,updateMemberMidd,updateCoronaMidd,addVaccineMidd,getCoronaMidd), // Concatenate the middleware correctly
});
