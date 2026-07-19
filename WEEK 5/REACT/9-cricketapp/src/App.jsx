import React from 'react';
import ListofPlayers from './ListofPlayers.jsx';
import IndianPlayers from './IndianPlayers.jsx';

class App extends React.Component {
  constructor(props) {
    super(props);
    // flag variable: true -> show ListofPlayers, false -> show IndianPlayers
    this.state = { flag: true };
    this.toggleFlag = this.toggleFlag.bind(this);
  }

  toggleFlag() {
    this.setState((prevState) => ({ flag: !prevState.flag }));
  }

  render() {
    const { flag } = this.state;
    let content;
    if (flag) {
      content = <ListofPlayers />;
    } else {
      content = <IndianPlayers />;
    }

    return (
      <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px' }}>
        <h1>Cricket App</h1>
        <button onClick={this.toggleFlag}>
          Toggle View (flag = {String(flag)})
        </button>
        <hr />
        {content}
      </div>
    );
  }
}

export default App;
