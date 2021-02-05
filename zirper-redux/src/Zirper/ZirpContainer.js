import React from 'react';
import ZirpCardList from './ZirpCardList';
import ZirpCreateForm from './ZirpCreateForm';

const ZirpContainer = () => {
	return (
		<div>
			<ZirpCreateForm />
			<hr />
			<ZirpCardList />
		</div>
	);
};

export default ZirpContainer;