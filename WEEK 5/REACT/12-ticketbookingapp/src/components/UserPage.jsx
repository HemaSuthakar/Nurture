import { useState } from 'react'
import flights from '../data/flights'

function UserPage() {
  const [bookedId, setBookedId] = useState(null)

  const handleBook = (flight) => {
    setBookedId(flight.id)
    alert(`Ticket booked successfully for flight ${flight.flightNo} (${flight.from} → ${flight.to})`)
  }

  return (
    <div className="container">
      <h2>Book Your Flight</h2>
      <table>
        <thead>
          <tr>
            <th>Flight No</th>
            <th>From</th>
            <th>To</th>
            <th>Departure</th>
            <th>Price (₹)</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.id}>
              <td>{flight.flightNo}</td>
              <td>{flight.from}</td>
              <td>{flight.to}</td>
              <td>{flight.departure}</td>
              <td>{flight.price}</td>
              <td>
                {/* element variable pattern for conditional rendering */}
                {bookedId === flight.id ? (
                  <span>✅ Booked</span>
                ) : (
                  <button className="book-btn" onClick={() => handleBook(flight)}>
                    Book
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default UserPage
