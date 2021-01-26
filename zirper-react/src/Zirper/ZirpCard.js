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
							<Button className="mx-3" color="primary" size="sm" id={ zirp.id + 'EditBtn' }><i class="fas fa-pen"></i></Button>
							<UncontrolledTooltip placement="top" target={ zirp.id + 'EditBtn' }>Edit</UncontrolledTooltip>
							<Button color="danger" size="sm" id={ zirp.id + 'RemoveBtn' }><i class="fas fa-trash"></i></Button>
							<UncontrolledTooltip placement="top" target={ zirp.id + 'RemoveBtn' }>Remove</UncontrolledTooltip>
						</Col>
					</Row>
				</CardTitle>
				<CardText><p><i>{ zirp.zirp }</i></p></CardText>
				<footer className="blockquote-footer text-right">
					<small>{ new Date(zirp.date).toLocaleString() }</small>
				</footer>
			</CardBody>
		</Card>
	);
};

export default ZirpCard;