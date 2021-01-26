import React, { useEffect, useState } from 'react';
import Footer from './Util/Footer';
import Header from './Util/Header';
import Loading from './Util/Loading';
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
    return <Loading></Loading>;
    return config ? <ZirpContainer serverUrl={ config.url } ></ZirpContainer> : <Loading></Loading>;
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