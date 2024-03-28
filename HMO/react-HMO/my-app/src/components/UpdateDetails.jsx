import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useDispatch, useSelector } from 'react-redux';

function AddMember() {
    const dispatch = useDispatch();
    const selectedMember = useSelector((state) => state.member.selectedMember);
    const members = useSelector((state) => state.member.listMembers);

    const [state, setState] = useState(selectedMember);
    const [errors, setErrors] = useState({});
    const [message, setMessage] = useState("")

    const handleChange = (event) => {
        const { name, value } = event.target;
        setState(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleAddressChange = (event) => {
        const { name, value } = event.target;
        setState(prevState => ({
            ...prevState,
            address: {
                ...prevState.address,
                [name]: value
            }
        }));
    };

    const validateFields = () => {
        const errors = {};

        // Check required fields
        if (!state.firstName.trim()) {
            errors.firstName = "First Name is required";
        }
        if (!state.lastName.trim()) {
            errors.lastName = "Last Name is required";
        }
        if (!state.identity.trim()) {
            errors.identity = "ID Card is required";
        }
        if (!state.dateOfBirth.trim()) {
            errors.dateOfBirth = "Date of Birth is required";
        }
        if (!state.telephone.trim()) {
            errors.telephone = "Telephone is required";
        }
        if (!state.cellPhone.trim()) {
            errors.cellPhone = "Cell Phone is required";
        }
        if (!state.address.city.trim()) {
            errors.city = "City is required";
        }
        if (!state.address.street.trim()) {
            errors.street = "Street is required";
        }
        if (!state.address.houseNumber.trim()) {
            errors.houseNumber = "House Number is required";
        }

        // Phone number validation (should have 9 or 10 digits)
        const isValidTelephone = /^\d{9}$/.test(state.telephone);
        const isValidCellPhone = /^\d{10}$/.test(state.cellPhone);
        if (!isValidTelephone) {
            errors.telephone = "Telephone must be a string of 9 digits";
        }
        if (!isValidCellPhone) {
            errors.cellPhone = "Cell Phone must be a string of 10 digits";
        }

        // Validate date of birth
        const today = new Date();
        const birthDate = new Date(state.dateOfBirth);
        if (isNaN(birthDate.getTime()) || birthDate >= today) {
            errors.dateOfBirth = "Date of Birth must be a valid date preceding today's date";
        }

        // ID number validation (should have 9 digits)
        const isValidIdentity = /^\d{9}$/.test(state.identity);
        if (!isValidIdentity) {
            errors.identity = "ID number must be a string of 9 digits";
        }


        setErrors(errors);

        return Object.keys(errors).length === 0;
    };

    const upload = () => {
        const isValid = validateFields();

        if (!isValid) {
            return; // Prevent upload if there are validation errors
        }
        const existsInlist = members.some(member => member.identity === state.identity && member.id != state.id);
        if (existsInlist) {
            setMessage('This member already exists in the system.');
            return; // Prevent upload if the member already exists
        }


        const member = {
            ...state,
            address: { ...state.address },
        };
        setMessage("")
        Request(member);
    };

    const Request = (member) => {
        dispatch({
            type: 'UPDATE_MEMBER',
            payload: { member }
        });
    };

    return (
        <>
            <Box sx={{ '& > :not(style)': { m: 1, width: '50ch' } }}>
            <h3 style={{ color: "purple"}}>Update member</h3>

                <TextField
                    required
                    id="outlined-required-firstName"
                    name="firstName"
                    label="First Name"
                    variant="outlined"
                    value={state.firstName}
                    onChange={handleChange}
                    error={!!errors.firstName}
                    helperText={errors.firstName}
                />
                <TextField
                    required
                    id="outlined-required-lastName"
                    name="lastName"
                    label="Last Name"
                    variant="outlined"
                    value={state.lastName}
                    onChange={handleChange}
                    error={!!errors.lastName}
                    helperText={errors.lastName}
                />
                <TextField
                    required
                    id="outlined-required-city"
                    name="city"
                    label="City"
                    variant="outlined"
                    value={state.address.city}
                    onChange={handleAddressChange}
                    error={!!errors.city}
                    helperText={errors.city}
                />
                <TextField
                    required
                    id="outlined-required-street"
                    name="street"
                    label="Street"
                    variant="outlined"
                    value={state.address.street}
                    onChange={handleAddressChange}
                    error={!!errors.street}
                    helperText={errors.street}
                />
                <TextField
                    required
                    id="outlined-required-houseNumber"
                    name="houseNumber"
                    label="House Number"
                    variant="outlined"
                    value={state.address.houseNumber}
                    onChange={handleAddressChange}
                    error={!!errors.houseNumber}
                    helperText={errors.houseNumber}
                />
                <TextField
                    required
                    id="outlined-required-phone"
                    name="telephone"
                    label="Phone"
                    variant="outlined"
                    type="tel"
                    value={state.telephone}
                    onChange={handleChange}
                    error={!!errors.telephone}
                    helperText={errors.telephone}
                />
                <TextField
                    required
                    id="outlined-required-mobilePhone"
                    name="cellPhone"
                    label="Mobile Phone"
                    variant="outlined"
                    type="tel"
                    value={state.cellPhone}
                    onChange={handleChange}
                    error={!!errors.cellPhone}
                    helperText={errors.cellPhone}
                />
                <TextField
                    required
                    id="outlined-required-idCard"
                    name="identity"
                    label="ID Card"
                    variant="outlined"
                    value={state.identity}
                    onChange={handleChange}
                    error={!!errors.identity}
                    helperText={errors.identity}
                />
                <TextField
                    required
                    id="outlined-required-dateOfBirth"
                    name="dateOfBirth"
                    label="Date of Birth"
                    variant="outlined"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    value={state.dateOfBirth}
                    onChange={handleChange}
                    error={!!errors.dateOfBirth}
                    helperText={errors.dateOfBirth}
                />
                <span style={{ color: "purple" }}>{message}</span>


            </Box>
            <button onClick={upload}>Update</button>
        </>
    );
}

export default AddMember;
