import React, { useState } from 'react';
import { Button, Card, CardBody, CardFooter, CardHeader, Input, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';

const ZirpCreateForm = ({ createFn }) => {

	const [zirp, setZirp] = useState({ username: '', zirp: '' });

	const changeZirp = (changed) => {
		setZirp({ ...zirp, [changed.target.name]: changed.target.value });
	};

	const onSubmit = () => {
		createFn(zirp);
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