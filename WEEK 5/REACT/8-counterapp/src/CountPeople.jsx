import React from 'react';

class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0,
    };
    this.UpdateEntry = this.UpdateEntry.bind(this);
    this.UpdateExit = this.UpdateExit.bind(this);
  }

  UpdateEntry() {
    this.setState((prevState) => ({ entrycount: prevState.entrycount + 1 }));
  }

  UpdateExit() {
    this.setState((prevState) => ({ exitcount: prevState.exitcount + 1 }));
  }

  render() {
    return (
      <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px' }}>
        <h1>Mall Entry / Exit Counter</h1>
        <p>Number of people who entered the mall: <b>{this.state.entrycount}</b></p>
        <p>Number of people who exited the mall: <b>{this.state.exitcount}</b></p>
        <button onClick={this.UpdateEntry}>Login</button>{' '}
        <button onClick={this.UpdateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
