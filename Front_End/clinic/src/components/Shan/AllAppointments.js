import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AllAppointments.css';

const AllAppointments = () => {
  const [appointments, setAppointments] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch all appointments from the backend
    axios.get('http://localhost:8085/ht/all')
      .then((response) => {
        setAppointments(response.data);
      })
      .catch((error) => {
        console.error('Error fetching appointments:', error);
      });
  }, []);

  const handleUpdate = (Id) => {
    // Navigate to the update page with the appointment ID as a parameter
    navigate(`/Update/${Id}`);
  };

  const handleDelete = (Id) => {
    // Delete the appointment from the backend
    axios.delete(`http://localhost:8085/ht/Appointment/delete/${Id}`)
      .then(() => {
        // Remove the deleted appointment from the UI
        setAppointments((prevAppointments) =>
          prevAppointments.filter((appointment) => appointment.id !== Id)
        );
        alert('Appointment deleted successfully');
      })
      .catch((error) => {
        console.error('Error deleting appointment:', error);
      });
  };

  return (
    <div className="all-appointments-container">
      <h2 className="all-appointments-header">All Appointments</h2>
      <table className="table-appointments" border="1">
        <thead>
          <tr>
            <th>User Name</th>
            <th>User Email</th>
            <th>Phone Number</th>
            <th>Date</th>
            <th>Reason</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {appointments.map((appointment) => (
            <tr key={appointment.id}>
              <td>{appointment.userName}</td>
              <td>{appointment.userEmail}</td>
              <td>{appointment.phoneNumber}</td>
              <td>{appointment.date}</td>
              <td>{appointment.reason}</td>
              <td>
                <button className="button-update" onClick={() => handleUpdate(appointment.id)}>Update</button>
                <button className="button-delete" onClick={() => handleDelete(appointment.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AllAppointments;
