import React from 'react';
import { Card, CardBody, CardText, CardTitle } from 'reactstrap';

const ZirpCard = ({ zirp }) => {
	return (
		<Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
			<CardBody>
				<CardTitle>@{ zirp.username }</CardTitle>
				<CardText><p>{ zirp.zirp }</p></CardText>
				<a href="/">Read more</a>
				<footer className="blockquote-footer text-right">
					<small>{ new Date(zirp.date).toLocaleString() }</small>
				</footer>
			</CardBody>
		</Card>
	);
};

export default ZirpCard;