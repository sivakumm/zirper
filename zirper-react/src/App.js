import React from 'react';
import Footer from './Footer';
import Header from './Header';
import ZirpCard from './Zirper/ZirpCard';
import ZirpCreateForm from './Zirper/ZirpCreateForm';

class App extends React.Component {
  render() {
    return (
    <div className="container">
      <Header></Header>
      <ZirpCreateForm></ZirpCreateForm>
      <hr />
      <ZirpCard></ZirpCard>
      <Footer></Footer>
    </div>
    );
  }
}

export default App;
