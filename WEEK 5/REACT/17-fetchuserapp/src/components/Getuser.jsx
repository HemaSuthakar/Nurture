import React from 'react'

// Class component using the lifecycle method componentDidMount() to
// fetch data, as required by the lab instructions.
class Getuser extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      user: null,
      loading: true,
      error: null,
    }
  }

  componentDidMount() {
    // Note: the working endpoint for the Random User Generator API is
    // https://randomuser.me/api/ (the lab document's "api.randomuser.me"
    // domain is not a live host).
    fetch('https://randomuser.me/api/')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok')
        }
        return response.json()
      })
      .then((data) => {
        const result = data.results[0]
        this.setState({ user: result, loading: false })
      })
      .catch((error) => {
        this.setState({ error: error.message, loading: false })
      })
  }

  render() {
    const { user, loading, error } = this.state

    if (loading) {
      return <p className="loading-msg">Loading user...</p>
    }

    if (error) {
      return <p className="error-msg">Failed to load user: {error}</p>
    }

    return (
      <div className="user-card">
        <img src={user.picture.large} alt="user avatar" />
        <h2>
          {user.name.title}. {user.name.first}
        </h2>
        <p>{user.email}</p>
      </div>
    )
  }
}

export default Getuser
