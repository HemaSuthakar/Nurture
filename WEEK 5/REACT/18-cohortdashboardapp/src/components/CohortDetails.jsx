function CohortDetails({ cohort }) {
  if (!cohort) {
    return <p>No cohort data available.</p>
  }

  const statusClass = cohort.status === 'Ongoing' ? 'status-ongoing' : 'status-completed'

  return (
    <div className="cohort-card">
      <h3>{cohort.code}</h3>
      <p>{cohort.name}</p>
      <p>Trainees: {cohort.trainees}</p>
      <span className={`status-badge ${statusClass}`}>{cohort.status}</span>
    </div>
  )
}

export default CohortDetails
