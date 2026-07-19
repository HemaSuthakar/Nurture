import { useState } from 'react'

function ComplaintRegister() {
  const [employeeName, setEmployeeName] = useState('')
  const [complaint, setComplaint] = useState('')

  const generateReferenceNumber = () => {
    const random = Math.floor(1000 + Math.random() * 9000)
    return `REF-${Date.now().toString().slice(-6)}-${random}`
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    if (!employeeName.trim() || !complaint.trim()) {
      alert('Please fill in both the employee name and the complaint.')
      return
    }

    const referenceNumber = generateReferenceNumber()
    alert(
      `Complaint registered successfully!\n\nEmployee: ${employeeName}\nReference Number: ${referenceNumber}\n\nPlease keep this reference number for follow-ups.`
    )

    setEmployeeName('')
    setComplaint('')
  }

  return (
    <div className="form-container">
      <h2>Register a Complaint</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="employeeName">Employee Name</label>
          <input
            type="text"
            id="employeeName"
            value={employeeName}
            onChange={(e) => setEmployeeName(e.target.value)}
            placeholder="Enter your name"
          />
        </div>

        <div className="form-group">
          <label htmlFor="complaint">Complaint</label>
          <textarea
            id="complaint"
            value={complaint}
            onChange={(e) => setComplaint(e.target.value)}
            placeholder="Describe your complaint"
          />
        </div>

        <button type="submit" className="submit-btn">
          Submit Complaint
        </button>
      </form>
    </div>
  )
}

export default ComplaintRegister
