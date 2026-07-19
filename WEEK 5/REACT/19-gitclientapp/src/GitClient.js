import axios from 'axios'

// GitClient talks to the public GitHub REST API to fetch a given
// user's repositories.
class GitClient {
  constructor() {
    this.baseUrl = 'https://api.github.com'
  }

  // Returns a promise that resolves to an array of repository names
  getRepositories(username) {
    return axios.get(`${this.baseUrl}/users/${username}/repos`).then((response) => {
      return response.data.map((repo) => repo.name)
    })
  }
}

export default GitClient
