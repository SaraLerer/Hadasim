import React, { useState, useEffect } from 'react';
import { LineChart } from '@mui/x-charts/LineChart';
import { subMonths, eachDayOfInterval, format } from 'date-fns';
import { useSelector } from 'react-redux';

function SummaryCorona() {

    const listCorona = useSelector((state) => state.corona.listCorona);
    const endDate = new Date();
    const startDate = subMonths(endDate, 1);
    const dates = eachDayOfInterval({ start: startDate, end: endDate });

    const patients = new Array(dates.length).fill(0); // Initialize an array to store the count of patients for each date

    for (let i = 0; i < dates.length; i++) {
        const date = dates[i];
        patients[i - 1] = listCorona.filter(corona => (
            corona.datePositiveResult &&
            new Date(corona.datePositiveResult) <= date &&
            (corona.recoveryDate === null || new Date(corona.recoveryDate) > date)
        )).length;
    }

    // Prepare data for the LineChart component
    const data = patients;

    // Prepare labels for the x-axis
    const labels = dates.map(date => format(date, 'dd/MM/yyyy'));

    console.log("data", data)
    console.log("labe ", labels)

    //בדיקה כמה חברי קופה אינם מחוסנים כלל
    const notVaccinated = listCorona.filter(corona => corona.vaccineList.length === 0).length;



    return (
        <>
            <div>
                <p>Number of members not vaccinated: {notVaccinated}</p>
            </div>
            <LineChart
                width={1400}
                height={300}
                series={[{ data: data, label: 'Active Patients', area: true, showMark: false }]}
                xAxis={[{ scaleType: 'point', data: labels, ticks: { fontSize: 10, fill: 'rgba(0, 0, 0, 0.5)' } }]}
                sx={{
                    '.MuiLineElement-root': {
                        display: 'none',
                    },
                }}
            />


        </>
    );
}

export default SummaryCorona;
