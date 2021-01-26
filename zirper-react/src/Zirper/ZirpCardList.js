import React from 'react';
import ZirpCard from './ZirpCard';

const ZirpCardList = ({ zirps, deleteFn }) => {
	return (
		<div>
			{ zirps.map(zirp => <ZirpCard key={ zirp.id } zirp={ zirp } deleteFn={ deleteFn } ></ZirpCard>) }
		</div>
	);
};

export default ZirpCardList;