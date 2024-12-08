import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const UpdateAppointment = () => {
  const { id } = useParams(); // Get the appointment ID from the URL
  const navigate = useNavigate();

  const [appointment, setAppointment] = useState({
    userName: "",
    userEmail: "",
    phoneNumber: "",
    date: "",
    reason: "",
  });

  const [error, setError] = useState("");

  // Fetch the appointment details when the component loads
  useEffect(() => {
    axios
      .get(`http://localhost:8085/ht/${id}`)
      .then((response) => {
        setAppointment(response.data);
      })
      .catch((error) => {
        console.error("Error fetching appointment:", error);
        setError("Failed to fetch appointment details.");
      });
  }, [id]);

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setAppointment({ ...appointment, [name]: value });
  };

  // Submit the updated appointment to the backend
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:8085/ht/Appointment/update/${id}`, appointment)
      .then((response) => {
        alert("Appointment updated successfully!");
        navigate("/AllAppointments"); // Redirect to all appointments page
      })
      .catch((error) => {
        console.error("Error updating appointment:", error);
        setError("Failed to update appointment.");
      });
  };

  return (
    <div>
      <h2>Update Appointment</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        
        
        
        <div>
          <label>Date:</label>
          <input
            type="date"
            name="date"
            value={appointment.date}
            onChange={handleChange}
            required
          />
        </div>
        
        <button type="submit">Update Appointment</button>
        <button
          type="button"
          onClick={() => navigate("/AllAppointments")}
          
        >
          Cancel
        </button>
      </form>
    </div>
  );
};

export default UpdateAppointment;
