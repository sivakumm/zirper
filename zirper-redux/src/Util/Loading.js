import React from 'react';
import { useSelector } from 'react-redux';
import { Col } from 'reactstrap';

const Loading = () => {

    const loading = useSelector(state => state.loading);

    return (
        <div style={{ display: loading ? 'inherit' : 'inherit' }}>
            <Col className="text-center">
                <img src={ process.env.PUBLIC_URL + '/images/loading.gif' } alt="loading screen" height="100px" />
                <h4 className="text-success"><i>loading</i></h4>
            </Col>
        </div>
    );
};

export default Loading;