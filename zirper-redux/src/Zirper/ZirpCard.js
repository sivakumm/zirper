import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, CardBody, CardText, CardTitle, Col, Input, InputGroup, InputGroupAddon, InputGroupText, Row, UncontrolledTooltip } from 'reactstrap';
import _ from 'lodash';
import doFetch from '../Util/Network';

const ZirpCard = ({ originalZirp }) => {

	const [editing, setEditing] = useState(false);
	const [zirp, setZirp] = useState(originalZirp);
	
	const serverUrl = useSelector(state => state.url, _.isEqual);
	const dispatch = useDispatch();

	const updateZirp = (change) => {
		setZirp({ ...zirp, [change.target.name]: change.target.value });
	}

	const submitUpdate = () => {
		setEditing(false);
		doFetch(
			`${serverUrl}/zirps/${zirp.id}`,
			{
				method: 'PUT',
				body: JSON.stringify(zirp)
			},
			'ZIRPS/REPLACE',
			'Updating zirp',
			dispatch
		);
	}

	const deleteZirp = (zirpId) => {
		doFetch(
			`${serverUrl}/zirps/${zirpId}`,
			{ method: 'DELETE' },
			'ZIRPS/DELETE',
			'Removing zirp',
			dispatch
		);
	}

	const validForm = () => {
		return zirp.username.length > 2 && zirp.username.length < 16 && zirp.zirp.length > 0;
	}

	return (
		<Card className="my-2 mx-auto" style={{ maxWidth: "800px" }}>
			<CardBody>
				<CardTitle>
					<Row>
						<Col>
							<InputGroup>
								<InputGroupAddon addonType="prepend" style={{ display: editing ? 'inherit' : 'none' }}>
									<InputGroupText>@</InputGroupText>
								</InputGroupAddon>
								<Input className="font-weight-bold" type="text" name="username" plaintext={ !editing } defaultValue={ zirp.username } onChange={ updateZirp } value={ (editing ? '' : '@') + zirp.username }/>
							</InputGroup>
						</Col>
						<Col className="text-right">
							<Button className="mx-3" color={ editing ? 'success' : 'primary' } size="sm" id={ 'editBtn' + zirp.id } onClick={ editing ? submitUpdate : () => setEditing(!editing) } disabled={ editing && !validForm() }>
								<i className={ editing ? 'fas fa-save' : 'fas fa-pen' }></i>
							</Button>

							<Button color="danger" size="sm" id={ 'removeBtn' + zirp.id } onClick={ editing ? () => setEditing(!editing) : () => deleteZirp(zirp.id) } >
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