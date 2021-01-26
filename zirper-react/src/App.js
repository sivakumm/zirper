import React, { useEffect, useState } from 'react';
import Footer from './Footer';
import Header from './Header';
import ZirpContainer from './Zirper/ZirpContainer';

const App = () => {

  const [config, setConfig] = useState(null);

  useEffect(() => {
    fetch(`application.json`)
    .then(response => response.json())
    .then(config => setConfig(config))
    .catch(console.error);
  }, []);

  const renderZirpContainer = (config) => {
    return config ? <ZirpContainer serverUrl={ config.url } ></ZirpContainer> : null;
  };

  return (
    <div className="container">
      <Header></Header>
      { renderZirpContainer(config) }
      <Footer></Footer>
    </div>
  );
};

export default App;