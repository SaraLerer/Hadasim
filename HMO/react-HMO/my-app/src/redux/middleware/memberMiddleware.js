import axios from 'axios';
import { getMembers, addMember, deleteMember, updateMember ,updateCorona,addVaccine} from '../reducers/memberReducer';

export const getMemberMidd = ({ dispatch }) => next => action => {
  if (action.type === 'GET_MEMBERS') {
    axios.get("http://localhost:8585/api/member/getAllMembers")
      .then((response) => {
        dispatch(getMembers(response.data));
      })
      .catch((error) => {
        console.error('Error fetching members:', error);
      });
  }

  return next(action);
};

export const addMemberMidd = ({ dispatch }) => next => action => {
  if (action.type === 'ADD_MEMBER') {
    const { updateMember, img } = action.payload;
    const formData = new FormData();
    console.log("member",updateMember)
    console.log("img",img)
    formData.append('image', img);
    
    formData.append('member', new Blob([JSON.stringify(updateMember)], { type: 'application/json' }));
    axios.post('http://localhost:8585/api/member/uploadMember', formData)
      .then((response) => {
        dispatch(addMember(response.data));
      })
      .catch((error) => {
        console.error('Error uploading the member:', error);
      });
  }

  return next(action);
};


export const addMemberWithoutImgMidd = ({ dispatch }) => next => action => {
  if (action.type === 'ADD_MEMBER_WITHOUT_IMG') {
    const { updateMember} = action.payload;
   console.log("member",updateMember)
   
    
    axios.post('http://localhost:8585/api/member/uploadMemberwithoutimg', updateMember)
      .then((response) => {
        dispatch(addMember(response.data));
      })
      .catch((error) => {
        console.error('Error uploading the member:', error);
      });
  }

  return next(action);
};

export const updateMemberMidd = ({ dispatch }) => next => action => {
  if (action.type === 'UPDATE_MEMBER') {
    const { member} = action.payload;
   console.log("member",member);
    console.log("id",member.id)
    axios.put(`http://localhost:8585/api/member/updateMemberWithout/${member.id}`, member)

      .then((response) => {
        dispatch(updateMember(response.data));
      })
      .catch((error) => {
        console.error('Error updating the member:', error);
      });
  }

  return next(action);
};



export const deleteMemberMidd = ({ dispatch }) => next => action => {
  if (action.type === 'DELETE_MEMBER') {
    const memberId = action.payload;
    axios.delete(`http://localhost:8585/api/member/deleteMember/${memberId}`)
      .then(() => {
        // Dispatch the deleteMember action with the memberId payload
        dispatch(deleteMember(memberId));
      })
      .catch((error) => {
        console.error('Error deleting the member:', error);
      });
  }

  return next(action);
};


export const updateCoronaMidd = ({ dispatch }) => next => action => {
  if (action.type === 'UPDATE_CORONA') {
    const { corona } = action.payload;
   console.log("corona",corona);
    console.log("id",corona.id)
    axios.put(`http://localhost:8585/api/corona/updateCorona/${corona.id}`, corona)

      .then((response) => {
        dispatch(updateCorona(response.data));
      })
      .catch((error) => {
        console.error('Error updating the corona:', error);
      });
  }

  return next(action);
};


export const addVaccineMidd = ({ dispatch }) => next => action => {
  if (action.type === 'ADD_VACCINE') {
    const { vaccine,corona_id } = action.payload;
   console.log("vaccine",vaccine);
   console.log("corona_id ",corona_id );
   
    axios.post(`http://localhost:8585/api/vaccine/addVaccine/${corona_id }`, vaccine)

      .then((response) => {
        dispatch(addVaccine(response.data));
      })
      .catch((error) => {
        console.error('Error adding the vaccine:', error);
      });
  }

  return next(action);
};

