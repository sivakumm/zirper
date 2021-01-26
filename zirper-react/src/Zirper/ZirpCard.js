import React from 'react';
import { Button, Card, CardBody, CardText, CardTitle, Col, Row, UncontrolledTooltip } from 'reactstrap';

const ZirpCard = ({ zirp }) => {
	return (
		<Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
			<CardBody>
				<CardTitle>
					<Row>
						<Col><h5 className="font-weight-bold">@{ zirp.username }</h5></Col>
						<Col className="text-right">
							<Button className="mx-3" color="primary" size="sm" id={ 'editBtn' + zirp.id }><i className="fas fa-pen"></i></Button>
							<UncontrolledTooltip placement="top" target={ 'editBtn' + zirp.id }>Edit</UncontrolledTooltip>
							<Button color="danger" size="sm" id={ 'removeBtn' + zirp.id }><i className="fas fa-trash"></i></Button>
							<UncontrolledTooltip placement="top" target={ 'removeBtn' + zirp.id }>Remove</UncontrolledTooltip>
						</Col>
					</Row>
				</CardTitle>
				<CardText><i>{ zirp.zirp }</i></CardText>
				<footer className="blockquote-footer text-right">
					<small>{ new Date(zirp.date).toLocaleString() }</small>
				</footer>
			</CardBody>
		</Card>
	);
};

export default ZirpCard;