import { useState } from 'react'
import GitClient from './GitClient'

const gitClient = new GitClient()

function App() {
  const [username, setUsername] = useState('techiesyed')
  const [repos, setRepos] = useState([])
  const [error, setError] = useState(null)

  const fetchRepos = () => {
    setError(null)
    gitClient
      .getRepositories(username)
      .then((names) => setRepos(names))
      .catch((err) => setError(err.message))
  }

  return (
    <div>
      <div className="header">
        <h1>🐙 Git Client App</h1>
      </div>
      <div className="repo-container">
        <h2>GitHub Repositories</h2>
        <div className="search-row">
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="GitHub username"
          />
          <button onClick={fetchRepos}>Fetch</button>
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <ul>
          {repos.map((name) => (
            <li key={name}>{name}</li>
          ))}
        </ul>
      </div>
    </div>
  )
}

export default App
