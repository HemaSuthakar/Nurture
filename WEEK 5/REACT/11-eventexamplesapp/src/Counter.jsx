import React from 'react';

class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0,
      message: '',
      clickedMessage: '',
    };
    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
    this.handlePress = this.handlePress.bind(this);
  }

  increment() {
    // The Increment button invokes multiple methods:
    this.setState((prevState) => ({ count: prevState.count + 1 })); // 1. increment value
    this.sayHello(); // 2. say hello with a static message
  }

  decrement() {
    this.setState((prevState) => ({ count: prevState.count - 1 }));
  }

  sayHello() {
    this.setState({ message: 'Hello! Counter was updated.' });
  }

  sayWelcome(name) {
    this.setState({ message: `Say ${name}` });
  }

  // Synthetic event handler - React wraps native events in a SyntheticEvent
  handlePress(e) {
    this.setState({ clickedMessage: 'I was clicked' });
  }

  render() {
    return (
      <div style={{ border: '1px solid #ccc', padding: '15px', marginBottom: '20px' }}>
        <h2>Counter &amp; Events</h2>
        <p>Count: <b>{this.state.count}</b></p>
        <button onClick={this.increment}>Increment</button>{' '}
        <button onClick={this.decrement}>Decrement</button>
        <p>{this.state.message}</p>

        <button onClick={() => this.sayWelcome('welcome')}>Say Welcome</button>

        <p>
          <button onClick={this.handlePress}>OnPress</button>
        </p>
        <p>{this.state.clickedMessage}</p>
      </div>
    );
  }
}

export default Counter;
