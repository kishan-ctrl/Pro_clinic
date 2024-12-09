import React from 'react';
import { useNavigate } from 'react-router-dom';

const ReceptionDashboard = () => {
    const navigate = useNavigate();

    const handleViewAppointments = () => {
        navigate('/AllAppointments');
    };

    return (
        <div>
            <h1>Reception Dashboard</h1>
            <p>Welcome to the reception dashboard!</p>
            <button onClick={handleViewAppointments}>View Appointment</button>
        </div>
    );
};

export default ReceptionDashboard;
