import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import _ from 'lodash';
import Footer from './Util/Footer';
import Header from './Util/Header';
import Error from './Util/Error';
import Loading from './Util/Loading';
import ZirpContainer from './Zirper/ZirpContainer';

const App = () => {

  const url = useSelector(state => state.url, _.isEqual);

  const dispatch = useDispatch();

  const readConfig = () => {
    fetch(`application.json`)
    .then(response => response.json())
    .then(config => dispatch({ type: 'URL', value: config.url }))
    .catch(console.error);
  }

  useEffect(readConfig, [dispatch]);

  const renderZirpContainer = (url) => {
    return url ? <ZirpContainer /> : null;
  };

  return (
    <div className="container">
      <Header></Header>
      <Error></Error>
      <Loading></Loading>
      { renderZirpContainer(url) }
      <Footer></Footer>
    </div>
  );
};

export default App;