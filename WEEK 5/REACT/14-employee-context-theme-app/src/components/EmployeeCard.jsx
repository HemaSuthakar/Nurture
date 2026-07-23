import { useContext } from 'react'
import ThemeContext from '../context/ThemeContext'

function EmployeeCard({ employee }) {
  // Retrieve the current theme value directly from context
  const theme = useContext(ThemeContext)

  const cardClass = theme === 'dark' ? 'employee-card dark-card' : 'employee-card light-card'
  const btnClass = theme === 'dark' ? 'dark-btn' : 'light-btn'

  return (
    <div className={cardClass}>
      <h3>{employee.name}</h3>
      <p>{employee.role}</p>
      <button className={btnClass}>View Profile</button>
    </div>
  )
}

export default EmployeeCard
