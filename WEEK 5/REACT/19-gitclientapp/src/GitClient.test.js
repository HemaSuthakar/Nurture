import axios from 'axios'
import GitClient from './GitClient'

// Mock the axios module so the test never makes a real network call
jest.mock('axios')

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', () => {
    const mockResponse = {
      data: [
        { name: 'react-hooks-demo' },
        { name: 'node-api-starter' },
        { name: 'awesome-testing' },
      ],
    }

    axios.get.mockResolvedValueOnce(mockResponse)

    const gitClient = new GitClient()

    return gitClient.getRepositories('techiesyed').then((repoNames) => {
      expect(axios.get).toHaveBeenCalledWith(
        'https://api.github.com/users/techiesyed/repos'
      )
      expect(repoNames).toEqual([
        'react-hooks-demo',
        'node-api-starter',
        'awesome-testing',
      ])
    })
  })
})
