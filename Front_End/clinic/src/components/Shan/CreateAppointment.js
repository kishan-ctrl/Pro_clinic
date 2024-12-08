import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // To navigate to other pages
import axios from 'axios'; // For making API requests

const CreateAppointment = () => {
  const [formData, setFormData] = useState({
    userName: '',
    userEmail: '',
    phoneNumber: '',
    date: '',
    reason: '',
  });

  const navigate = useNavigate(); // Hook to navigate to the next page

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Make API request to create the appointment
    axios.post('http://localhost:8085/ht/Appointment/create', formData)
      .then((response) => {
        console.log('Appointment created:', response.data);
        // Navigate to AllAppointments page after the appointment is created
        navigate('/recep', { state: { appointment: response.data } }); // Pass the response data as state
      })
      .catch((error) => {
        console.error('There was an error creating the appointment!', error);
      });
  };

  const handleCancel = () => {
    // Clear form fields or navigate away
    setFormData({
      userName: '',
      userEmail: '',
      phoneNumber: '',
      date: '',
      reason: '',
    });
    navigate('/'); // Navigate back to home page or previous page
  };

  return (
    <div className="create-appointment">
      <h2>Create Appointment</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="userName">User Name:</label>
          <input
            type="text"
            id="userName"
            name="userName"
            value={formData.userName}
            onChange={handleChange}
            required
          />
        </div>
        
        <div>
          <label htmlFor="userEmail">User Email:</label>
          <input
            type="email"
            id="userEmail"
            name="userEmail"
            value={formData.userEmail}
            onChange={handleChange}
            required
          />
        </div>
        
        <div>
          <label htmlFor="phoneNumber">Phone Number:</label>
          <input
            type="tel"
            id="phoneNumber"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleChange}
            required
          />
        </div>
        
        <div>
          <label htmlFor="date">Date:</label>
          <input
            type="date"
            id="date"
            name="date"
            value={formData.date}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="reason">Reason:</label>
          <textarea
            id="reason"
            name="reason"
            value={formData.reason}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <button type="submit">Create</button>
          <button type="button" onClick={handleCancel}>Cancel</button>
        </div>
      </form>
    </div>
  );
};

export default CreateAppointment;
