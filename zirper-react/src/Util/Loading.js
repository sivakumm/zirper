import React from 'react';
import { Col } from 'reactstrap';

const Loading = () => {
    return (
        <div>
            <Col className="text-center">
                <img src={ process.env.PUBLIC_URL + '/images/loading.gif' } alt="loading screen" />
                <h2 className="text-success">Source is loading</h2>
            </Col>
        </div>
    );
};

export default Loading;