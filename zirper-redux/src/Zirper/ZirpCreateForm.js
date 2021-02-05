import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, CardBody, CardFooter, CardHeader, Input, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';
import _ from 'lodash';
import doFetch from '../Util/Network';

const ZirpCreateForm = () => {

	const [zirp, setZirp] = useState({ username: '', zirp: '' });

	const serverUrl = useSelector(state => state.url, _.isEqual);
	const dispatch = useDispatch();

	const changeZirp = (changed) => {
		setZirp({ ...zirp, [changed.target.name]: changed.target.value });
	};

	const onSubmit = () => {
		doFetch(
			`${serverUrl}/zirps`,
			{
				method: 'POST',
				headers: new Headers({
					'Content-Type': 'application/json'
				}),
				body: JSON.stringify(zirp)
			},
			data => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/ADD', value: data });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
		setZirp({ username: '', zirp: '' });
	};

	const validForm = () => {
		return zirp.username.length > 2 && zirp.username.length < 16 && zirp.zirp.length > 0;
	}

	return (
		<Card>
			<form>
				<CardHeader>
					<InputGroup>
						<InputGroupAddon addonType="prepend">
							<InputGroupText>@</InputGroupText>
						</InputGroupAddon>
						<Input name="username" placeholder="username" value={ zirp.username } onChange={ changeZirp } />
					</InputGroup>
				</CardHeader>
				<CardBody>
					<Input type="textarea" name="zirp" placeholder="share your thoughts..." value={ zirp.zirp } onChange={ changeZirp } />
				</CardBody>
				<CardFooter>
					<Button color="success" block onClick={ onSubmit } disabled={ !validForm() }>Zirp</Button>
				</CardFooter>
			</form>
		</Card>
	);
};

export default ZirpCreateForm;