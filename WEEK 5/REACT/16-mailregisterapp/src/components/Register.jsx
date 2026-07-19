import { useState } from 'react'

function Register() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
  })
  const [errors, setErrors] = useState({})
  const [submitted, setSubmitted] = useState(false)

  // Validates a single field and returns an error message (or '' if valid)
  const validateField = (name, value) => {
    switch (name) {
      case 'name':
        return value.trim().length >= 5 ? '' : 'Name must be at least 5 characters long.'
      case 'email':
        return value.includes('@') && value.includes('.')
          ? ''
          : 'Email must contain "@" and "."'
      case 'password':
        return value.length >= 8 ? '' : 'Password must be at least 8 characters long.'
      default:
        return ''
    }
  }

  // handleChange: validates as the user types (event handler based validation)
  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))
    setErrors((prev) => ({ ...prev, [name]: validateField(name, value) }))
  }

  // handleSubmit: re-validates every field on submit
  const handleSubmit = (e) => {
    e.preventDefault()

    const newErrors = {
      name: validateField('name', formData.name),
      email: validateField('email', formData.email),
      password: validateField('password', formData.password),
    }
    setErrors(newErrors)

    const hasErrors = Object.values(newErrors).some((msg) => msg !== '')
    if (!hasErrors) {
      setSubmitted(true)
    } else {
      setSubmitted(false)
    }
  }

  return (
    <div className="form-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit} noValidate>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            className={errors.name ? 'error' : ''}
          />
          {errors.name && <div className="error-text">{errors.name}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="text"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className={errors.email ? 'error' : ''}
          />
          {errors.email && <div className="error-text">{errors.email}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            className={errors.password ? 'error' : ''}
          />
          {errors.password && <div className="error-text">{errors.password}</div>}
        </div>

        <button type="submit" className="submit-btn">
          Register
        </button>

        {submitted && <p className="success-msg">Registration successful!</p>}
      </form>
    </div>
  )
}

export default Register
