import employees from '../data/employees'
import EmployeeCard from './EmployeeCard'

// Theme is no longer passed down as a prop - each EmployeeCard
// reads it directly from ThemeContext.
function EmployeeList() {
  return (
    <div className="employee-list">
      {employees.map((employee) => (
        <EmployeeCard key={employee.id} employee={employee} />
      ))}
    </div>
  )
}

export default EmployeeList
