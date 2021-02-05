import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { applyMiddleware, createStore } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import _ from 'lodash';
import 'bootstrap/dist/css/bootstrap.min.css';

const intialState = {
  url: '',
};

const reducerMapper = {
  'URL': (state, action) => ({ ...state, url: action.value })
};

const reducer = (state = intialState, action) => {
  return _.get(reducerMapper, action.type, _.identity)(state, action);
};

const store = createStore(reducer, applyMiddleware(thunk));

ReactDOM.render(
  <Provider store={ store }>
    <App />
  </Provider>,
  document.getElementById('root')
);
