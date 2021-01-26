import React from 'react';
import Footer from './Footer';
import Header from './Header';

class App extends React.Component {
  render() {
    return (
    <div className="container">
      <Header></Header>
      <h1>Hello World!</h1>
      <Footer></Footer>
    </div>
    );
  }
}

export default App;
