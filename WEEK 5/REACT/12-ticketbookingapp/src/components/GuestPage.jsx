import flights from '../data/flights'

function GuestPage() {
  return (
    <div className="container">
      <h2>Available Flights</h2>
      <table>
        <thead>
          <tr>
            <th>Flight No</th>
            <th>From</th>
            <th>To</th>
            <th>Departure</th>
            <th>Price (₹)</th>
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
            </tr>
          ))}
        </tbody>
      </table>
      <p className="guest-msg">Please login to book a ticket.</p>
    </div>
  )
}

export default GuestPage
