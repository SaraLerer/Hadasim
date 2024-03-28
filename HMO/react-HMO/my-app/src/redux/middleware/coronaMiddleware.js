import axios from 'axios';
import { getCorona } from '../reducers/coronaReducer';
export const getCoronaMidd = ({ dispatch }) => next => action => {
  if (action.type === 'GET_CORONA') {
    axios.get("http://localhost:8585/api/corona/getAll")
      .then((response) => {
        dispatch(getCorona(response.data));
      })
      .catch((error) => {
        console.error('Error fetching corona:', error);
      });
  }

  return next(action);
};

