import { useState } from 'react'
import GuestPage from './components/GuestPage'
import UserPage from './components/UserPage'

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)

  const handleLogin = () => setIsLoggedIn(true)
  const handleLogout = () => setIsLoggedIn(false)

  // Element variable technique
  let authButton
  if (isLoggedIn) {
    authButton = <button onClick={handleLogout}>Logout</button>
  } else {
    authButton = <button onClick={handleLogin}>Login</button>
  }

  return (
    <div>
      <div className="header">
        <h1>✈ Ticket Booking App</h1>
        {authButton}
      </div>

      {/* Conditional rendering with ternary operator */}
      {isLoggedIn ? <UserPage /> : <GuestPage />}
    </div>
  )
}

export default App
