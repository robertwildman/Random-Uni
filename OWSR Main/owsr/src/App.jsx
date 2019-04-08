import React, { Component } from 'react';
import './App.css';
import { Form, FormControl, Button } from 'react-bootstrap';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      SR: '1000',
      newSR: ''
    }
  }

  changeSR() {
    this.setState({SR: this.state.newSR});
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Welcome to your Overwatch SR</h1>
        </header>
        <p className="App-intro">
          Simple program to keep track of your SR and Performence.
        </p>
        <div classname="SR">
          Your current SR is: {this.state.SR}
        </div>
        <Form>
          <FormControl
            className="SR-input"
            placeholder='new SR'
            onChange={event => this.setState({newSR: event.target.value})}
          />
          <Button onClick={() => this.changeSR()}>
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}

export default App;
