import React, { useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useSelector, useDispatch } from 'react-redux';
import { updateSelectedMember } from "../redux/reducers/memberReducer";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from '@mui/material/IconButton';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import Dialog from '@mui/material/Dialog';
import ShowDetails from './ShowDetails';
import AddMember from './AddMember';
import AddOrUpdateCorona from './AddOrUpdateCorona';
import EditIcon from '@mui/icons-material/Edit';
import UpdateMember from './UpdateDetails';

const MemberList = () => {
  const members = useSelector((state) => state.member.listMembers);
  const dispatch = useDispatch();
  const [showDetailsDialog, setShowDetailsDialog] = useState(false);
  const [selectedMember, setSelectedMember] = useState(null);
  const [addMember, setAddMember] = useState(false);
  const [addOrUpdateCorona, setAddOrUpdateCorona] = useState(false);
  const [updateDetail, setUpdateDetail] = useState(false);

  const handleUpdateSelectedMember = (member) => {
    if (member && member.id) {
      const selectedMember = members.find(m => m.id === member.id);
      dispatch(updateSelectedMember(selectedMember));
      setShowDetailsDialog(true);
    }
  };

  const handleEditClick = (member) => {
    if (member && member.id) {
      const selectedMember = members.find(m => m.id === member.id);
      dispatch(updateSelectedMember(selectedMember));
      setUpdateDetail(true);
    }
  };

  const handleDeleteMember = (member) => {
    if (member && member.id) {
      dispatch({ type: 'DELETE_MEMBER', payload: member.id });
    }
  };

  const handleAddMember = () => {
    setAddMember(true);
  };

  const handleAddOrUpdateCorona = (member) => {
    if (member && member.id) {
      const selectedMember = members.find(m => m.id === member.id);
      dispatch(updateSelectedMember(selectedMember));
      setAddOrUpdateCorona(true);
    }
  };


  const rows = members.map((member) => {
    if (member && member.id) {
      return {
        id: member.id,
        firstName: member.firstName,
        lastName: member.lastName,
        hasCoronaCard: member.corona && (member.corona.datePositiveResult !== null || (member.corona.vaccineList && member.corona.vaccineList.length > 0))

      };
    }
    return null; 
  }).filter(Boolean); 

  const columns = [
    { field: 'firstName', headerName: 'First Name', width: 250 ,color:"#0000CD"},
    { field: 'lastName', headerName: 'Last Name', width: 250 },
    
    {
      field: 'show details',
      headerName: 'Show details',
      width: 200,
      color:"#0000CD",
      renderCell: (params) => (
        <button onClick={() => handleUpdateSelectedMember(params.row)} style={{  color: "#0000CD" }}>Details</button>
      ),
    },
    
    {
      field: 'corona',
      headerName: 'Corona',
      width: 240,
      color: "#0000CD",
      renderCell: (params) => {
        const { row } = params;
        if (row.hasCoronaCard) {
          return <button onClick={() => handleAddOrUpdateCorona(row)} style={{ backgroundColor: "", color: "#BC8F8F" }}>Update Corona</button>;
        } else {
          return <button onClick={() => handleAddOrUpdateCorona(row)} style={{ backgroundColor: "", color: "#0000CD" }}>Add Corona</button>;
        }
      },
    },
    {
      field: 'edit',
      headerName: 'Edit',
      width: 200,
      color:"#yellow",
      renderCell: (params) => (
        <div>
          <Fab color="secondary" aria-label="edit" sx={{ width: '32px', height: '32px', color:'white', background: '#0000CD'}} onClick={()=>handleEditClick(params.row)}>
            <EditIcon />
          </Fab>
         
        </div>
      ),
    },
    
    {
      field: 'delete',
      headerName: 'Delete',
      width: 150,
      color:"#0000CD",
      renderCell: (params) => (
        <IconButton
          aria-label="delete"
          size="large"
          onClick={() => handleDeleteMember(params.row)}
        >
          <DeleteIcon fontSize="inherit" />
        </IconButton>
      ),
    },
  ];

  return (
    <>
   
      <div style={{ height: 480, width: '1300px',marginTop:"10%" }}>
        <DataGrid
          rows={rows}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5, 10, 20]}
          disableSelectionOnClick
        />
      </div>
      <Fab color="primary" aria-label="add" onClick={handleAddMember}>
        <AddIcon />
      </Fab>
      <Dialog open={showDetailsDialog} onClose={() => setShowDetailsDialog(false)}>
        <ShowDetails />
      </Dialog>
      <Dialog
        open={addMember}
        onClose={() => setAddMember(false)}
        maxWidth="md"
        fullWidth
        PaperProps={{
          style: {
            maxHeight: '180vh',
          },
        }}
      >
        <AddMember />
      </Dialog>
      <Dialog
        open={addOrUpdateCorona}
        onClose={() => setAddOrUpdateCorona(false)}
        maxWidth="md"
        fullWidth
        PaperProps={{
          style: {
            maxHeight: '180vh',
          },
        }}
      >
        <AddOrUpdateCorona />
      </Dialog>
     
      <Dialog
        open={updateDetail}
        onClose={() => setUpdateDetail(false)}
        maxWidth="md"
        fullWidth
        PaperProps={{
          style: {
            maxHeight: '180vh', 
          },
        }}
      >
        <UpdateMember />
      </Dialog>
    </>
  );
};

export default MemberList;
