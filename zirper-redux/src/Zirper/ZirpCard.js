import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, CardBody, CardText, CardTitle, Col, Input, Row, UncontrolledTooltip } from 'reactstrap';
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
				headers: new Headers({
					'Content-Type': 'application/json'
				}),
				body: JSON.stringify(zirp)
			},
			data => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/REPLACE', value: data });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
	}

	const deleteZirp = (zirpId) => {
		doFetch(
			`${serverUrl}/zirps/${zirpId}`,
			{ method: 'DELETE' },
			() => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/DELETE', value: zirpId });
			},
			error => dispatch({ type: 'ERROR', value: error })
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
							<Input className="font-weight-bold" type="text" name="username" plaintext={ !editing } defaultValue={ '@' + zirp.username } onChange={ updateZirp } />
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