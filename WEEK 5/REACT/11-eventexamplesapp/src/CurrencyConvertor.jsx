import React from 'react';

const INR_TO_EUR_RATE = 0.011; // approximate conversion rate

class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: '',
      euro: null,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ rupees: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    const rupees = parseFloat(this.state.rupees) || 0;
    const euro = (rupees * INR_TO_EUR_RATE).toFixed(2);
    this.setState({ euro });
  }

  render() {
    return (
      <div style={{ border: '1px solid #ccc', padding: '15px' }}>
        <h2>Currency Convertor (INR to Euro)</h2>
        <form onSubmit={this.handleSubmit}>
          <label>
            Rupees (&#8377;):{' '}
            <input
              type="number"
              value={this.state.rupees}
              onChange={this.handleChange}
              placeholder="Enter amount in Rupees"
            />
          </label>{' '}
          <button type="submit">Convert</button>
        </form>
        {this.state.euro !== null && (
          <p>
            &#8377;{this.state.rupees} = &#8364;{this.state.euro}
          </p>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
