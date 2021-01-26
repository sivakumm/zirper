import React from 'react';
import { Button, Card, CardBody, CardFooter, CardHeader, Input, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';

const ZirpCreateForm = () => {
	return (
		<Card>
			<form>
				<CardHeader>
					<InputGroup>
						<InputGroupAddon addonType="prepend">
							<InputGroupText>@</InputGroupText>
						</InputGroupAddon>
						<Input placeholder="username" />
					</InputGroup>
				</CardHeader>
				<CardBody>
					<Input type="textarea" placeholder="share your thoughts..." />
				</CardBody>
				<CardFooter>
					<Button color="success" block type="submit">Zirp</Button>
				</CardFooter>
			</form>
		</Card>
	);
};

export default ZirpCreateForm;