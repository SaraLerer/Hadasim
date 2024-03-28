// import React, { useState } from 'react';
// import Box from '@mui/material/Box';
// import TextField from '@mui/material/TextField';
// import { useDispatch, useSelector } from 'react-redux';

// function AddOrUpdateCorona() {
//     const dispatch = useDispatch();
//     const selectedMember = useSelector((state) => state.member.selectedMember);
//     const [state, setState] = useState(selectedMember.corona);
//     const [message, setMessage] = useState('');

//     const handleChange = (event) => {
//         const { name, value } = event.target;
//         setState(prevState => ({
//             ...prevState,
//             [name]: value
//         }));
//     };

//     const handleVaccineChange = (index, field, value) => {
//         setState(prevState => ({
//             ...prevState,
//             vaccineList: prevState.vaccineList.map((v, i) =>
//                 i === index ? { ...v, [field]: value } : v
//             )
//         }));
//     };

//     const addVaccine = () => {
//         if (state.vaccineList.length < 4) {
//             setState(prevState => ({
//                 ...prevState,
//                 vaccineList: [...prevState.vaccineList, { date: '', manufacturer: '' }]
//             }));
//         } else {
//             setMessage('Maximum limit of 4 vaccines reached. Cannot add more.');
//         }
//     };

//     const removeVaccine = (index) => {
//         setState(prevState => ({
//             ...prevState,
//             vaccineList: prevState.vaccineList.filter((_, i) => i !== index)
//         }));
//     };

//     const upload = () => {
//         // Check if any vaccine input is empty or has an invalid date format
//         const invalidVaccines = state.vaccineList.some(vaccine => {
//             // Check if date or manufacturer is empty
//             if (!vaccine.date.trim() || !vaccine.manufacturer.trim()) {
//                 return true;
//             }

//             // Convert input date to Date object
//             const vaccineDate = new Date(vaccine.date);

//             // Check if the date is in the future
//             if (vaccineDate > new Date()) {
//                 return true;
//             }

//             return false;
//         });

//         if (invalidVaccines) {
//             setMessage('Please provide a valid date and manufacturer for all vaccines, and ensure the date is not in the future.');
//             return;
//         }


//         const positiveResultDate = state.datePositiveResult;
//         const recoveryDate = state.recoveryDate;
//         const currentDate = new Date(); // Current date without time component
        
//         // Validation for positive result date not later than recovery date
//         if (recoveryDate && positiveResultDate > recoveryDate) {
//             setMessage('Date of positive result cannot be later than recovery date.');
//             return;
//         }
        
//         // Validation for providing recovery date without a positive result date
//         if (!positiveResultDate && recoveryDate) {
//             setMessage('Cannot provide a recovery date without a positive result date.');
//             return;
//         }
        
//         // Validation for positive result date in the future
//         if (positiveResultDate && positiveResultDate > currentDate) {
//             console.log("yessssssss")
//             setMessage('Please enter a valid positive result date.');
//             return;
//         }
        
//         // Validation for recovery date in the future
//         if (recoveryDate && recoveryDate > currentDate) {
//             setMessage('Please enter a valid recovery date.');
//             return;
//         }

//         // If all validations pass, dispatch the action to update corona details
//         setMessage('');
//         dispatch({
//             type: 'UPDATE_CORONA',
//             payload: { corona: state },
//         });
//     };



//     return (
//         <>
//             <Box sx={{ '& > :not(style)': { m: 1, width: '100ch' } }}>
//                 <TextField
//                     required
//                     id="date-positive-result"
//                     name="datePositiveResult"
//                     label="Date of Positive Result"
//                     variant="outlined"
//                     type="date"
//                     InputLabelProps={{
//                         shrink: true,
//                     }}
//                     value={state.datePositiveResult}
//                     onChange={handleChange}
//                 />
//                 <TextField
//                     required
//                     id="recovery-date"
//                     name="recoveryDate"
//                     label="Recovery Date"
//                     variant="outlined"
//                     type="date"
//                     InputLabelProps={{
//                         shrink: true,
//                     }}
//                     value={state.recoveryDate}
//                     onChange={handleChange}
//                 />

//                 {state.vaccineList.map((vaccine, index) => (
//                     <div key={index} style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
//                         <TextField
//                             required
//                             id={`vaccine-date-${index}`}
//                             name="date"
//                             label={`Vaccine Date ${index + 1}`}
//                             variant="outlined"
//                             type="date"
//                             InputLabelProps={{
//                                 shrink: true,
//                             }}
//                             value={vaccine.date}
//                             onChange={(e) => handleVaccineChange(index, 'date', e.target.value)}
//                         />
//                         <TextField
//                             required
//                             id={`vaccine-manufacturer-${index}`}
//                             name="manufacturer"
//                             label={`Vaccine Manufacturer ${index + 1}`}
//                             variant="outlined"
//                             value={vaccine.manufacturer}
//                             onChange={(e) => handleVaccineChange(index, 'manufacturer', e.target.value)}
//                         />
//                         <button onClick={() => removeVaccine(index)}>Remove</button>
//                     </div>
//                 ))}
//                 {message && <p>{message}</p>}
//                 <button onClick={addVaccine}>Add Vaccine</button>
//             </Box>
//             <button onClick={upload}>Upload</button>
//         </>
//     );
// }

// export default AddOrUpdateCorona;
import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useDispatch, useSelector } from 'react-redux';

function AddOrUpdateCorona() {
    const dispatch = useDispatch();
    const selectedMember = useSelector((state) => state.member.selectedMember);
    const [state, setState] = useState(selectedMember.corona);
    const [message, setMessage] = useState('');

    const handleChange = (event) => {
        const { name, value } = event.target;
        setState(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleVaccineChange = (index, field, value) => {
        setState(prevState => ({
            ...prevState,
            vaccineList: prevState.vaccineList.map((v, i) =>
                i === index ? { ...v, [field]: value } : v
            )
        }));
    };

    const addVaccine = () => {
        if (state.vaccineList.length < 4) {
            setState(prevState => ({
                ...prevState,
                vaccineList: [...prevState.vaccineList, { date: '', manufacturer: '' }]
            }));
        } else {
            setMessage('Maximum limit of 4 vaccines reached. Cannot add more.');
        }
    };

    const removeVaccine = (index) => {
        setState(prevState => ({
            ...prevState,
            vaccineList: prevState.vaccineList.filter((_, i) => i !== index)
        }));
    };

    const upload = () => {
        // Check if any vaccine input is empty or has an invalid date format
        const invalidVaccines = state.vaccineList.some(vaccine => {
            // Check if date or manufacturer is empty
            if (!vaccine.date.trim() || !vaccine.manufacturer.trim()) {
                return true;
            }

            // Convert input date to Date object
            const vaccineDate = new Date(vaccine.date);

            // Check if the date is in the future
            if (vaccineDate > new Date()) {
                return true;
            }

            return false;
        });

        if (invalidVaccines) {
            setMessage('Please provide a valid date and manufacturer for all vaccines, and ensure the date is not in the future.');
            return;
        }

        const positiveResultDate = new Date(state.datePositiveResult);
        const recoveryDate = new Date(state.recoveryDate);
        const currentDate = new Date(); // Current date without time component


         // Validation for positive result date in the future
         if (positiveResultDate > currentDate) {
            setMessage('Please enter a valid positive result date.');
            return;
        }

        // Validation for recovery date in the future
        if (recoveryDate > currentDate) {
            setMessage('Please enter a valid recovery date.');
            return;
        }
        // Validation for positive result date not later than recovery date
        if ( state.recoveryDate && positiveResultDate > recoveryDate) {
            setMessage('Date of positive result cannot be later than recovery date.');
            return;
        }

        // Validation for providing recovery date without a positive result date
        if (!state.datePositiveResult && state.recoveryDate) {
            setMessage('Cannot provide a recovery date without a positive result date.');
            return;
        }

       

        // If all validations pass, dispatch the action to update corona details
        setMessage('');
        dispatch({
            type: 'UPDATE_CORONA',
            payload: { corona: state },
        });
    };

    return (
        <>
            <Box sx={{ '& > :not(style)': { m: 1, width: '100ch' } }}>
            <h2 style={{ color: "purple" }}>Add corona details</h2>
                <TextField
                    required
                    id="date-positive-result"
                    name="datePositiveResult"
                    label="Date of Positive Result"
                    variant="outlined"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    value={state.datePositiveResult}
                    onChange={handleChange}
                />
                <TextField
                    required
                    id="recovery-date"
                    name="recoveryDate"
                    label="Recovery Date"
                    variant="outlined"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    value={state.recoveryDate}
                    onChange={handleChange}
                />

                {state.vaccineList.map((vaccine, index) => (
                    <div key={index} style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
                        <TextField
                            required
                            id={`vaccine-date-${index}`}
                            name="date"
                            label={`Vaccine Date ${index + 1}`}
                            variant="outlined"
                            type="date"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            value={vaccine.date}
                            onChange={(e) => handleVaccineChange(index, 'date', e.target.value)}
                        />
                        <TextField
                            required
                            id={`vaccine-manufacturer-${index}`}
                            name="manufacturer"
                            label={`Vaccine Manufacturer ${index + 1}`}
                            variant="outlined"
                            value={vaccine.manufacturer}
                            onChange={(e) => handleVaccineChange(index, 'manufacturer', e.target.value)}
                        />
                        <button onClick={() => removeVaccine(index)}>Remove</button>
                    </div>
                ))}
                {message && <p>{message}</p>}
                <button onClick={addVaccine}>Add Vaccine</button>
            </Box>
            <button onClick={upload}>Upload</button>
        </>
    );
}

export default AddOrUpdateCorona;
