import React from 'react';
import { Col, Spinner } from 'reactstrap';

const Loading = () => {
    return (
        <Col className="text-center">
            <Spinner color="success" style={{ width: '5rem', height: '5rem' }} />
        </Col>
    );
};

export default Loading;