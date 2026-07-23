import CohortData from './data/Cohort'
import CohortDetails from './components/CohortDetails'

function App() {
  return (
    <div>
      <div className="header">
        <h1>🎓 Cohort Dashboard</h1>
      </div>
      <div className="dashboard">
        {CohortData.map((cohort) => (
          <CohortDetails key={cohort.id} cohort={cohort} />
        ))}
      </div>
    </div>
  )
}

export default App
