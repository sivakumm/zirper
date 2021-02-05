import { Alert } from 'bootstrap';
import React from 'react';

const Error = ({ message }) => {
    return (
        <div>
            <Alert color="danger"><b>ERROR: </b>{ message }</Alert>
        </div>
    );
};

export default Error;