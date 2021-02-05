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
  error: '',
  zirps: []
};

const reducerMapper = {
  'URL': (state, action) => ({ ...state, url: action.value }),
  'ERROR': (state, action) => ({ ...state, error: action.value }),
  'ZIRPS/ALL': (state, action) => ({ ...state, zirps: action.value }),
  'ZIRPS/ADD': (state, action) => ({ ...state, zirps: _.concat(state.zirps, action.value) }),
  'ZIRPS/REPLACE': (state, action) => ({ ...state, zirps: _.map(state.zirps, z => z.id === action.value.id ? action.value : z) }),
  'ZIRPS/DELETE': (state, action) => ({ ...state, zirps:  _.reject(state.zirps, { id: action.value }) })
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
