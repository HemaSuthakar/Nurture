import { useState } from 'react'
import ThemeContext from './context/ThemeContext'
import EmployeeList from './components/EmployeeList'

function App() {
  const [theme, setTheme] = useState('light')

  const toggleTheme = () => {
    setTheme((prev) => (prev === 'light' ? 'dark' : 'light'))
  }

  return (
    <ThemeContext.Provider value={theme}>
      <div className={theme === 'dark' ? 'dark-page' : 'light-page'}>
        <div className="header">
          <h1>Employee Management</h1>
          <button className="toggle-btn" onClick={toggleTheme}>
            Switch to {theme === 'light' ? 'Dark' : 'Light'} Mode
          </button>
        </div>
        <EmployeeList />
      </div>
    </ThemeContext.Provider>
  )
}

export default App
