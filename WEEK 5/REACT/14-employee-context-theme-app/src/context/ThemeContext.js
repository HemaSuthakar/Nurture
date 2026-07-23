import { createContext } from 'react'

// Create a context to share the theme name with any nested child component
// without having to pass it down manually via props at every level.
const ThemeContext = createContext('light')

export default ThemeContext
