import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserDashboard = () => {
  const [Id, setUserId] = useState('');
  const [appointments, setAppointments] = useState([]); // Store appointment data

  // Fetch user ID from localStorage and fetch appointments
  useEffect(() => {
    const storedId = localStorage.getItem('userId');
    if (storedId) {
      setUserId(storedId);
      fetchAppointments(storedId); // Fetch appointments for this user
    }
  }, []);

  // Function to fetch appointments for the user
  const fetchAppointments = (id) => {
    axios.get(`http://localhost:8085/ht/${id}`)
      .then(response => {
        setAppointments(response.data); // Store fetched appointments
      })
      .catch(error => {
        console.error('Error fetching appointments:', error);
      });
  };

  // Function to confirm an appointment
  const handleConfirm = (Id) => {
    axios.put(`http://localhost:8085/ht/appointments/confirm/${userEmail}`)
      .then(response => {
        console.log('Appointment confirmed:', response.data);
        // Update the status of the confirmed appointment
        setAppointments(appointments.map(app => 
          app.id === Id ? { ...app, status: 'Confirmed' } : app
        ));
      })
      .catch(error => {
        console.error('Error confirming appointment:', error);
      });
  };

  if (!userId) {
    return <div>Loading...</div>; // Show loading text while fetching user ID
  }

  return (
    <div>
      <h2>User Appointments</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {appointments.map(appointment => (
            <tr key={appointment.id}>
              <td>{appointment.date}</td>
              <td>
                {appointment.status !== 'Confirmed' ? (
                  <button onClick={() => handleConfirm(appointment.id)}>Confirm</button>
                ) : (
                  'Confirmed'
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserDashboard;
