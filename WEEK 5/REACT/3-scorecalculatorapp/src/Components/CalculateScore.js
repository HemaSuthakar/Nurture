import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const { Name, School, Total, goal } = props;
  const average = (Total / goal) * 100;

  return (
    <div className="score-card">
      <h1>Student Score Card</h1>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Marks:</strong> {Total}</p>
      <p><strong>Goal (Max Marks):</strong> {goal}</p>
      <p><strong>Average Score:</strong> {average.toFixed(2)}%</p>
    </div>
  );
}

export default CalculateScore;
