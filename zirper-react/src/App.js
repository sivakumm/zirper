import React from 'react';
import Footer from './Footer';
import Header from './Header';
import ZirpContainer from './Zirper/ZirpContainer';

const App = () => {
  return (
    <div className="container">
      <Header></Header>
      <ZirpContainer></ZirpContainer>
      <Footer></Footer>
    </div>
  );
};

export default App;