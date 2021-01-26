import React, { useState } from 'react';
import { Button, Card, CardBody, CardText, CardTitle, Col, Input, Row, UncontrolledTooltip } from 'reactstrap';

const ZirpCard = ({ originalZirp, deleteFn, updateFn }) => {

	const [editing, setEditing] = useState(false);
	const [zirp, setZirp] = useState(originalZirp);

	const updateZirp = (change) => {
		setZirp({ ...zirp, [change.target.name]: change.target.value });
	}

	const submitUpdate = () => {
		setEditing(false);
		updateFn(zirp);
	}

	return (
		<Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
			<CardBody>
				<CardTitle>
					<Row>
						<Col>
							<Input className="font-weight-bold" type="text" name="username" plaintext={ !editing } defaultValue={ '@' + zirp.username } onChange={ updateZirp } />
						</Col>
						<Col className="text-right">
							<Button className="mx-3" color={ editing ? 'success' : 'primary' } size="sm" id={ 'editBtn' + zirp.id } onClick={ editing ? submitUpdate : () => setEditing(!editing) }>
								<i className={ editing ? 'fas fa-save' : 'fas fa-pen' }></i>
							</Button>

							<Button color="danger" size="sm" id={ 'removeBtn' + zirp.id } onClick={ editing ? () => setEditing(!editing) : () => deleteFn(zirp.id) } >
								<i className={ editing ? 'fas fa-times' : 'fas fa-trash' }></i>
							</Button>

							<UncontrolledTooltip placement="top" target={ 'editBtn' + zirp.id }>
								{ editing ? 'Save' : 'Edit'}
							</UncontrolledTooltip>
							<UncontrolledTooltip placement="top" target={ 'removeBtn' + zirp.id }>
								{ editing ? 'Cancel' : 'Remove' }
							</UncontrolledTooltip>
						</Col>
					</Row>
				</CardTitle>
				<CardText>
					<Input type="textarea" name="zirp" plaintext={ !editing } defaultValue={ zirp.zirp } onChange={ updateZirp } />
				</CardText>
				<footer className="blockquote-footer text-right">
					<small>{ new Date(zirp.date).toLocaleString() }</small>
				</footer>
			</CardBody>
		</Card>
	);
};

export default ZirpCard;