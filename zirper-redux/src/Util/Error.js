import { Alert } from 'reactstrap';
import React from 'react';
import { useSelector } from 'react-redux';
import _ from 'lodash';

const Error = () => {

    const message = useSelector(state => state.error, _.isEqual);

    return (
        <div style={{ display: message.length > 0 ? 'inherit' : 'none' }}>
            <Alert color="danger"><b>ERROR: </b>{ message }</Alert>
        </div>
    );
};

export default Error;