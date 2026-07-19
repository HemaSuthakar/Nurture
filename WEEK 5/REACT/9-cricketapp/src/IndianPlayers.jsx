import React from 'react';

const teamPlayers = [
  'Rohit Sharma',
  'Virat Kohli',
  'KL Rahul',
  'Shreyas Iyer',
  'Rishabh Pant',
  'Hardik Pandya',
];

// ES6 Destructuring: pick out odd (1st, 3rd, 5th) and even (2nd, 4th, 6th) position players
const [oddTeamPlayer1, evenTeamPlayer1, oddTeamPlayer2, evenTeamPlayer2, oddTeamPlayer3, evenTeamPlayer3] =
  teamPlayers;

const oddTeamPlayers = [oddTeamPlayer1, oddTeamPlayer2, oddTeamPlayer3];
const evenTeamPlayers = [evenTeamPlayer1, evenTeamPlayer2, evenTeamPlayer3];

// Two arrays to merge using the ES6 spread ("merge") feature
const T20players = ['Suryakumar Yadav', 'Ishan Kishan', 'Axar Patel'];
const RanjiTrophyPlayers = ['Prithvi Shaw', 'Sarfaraz Khan', 'Yash Dhull'];
const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

class IndianPlayers extends React.Component {
  render() {
    return (
      <div>
        <h2>Odd Team Players</h2>
        <ul>
          {oddTeamPlayers.map((name, index) => (
            <li key={index}>{name}</li>
          ))}
        </ul>

        <h2>Even Team Players</h2>
        <ul>
          {evenTeamPlayers.map((name, index) => (
            <li key={index}>{name}</li>
          ))}
        </ul>

        <h2>T20 + Ranji Trophy Players (Merged)</h2>
        <ul>
          {mergedPlayers.map((name, index) => (
            <li key={index}>{name}</li>
          ))}
        </ul>
      </div>
    );
  }
}

export default IndianPlayers;
