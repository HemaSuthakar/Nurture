import React from 'react';

const players = [
  { name: 'Rohit Sharma', score: 85 },
  { name: 'Virat Kohli', score: 92 },
  { name: 'KL Rahul', score: 45 },
  { name: 'Shreyas Iyer', score: 60 },
  { name: 'Rishabh Pant', score: 33 },
  { name: 'Hardik Pandya', score: 78 },
  { name: 'Ravindra Jadeja', score: 55 },
  { name: 'Jasprit Bumrah', score: 12 },
  { name: 'Mohammed Shami', score: 8 },
  { name: 'Kuldeep Yadav', score: 20 },
  { name: 'Shubman Gill', score: 70 },
];

// Arrow function using ES6 filter to find players with scores below 70
const lowScorers = players.filter((player) => player.score < 70);

class ListofPlayers extends React.Component {
  render() {
    return (
      <div>
        <h2>All Players</h2>
        <ul>
          {players.map((player, index) => (
            <li key={index}>
              {player.name} - {player.score}
            </li>
          ))}
        </ul>

        <h2>Players with score below 70</h2>
        <ul>
          {lowScorers.map((player, index) => (
            <li key={index}>
              {player.name} - {player.score}
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default ListofPlayers;
