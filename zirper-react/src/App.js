import React from 'react';
import Footer from './Footer';
import Header from './Header';
import ZirpCardList from './Zirper/ZirpCardList';
import ZirpCreateForm from './Zirper/ZirpCreateForm';

const App = ({ zirps }) => {
  return (
    <div className="container">
      <Header></Header>
      <ZirpCreateForm></ZirpCreateForm>
      <hr />
      <ZirpCardList zirps={ zirps } ></ZirpCardList>
      <Footer></Footer>
    </div>
  );
};

export default App;

App.defaultProps = {
  zirps: [
    { id: 'xyz0', username: 'defaultUser0', zirp: 'Message to share with the world!', date: '2021-01-25T08:51:41.217Z' },
    { id: 'xyz1', username: 'defaultUser0', zirp: 'Message to share with the world!', date: '2021-01-25T09:51:41.217Z' },
    { id: 'xyz2', username: 'defaultUser1', zirp: 'Message to share with the world!', date: '2021-01-25T10:51:41.217Z' },
    { id: 'xyz3', username: 'defaultUser1', zirp: 'Message to share with the world!', date: '2021-01-25T11:51:41.217Z' },
  ]
};
