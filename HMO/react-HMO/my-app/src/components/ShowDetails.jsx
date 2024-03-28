import { React, useState } from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import EditIcon from '@mui/icons-material/Edit';
import Dialog from '@mui/material/Dialog';
import Fab from '@mui/material/Fab';

import { useDispatch, useSelector } from 'react-redux';
import UpdateMember from './UpdateDetails';


const ExpandMore = styled((props) => {

  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function ShowDetails() {
  const [expanded, setExpanded] = useState(false);
  const [updateDetail, setUpdateDetail] = useState(false)
  const selectedMember = useSelector((state) => state.member.selectedMember);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };
  const handleEditClick = () => {
    setUpdateDetail(true)
  }

  return (
    <Card sx={{ maxWidth: 545, minWidth: 445 }}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            <img
              src={`data:image/jpg;base64,${selectedMember.image}`}
              alt={`Avatar of ${selectedMember.firstName}`}
              style={{ width: '100%', height: '100%', objectFit: 'cover' }}
            />
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title='Details'
        subheader={
          <>
            <b>   <span style={{ color: 'blue', fontSize: "20px" }}>{selectedMember.firstName}</span></b>
            {' '}
            <b><span style={{ color: 'blue', fontSize: "20px" }}>{selectedMember.lastName}</span></b>
          </>
        }
      />

      <CardContent>

        <Typography variant="body2" color="text.secondary">
          <b>ID:----------------------------------</b>{selectedMember.identity}<br />
          <b>Date of Birth:----------------------</b>{selectedMember.dateOfBirth}<br />
          <b>Telephone:-------------------------</b>{selectedMember.telephone}<br />
          <b>Cell Phone:------------------------</b>{selectedMember.cellPhone}<br />
          Address:<br />
          <b>City:----------------------</b>{selectedMember.address.city}<br />
          <b>Street:--------------------</b>{selectedMember.address.street}<br />
          <b>Home Number:----------</b>{selectedMember.address.houseNumber}<br />
        </Typography >

        

      </CardContent>

      <CardActions disableSpacing>


        <ExpandMore

          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"

        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        {selectedMember.corona.datePositiveResult || selectedMember.corona.vaccineList.length > 0 ? (
          <CardContent style={{ maxHeight: '300px', overflowY: 'auto' }}>
            <Typography paragraph>Corona Detail:</Typography>
            <Typography paragraph>
              <b>Date Positive Result:</b> {selectedMember.corona.datePositiveResult}<br />
              <b>Recovery Date:</b> {selectedMember.corona.recoveryDate}<br />
              {selectedMember.corona.vaccineList.map((vaccine, index) => (
                <div key={index}>
                  <Typography paragraph>
                    <b>Vaccine {index + 1}</b><br />
                    ID: {vaccine.id}<br />
                    Date: {vaccine.date}<br />
                    Manufacturer: {vaccine.manufacturer}
                  </Typography>
                </div>
              ))}
            </Typography>
          </CardContent>
        ) : (
          <CardContent>
            <Typography paragraph>No corona details available</Typography>
          </CardContent>
        )}
      </Collapse>
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
        id={selectedMember.id}
      >
        <UpdateMember />
      </Dialog>


    </Card>

  );
}