import React from 'react';
import { Button, Card, CardBody, CardText, CardTitle, Col, Row } from 'reactstrap';

const ZirpCard = ({ zirp }) => {
	return (
		<Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
			<CardBody>
				<CardTitle>
					<Row>
						<Col>@{ zirp.username }</Col>
						<Col className="text-right">
							<Button className="mx-3" color="primary" size="sm"><i class="fas fa-pen"></i></Button>
							<Button color="danger" size="sm"><i class="fas fa-trash"></i></Button>
						</Col>
					</Row>
				</CardTitle>
				<CardText><p>{ zirp.zirp }</p></CardText>
				<footer className="blockquote-footer text-right">
					<small>{ new Date(zirp.date).toLocaleString() }</small>
				</footer>
			</CardBody>
		</Card>
	);
};

export default ZirpCard;