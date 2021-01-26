import React from 'react';
import { Card, CardBody, CardText, CardTitle } from 'reactstrap';

const ZirpCard = () => {
    return (
        <Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
            <CardBody>
                <CardTitle>@MockName</CardTitle>
                <CardText><p>Zirp here.</p></CardText>
                <a href="/">Read more</a>
                <footer className="blockquote-footer text-right">
                    <small>00.00.0000 00:00</small>
                </footer>
            </CardBody>
        </Card>
    );
};

export default ZirpCard;